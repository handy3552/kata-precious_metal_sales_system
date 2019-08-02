package com.coding.sales;

import com.coding.sales.config.InitProductInfo;
import com.coding.sales.entity.*;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        //打折券信息
        List<String> discountCards = new ArrayList<String>();

        List<OrderItemCommand> orderItem_list = command.getItems();
        BigDecimal order_total_money = BigDecimal.ZERO;//优惠后总金额

        List<OrderItemRepresentation> orderItemRepresentationList = new ArrayList<OrderItemRepresentation>();
        OrderItemRepresentation orderItemRepresentation = null;

        List<DiscountItemRepresentation> discountsList = new ArrayList<DiscountItemRepresentation>();
        DiscountItemRepresentation discountItemRepresentation = null;

        BigDecimal order_total_amount = BigDecimal.ZERO;//订单不优惠总金额
        for(OrderItemCommand order_item : orderItem_list) {

            ProductInfo product = new ProductInfo();
            //获取商品信息
            product = InitProductInfo.productMap.get(order_item.getProduct());
            //计算单个商品总金额
            BigDecimal product_total_money = new BigDecimal(product.getProductPrice()).multiply(order_item.getAmount());
            BigDecimal product_rate_money = product_total_money;
            order_total_amount = order_total_amount.add(product_total_money);
            //计算是否有折扣
            SaleTicket ticket = product.getSaleGroup();
            BigDecimal ticket_money = BigDecimal.ZERO;
            if(null != ticket) {
                //折扣后金额
                ticket_money = product_rate_money.multiply(new BigDecimal(ticket.getSaleType()));
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
                        if(product_rate_money.compareTo(new BigDecimal(discount_type_info.getTotalAmout())) >= 0) {
                            //满减倍数  每满3000减350
                            BigDecimal times = product_rate_money.divide(new BigDecimal(discount_type_info.getTotalAmout()), 0, BigDecimal.ROUND_DOWN);
                            //满减后的金额
                            discount_money_temp = product_rate_money.subtract(new BigDecimal(discount_type_info.getDiscountAmout()).multiply(times));
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
                    product_rate_money = ticket_money;
                    discountCards.add(ticket.getSaleContent());
                } else {
                    product_rate_money = discount_money;
                }

            } else if(ticket_money.compareTo(discount_money) < 0) {
                //满减减的比较多
                if(ticket_money.compareTo(new BigDecimal(0)) == 0) {
                    product_rate_money = discount_money;
                } else {
                    product_rate_money = ticket_money;
                    discountCards.add(ticket.getSaleContent());
                }
            }
            //结果四舍五入
            product_total_money = product_total_money.setScale(2, BigDecimal.ROUND_HALF_UP);
            product_rate_money = product_rate_money.setScale(2, BigDecimal.ROUND_HALF_UP);

            //加入商品清单
            orderItemRepresentation = new OrderItemRepresentation(product.getProductId(),
                    product.getProductName(), new BigDecimal(product.getProductPrice()),
                    order_item.getAmount(), product_total_money);
            orderItemRepresentationList.add(orderItemRepresentation);

            //商品优惠清单
            if(product_total_money.compareTo(product_rate_money) > 0) {
                discountItemRepresentation = new DiscountItemRepresentation(product.getProductId(),
                        product.getProductName(), product_total_money.subtract(product_rate_money));
                discountsList.add(discountItemRepresentation);
            }


            order_total_money = order_total_money.add(product_rate_money);
        }

        System.out.println("商品总金额："+order_total_money);
        List<PaymentCommand> paymentsList  = command.getPayments();
        List<PaymentRepresentation> paymentRepresentationList = new ArrayList<PaymentRepresentation>();
        PaymentRepresentation paymentRepresentation  = null;
        for (PaymentCommand paymentCommand : paymentsList) {
            paymentRepresentation = new PaymentRepresentation(paymentCommand.getType(), paymentCommand.getAmount());
            paymentRepresentationList.add(paymentRepresentation);
        }

        int addPoint = order_total_money.intValue();
        customer.setCustomer_points_new(addPoint);
        int newPoint = customer.getCustomer_points_new();
        String newLevel = customer.getCustomer_level_new();

        //TODO: 请完成需求指定的功能
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        result = new OrderRepresentation(command.getOrderId(), format.parse(command.getCreateTime(), pos), command.getMemberId(),
                customer.getCustomer_name(), customer.getCustomer_level(), newLevel, addPoint, newPoint,
                orderItemRepresentationList, order_total_amount, discountsList,
                order_total_amount.subtract(order_total_money), order_total_money, paymentRepresentationList,
                discountCards);
        return result;
    }

    /**
     * 计算商品总金额
     * @return
     */
    private static int totalMoney() {
        int totalMoney = 0;

        return totalMoney;
    }
}
