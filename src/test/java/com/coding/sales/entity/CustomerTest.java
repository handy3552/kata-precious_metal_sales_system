package com.coding.sales.entity;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void getCustomer() {
        Customer customer = new Customer();
        System.out.println(customer.getCustomer("6236609999").getCustomer_name());
    }
}