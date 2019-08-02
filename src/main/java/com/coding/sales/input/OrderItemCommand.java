package com.coding.sales.input;

import java.math.BigDecimal;

public class OrderItemCommand {
    /**
     * 产品ID
     */
    String product;
    /**
     * 购买数量
     */
    BigDecimal amount;

    public OrderItemCommand(String product, BigDecimal amount) {
        this.product = product;
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
