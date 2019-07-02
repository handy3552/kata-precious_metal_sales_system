package com.coding.sales.entity;

import java.util.HashMap;
import java.util.Map;

public class Customer {
    /**
     * 客户姓名
     */
    private String customer_name;
    /**
     * 客户等级
     */
    private String customer_level;
    /**
     * 客户卡号
     */
    private String customer_card;
    /**
     * 客户积分
     */
    private int customer_points;

    private static Map<String, Customer> customer_map;
    /*马丁,普卡,6236609999,9860
    王立,金卡,6630009999,48860
    李想,白金卡,8230009999,98860
    张三,钻石卡,9230009999,198860*/
    static {
        customer_map = new HashMap<String, Customer>();
        customer_map.put("6236609999", new Customer("马丁", "普卡", "6236609999", 9860));
        customer_map.put("6630009999", new Customer("王立", "金卡", "6630009999", 48860));
        customer_map.put("8230009999", new Customer("李想", "白金卡", "8230009999", 98860));
        customer_map.put("9230009999", new Customer("张三", "钻石卡", "9230009999", 198860));
    }

    public Customer() {}

    public Customer(String customer_name, String customer_level,
                    String customer_card, int customer_points) {

        this.customer_name = customer_name;
        this.customer_level = customer_level;
        this.customer_card = customer_card;
        this.customer_points = customer_points;
    }

    public Customer getCustomer(String customer_card) {
        return customer_map.get(customer_card);
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_level() {
        return customer_level;
    }

    public void setCustomer_level(String customer_level) {
        this.customer_level = customer_level;
    }

    public String getCustomer_card() {
        return customer_card;
    }

    public void setCustomer_card(String customer_card) {
        this.customer_card = customer_card;
    }

    public int getCustomer_points() {
        return customer_points;
    }

    public void setCustomer_points(int customer_points) {
        this.customer_points = customer_points;
    }
}