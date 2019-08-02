package com.coding.sales.entity;

public class Level {

//    private static Map<String, Level> level_map;

    /*普卡：1倍基准积分，累计积分1万以下
    金卡：1.5倍基准积分，累计积分1万（含）-5万（不含）
    白金卡：1.8倍基准积分，累计积分5万（含）-10万（不含）
    钻石卡：2倍基准积分，累计积分10万以上*/
   /* static {
        int[] level1 = {0, 10000};
        int[] level2 = {10000, 50000};
        int[] level3 = {50000, 100000};
        int[] level4 = {100000, Integer.MAX_VALUE};
        level_map = new HashMap<String, Level>();
        level_map.put("1", new Level(1, "普卡", 1, level1));
        level_map.put("2", new Level(2, "金卡", 1.5, level2));
        level_map.put("3", new Level(3, "白金卡", 1.8, level3));
        level_map.put("4", new Level(4, "钻石卡", 2, level4));
    }*/

    public static int getNewPoints(double oldPoint, int addPoint) {
        double newPoint = 0;
        if("普卡".equals(getLevel(oldPoint))) {
            newPoint = oldPoint + addPoint;
        } else if("金卡".equals(getLevel(oldPoint))) {
            newPoint = oldPoint + addPoint * 1.5;
        } else if("白金卡".equals(getLevel(oldPoint))) {
            newPoint = oldPoint + addPoint * 1.8;
        } else if("钻石卡".equals(getLevel(oldPoint))) {
            newPoint = oldPoint + addPoint * 2;
        }
        return (int) newPoint;
    }

    public static String getLevel(double point) {
        if(point >= 100000) {
            return "钻石卡";
        } else if(point >= 50000) {
            return "白金卡";
        } else if(point >= 10000) {
            return "金卡";
        } else {
            return "普卡";
        }
    }



}
