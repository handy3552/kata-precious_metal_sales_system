package com.coding.sales.entity;

import org.junit.Test;

public class LevelTest {

    public static void main(String[] args) {
        System.out.println((int)19360.0);
    }

    @Test
    public void getLevel() {
        Level level = new Level();
        System.out.println(level.getLevel(10234));//金卡
    }


    @Test
    public void getNewPoints() {
        Level level = new Level();
        System.out.println(level.getNewPoints(10000, 1000));//11500
    }
}