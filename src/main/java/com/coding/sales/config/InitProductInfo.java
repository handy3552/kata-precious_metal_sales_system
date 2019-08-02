package com.coding.sales.config;

import com.coding.sales.entity.DiscountTypeInfo;
import com.coding.sales.entity.ProductInfo;
import com.coding.sales.entity.SaleTicket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 世园会五十国钱币册
 * 编号：001001
 * 单位：册
 * 价格：998.00元
 *
 * 2019北京世园会纪念银章大全40g
 * 编号：001002
 * 单位：盒
 * 价格：1380.00
 * 可使用9折打折券
 *
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
public class InitProductInfo {
    public static Map<String, ProductInfo> productMap = new HashMap<String, ProductInfo>();
    //初始化商品信息
    static{
        //初始化商品折扣券信息
        SaleTicket saleTicket_95 = new SaleTicket();
        saleTicket_95.setSaleType(0.95);
        saleTicket_95.setSaleContent("95折券");

        SaleTicket saleTicket_90 = new SaleTicket();
        saleTicket_90.setSaleType(0.9);
        saleTicket_90.setSaleContent("9折券");
        // 初始化打折券信息
        //每满3000元减350
        DiscountTypeInfo discountType_one = new DiscountTypeInfo();
        discountType_one.setDiscountType("1");
        discountType_one.setTotalAmout(3000);
        discountType_one.setDiscountAmout(350);
        //每满2000元减30
        DiscountTypeInfo discountType_two = new DiscountTypeInfo();
        discountType_two.setDiscountType("1");
        discountType_two.setTotalAmout(2000);
        discountType_two.setDiscountAmout(30);
        //每满1000元减10
        DiscountTypeInfo discountType_three = new DiscountTypeInfo();
        discountType_three.setDiscountType("1");
        discountType_three.setTotalAmout(1000);
        discountType_three.setDiscountAmout(10);

        //第3件半价（买3件及以上，其中1件半价）
        DiscountTypeInfo discountType_four = new DiscountTypeInfo();
        discountType_four.setDiscountType("2");
        discountType_four.setTotalAmout(3);
        discountType_four.setDiscountAmout(0.5);
        //满3送1（买4件及以上，其中1件免费）
        DiscountTypeInfo discountType_five = new DiscountTypeInfo();
        discountType_five.setDiscountType("2");
        discountType_five.setTotalAmout(4);
        discountType_five.setDiscountAmout(1);

        ProductInfo productOne = new ProductInfo();
        productOne.setProductId("001001");
        productOne.setProductName("世园会五十国钱币册");
        productOne.setProductPrice(new BigDecimal(998.00));
        productOne.setProductUnit("册");
        productMap.put("001001",productOne);

        ProductInfo productTwo = new ProductInfo();
        productTwo.setProductId("001002");
        productTwo.setProductName("2019北京世园会纪念银章大全40g");
        productTwo.setProductPrice(new BigDecimal(1380.00));
        productTwo.setProductUnit("盒");
        productTwo.setSaleGroup(saleTicket_90);
        productMap.put("001002",productTwo);

        ProductInfo productThree = new ProductInfo();
        productThree.setProductId("003001");
        productThree.setProductName("招财进宝");
        productThree.setProductPrice(new BigDecimal(1580.00));
        productThree.setProductUnit("条");
        productThree.setSaleGroup(saleTicket_95);
        productMap.put("003001",productThree);

        ProductInfo productFour = new ProductInfo();
        productFour.setProductId("003002");
        productFour.setProductName("水晶之恋");
        productFour.setProductPrice(new BigDecimal(980.00));
        productFour.setProductUnit("条");
        List<DiscountTypeInfo> DiscountTypeInfoList = new ArrayList<DiscountTypeInfo>();
        DiscountTypeInfoList.add(discountType_four);
        DiscountTypeInfoList.add(discountType_five);
        productFour.setDiscountTypeInfoGroup(DiscountTypeInfoList);
        productMap.put("003002",productFour);
        //中国经典钱币套装
        ProductInfo productFive = new ProductInfo();
        productFive.setProductId("002002");
        productFive.setProductName("中国经典钱币套装");
        productFive.setProductPrice(new BigDecimal(998.00));
        productFive.setProductUnit("套");
        List<DiscountTypeInfo> DiscountTypeInfoListTwo = new ArrayList<DiscountTypeInfo>();
        DiscountTypeInfoListTwo.add(discountType_two);
        DiscountTypeInfoListTwo.add(discountType_three);
        productFive.setDiscountTypeInfoGroup(DiscountTypeInfoListTwo);
        productMap.put("002002",productFive);

        //守扩之羽比翼双飞4.8g
        ProductInfo productSix = new ProductInfo();
        productSix.setProductId("002001");
        productSix.setProductName("守扩之羽比翼双飞4.8g");
        productSix.setProductPrice(new BigDecimal(1080.00));
        productSix.setProductUnit("条");
        List<DiscountTypeInfo> DiscountTypeInfoListThree = new ArrayList<DiscountTypeInfo>();
        DiscountTypeInfoListThree.add(discountType_four);
        DiscountTypeInfoListThree.add(discountType_five);
        productSix.setDiscountTypeInfoGroup(DiscountTypeInfoListThree);
        productSix.setSaleGroup(saleTicket_95);
        productMap.put("002001",productSix);

        //中国银象棋12g
        ProductInfo productSeven = new ProductInfo();
        productSeven.setProductId("002003");
        productSeven.setProductName("中国银象棋12g");
        productSeven.setProductPrice(new BigDecimal(698.00));
        productSeven.setProductUnit("套");
        List<DiscountTypeInfo> DiscountTypeInfoListFour = new ArrayList<DiscountTypeInfo>();
        DiscountTypeInfoListFour.add(discountType_one);
        DiscountTypeInfoListFour.add(discountType_two);
        DiscountTypeInfoListFour.add(discountType_three);
        productSeven.setDiscountTypeInfoGroup(DiscountTypeInfoListFour);
        productSeven.setSaleGroup(saleTicket_90);
        productMap.put("002003",productSeven);
    }

}
