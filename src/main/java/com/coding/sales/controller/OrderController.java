package com.coding.sales.controller;

import com.coding.sales.config.InitProductInfo;
import com.coding.sales.entity.DiscountTypeInfo;
import com.coding.sales.entity.Order;
import com.coding.sales.entity.ProductInfo;
import com.coding.sales.entity.SaleTicket;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;

import java.math.BigDecimal;
import java.util.List;

public class OrderController {

    public Order calculateOrder(OrderCommand command) {
        Order order = new Order();

        List<OrderItemCommand> orderItemCommands = command.getItems();
        List<PaymentCommand> payments = command.getPayments();
        for(OrderItemCommand orderProductInfo : orderItemCommands) {
            ProductInfo productInfo = InitProductInfo.productMap.get(orderProductInfo.getProduct());
            //优惠前该商品总金额
            BigDecimal beforeTotalMoney = productInfo.getProductPrice().multiply(orderProductInfo.getAmount());
            productInfo.getSaleGroup();//该商品打折券
            productInfo.getDiscountTypeInfoGroup();//该商品满减券
            BigDecimal afterMoney = calcuAfterMoney(productInfo.getSaleGroup(), productInfo.getDiscountTypeInfoGroup());
        }



        return order;
    }

    private static BigDecimal calcuAfterMoney(SaleTicket tickets, List<DiscountTypeInfo> list) {



        return BigDecimal.ZERO;
    }
}
