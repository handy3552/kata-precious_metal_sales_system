package com.coding.sales.entity;

import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.input.PaymentCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.PaymentRepresentation;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private String orderId;
    private String createTime;
    private String memberId;
    private Customer customer;

    /**
     * 订单明细
     */
    List<OrderItemRepresentation> orderItems;
    /**
     * 优惠明细
     *  产品ID， 名称，优惠金额
     */
    List<DiscountItemRepresentation> discounts;
    /**
     * 应收金额
     */
    BigDecimal receivables;
    /**
     * 付款记录
     *   付款方式，付款金额
     */
    List<PaymentRepresentation> payments;
    private List<OrderItemCommand> items;
    //private List<PaymentCommand> payments;
    /**
     * 付款使用的打折券
     */
    private List<String> discountCards;



}
