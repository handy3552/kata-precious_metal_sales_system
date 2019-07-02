package com.coding.sales.entity;

/*
客户支付时如果使用打折券，对订单中的 所有 可打折贵金属将享受优惠：
95折券：如果商品参与95折，则应收金额为95折
9折券：如果商品参与9折，则应收金额为9折
 */
public class SaleTicket {
/*
折扣率
 */
    private double saleType;
    /*

     */
    private String saleContent;

    public double getSaleType() {
        return saleType;
    }

    public void setSaleType(double saleType) {
        this.saleType = saleType;
    }

    public String getSaleContent() {
        return saleContent;
    }

    public void setSaleContent(String saleContent) {
        this.saleContent = saleContent;
    }
}
