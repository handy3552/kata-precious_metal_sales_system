package com.coding.sales;

import com.coding.sales.config.InitProductInfo;
import com.coding.sales.entity.Customer;
import com.coding.sales.entity.DiscountTypeInfo;
import com.coding.sales.entity.ProductInfo;
import com.coding.sales.entity.SaleTicket;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.output.OrderRepresentation;

import java.math.BigDecimal;
import java.util.List;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);
        
        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        OrderRepresentation result = null;

        // 获取客户信息
        Customer customer = new Customer();
        customer = customer.getCustomer(command.getMemberId());
        List<OrderItemCommand> orderItem_list = command.getItems();
        BigDecimal order_total_money = BigDecimal.ZERO;
        for(OrderItemCommand order_item : orderItem_list) {
            ProductInfo product = new ProductInfo();
            //获取商品信息
            product = InitProductInfo.productMap.get(order_item.getProduct());
            //计算单个商品总金额
            BigDecimal product_total_money = new BigDecimal(product.getProductPrice()).multiply(order_item.getAmount());
            //计算是否有折扣
            SaleTicket ticket = product.getSaleGroup();
            BigDecimal ticket_money = BigDecimal.ZERO;
            if(null != ticket) {
                //折扣后金额
                ticket_money = product_total_money.multiply(new BigDecimal(ticket.getSaleType()));
            }
            //计算是否有满减
            List<DiscountTypeInfo> discount_type_info_list = product.getDiscountTypeInfoGroup();
            //满减后金额
            BigDecimal discount_money = BigDecimal.ZERO;
            //满减后金额临时变量
            BigDecimal discount_money_temp = BigDecimal.ZERO;
            if(null != discount_type_info_list && !discount_type_info_list.isEmpty()) {
                for(DiscountTypeInfo discount_type_info : discount_type_info_list) {
                    //按金额满减
                    if("1".equals(discount_type_info.getDiscountType())) {
                        if(product_total_money.compareTo(new BigDecimal(discount_type_info.getTotalAmout())) >= 0) {
                            //满减倍数  每满3000减350
                            BigDecimal times = product_total_money.divide(new BigDecimal(discount_type_info.getTotalAmout()), 0, BigDecimal.ROUND_DOWN);
                            //满减后的金额
                            discount_money_temp = product_total_money.subtract(new BigDecimal(discount_type_info.getDiscountAmout()).multiply(times));
                            //获取最少的满减后金额
                            if(discount_money_temp.compareTo(discount_money) < 0 || discount_money.compareTo(BigDecimal.ZERO) == 0) {
                                discount_money = discount_money_temp;
                            }
                        }
                    } else if("2".equals(discount_type_info.getDiscountType())) {
                        //按件满减
                        if(order_item.getAmount().compareTo(new BigDecimal(3)) < 0) {
                            continue;
                        }
                        //第三件半价
                        if(3 == discount_type_info.getTotalAmout()) {
                            discount_money = new BigDecimal(product.getProductPrice()).multiply(order_item.getAmount().subtract(new BigDecimal(0.5)));
                            //获取最少的满减后金额
                            if(discount_money_temp.compareTo(discount_money) < 0) {
                                discount_money = discount_money_temp;
                            }
                        } else if(4 == discount_type_info.getTotalAmout() && order_item.getAmount().compareTo(new BigDecimal(4)) >= 0) {
                            //满3减1
                            discount_money = new BigDecimal(product.getProductPrice()).multiply(order_item.getAmount().subtract(new BigDecimal(1)));
                            if(discount_money_temp.compareTo(discount_money) < 0) {
                                discount_money = discount_money_temp;
                            }
                        }
                    }
                }
            }
            //比较折扣金额和满减金额
            if(ticket_money.compareTo(discount_money) > 0) {
                //折扣减的比较多
                if(discount_money.compareTo(new BigDecimal(0)) == 0) {
                    product_total_money = ticket_money;
                } else {
                    product_total_money = discount_money;
                }
            } else if(ticket_money.compareTo(discount_money) < 0) {
                //满减减的比较多
                if(ticket_money.compareTo(new BigDecimal(0)) == 0) {
                    product_total_money = discount_money;
                } else {
                    product_total_money = ticket_money;
                }
            }
            order_total_money = order_total_money.add(product_total_money);


        }
        System.out.println("商品总金额："+order_total_money);

        System.out.println(customer.getCustomer_name());

        //TODO: 请完成需求指定的功能

        return result;
    }
}
