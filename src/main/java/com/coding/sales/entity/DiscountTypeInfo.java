package com.coding.sales.entity;
/*
客户支付时如果使用打折券，对订单中的 所有 可打折贵金属将享受优惠：
95折券：如果商品参与95折，则应收金额为95折
9折券：如果商品参与9折，则应收金额为9折
 */
public class DiscountTypeInfo {

    // 1:金额满减 2：数量满减
    private String discountType;
    private int totalAmout;
    private int discountAmout;

    public int getTotalAmout() {
        return totalAmout;
    }

    public void setTotalAmout(int totalAmout) {
        this.totalAmout = totalAmout;
    }

    public int getDiscountAmout() {
        return discountAmout;
    }

    public void setDiscountAmout(int discountAmout) {
        this.discountAmout = discountAmout;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }
}
