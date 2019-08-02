package com.coding.sales.input;

import java.math.BigDecimal;

public class PaymentCommand {
    /**
     * 账户类型
     */
    String type;
    /**
     * 账户金额
     */
    BigDecimal amount;

    public PaymentCommand(String type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
