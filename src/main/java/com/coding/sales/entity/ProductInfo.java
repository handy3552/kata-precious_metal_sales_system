package com.coding.sales.entity;

import java.math.BigDecimal;
import java.util.List;

public class ProductInfo {
    /*
     * 世园会五十国钱币册
     * 编号：001001
     * 单位：册
     * 价格：998.00元
     * 2019北京世园会纪念银章大全40g
     * 编号：001002
     * 单位：盒
     * 价格：1380.00
     * 可使用9折打折券
     * 招财进宝
     * 编号：003001
     * 单位：条
     * 价格：1580.00
     * 可使用95折打折券
     * 水晶之恋
     * 编号：003002
     * 单位：条
     * 价格：980.00
     * 参与满减：第3件半价，满3送1
     * 中国经典钱币套装
     * 编号：002002
     * 单位：套
     * 价格：998.00
     * 参与满减：每满2000减30，每满1000减10

     * 守扩之羽比翼双飞4.8g
     * 编号：002001
     * 单位：条
     * 价格：1080.00
     * 参与满减：第3件半价，满3送1
     * 可使用95折打折券
     * 中国银象棋12g
     * 编号：002003
     * 单位：套
     * 价格：698.00
     * 参与满减：每满3000元减350, 每满2000减30，每满1000减10
     * 可使用9折打折券
     */
    private String productName;
    private String productId;
    /**
     * 单位
     */
    private String productUnit;
    private BigDecimal productPrice;
    /**
     * 折扣组合
     */
    private SaleTicket saleGroup;
    /**
     * 满减规则
     */
    private List<DiscountTypeInfo> DiscountTypeInfoGroup;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public SaleTicket getSaleGroup() {
        return saleGroup;
    }

    public void setSaleGroup(SaleTicket saleGroup) {
        this.saleGroup = saleGroup;
    }

    public List<DiscountTypeInfo> getDiscountTypeInfoGroup() {
        return DiscountTypeInfoGroup;
    }

    public void setDiscountTypeInfoGroup(List<DiscountTypeInfo> discountTypeInfoGroup) {
        DiscountTypeInfoGroup = discountTypeInfoGroup;
    }
}
