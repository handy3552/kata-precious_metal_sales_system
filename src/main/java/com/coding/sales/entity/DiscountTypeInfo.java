package com.coding.sales.entity;
/*
折扣券
满减（仅对满减商品使用）
每满3000元减350
每满2000元减30
每满1000元减10
第3件半价（买3件及以上，其中1件半价）
满3送1（买4件及以上，其中1件免费）
注：贵金属如果同时满足打折、满减，则只使用优惠力度最大的，不能同时使用。
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
