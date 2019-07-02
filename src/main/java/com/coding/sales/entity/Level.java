package com.coding.sales.entity;

import java.util.HashMap;
import java.util.Map;

public class Level {

    private int level;
    private String level_name;
    private double level_times;
    private int[] point_area;

    private static Map<String, Level> level_map;

    /*普卡：1倍基准积分，累计积分1万以下
    金卡：1.5倍基准积分，累计积分1万（含）-5万（不含）
    白金卡：1.8倍基准积分，累计积分5万（含）-10万（不含）
    钻石卡：2倍基准积分，累计积分10万以上*/
    static {
        int[] level1 = {0, 10000};
        int[] level2 = {10000, 50000};
        int[] level3 = {50000, 100000};
        int[] level4 = {100000, Integer.MAX_VALUE};
        level_map = new HashMap<String, Level>();
        level_map.put("1", new Level(1, "普卡", 1, level1));
        level_map.put("2", new Level(2, "金卡", 1.5, level2));
        level_map.put("3", new Level(3, "白金卡", 1.8, level3));
        level_map.put("4", new Level(4, "钻石卡", 2, level4));
    }

    public Level() {
    }

    private Level(int level, String level_name, double level_times, int[] point_area) {
        this.level = level;
        this.level_name = level_name;
        this.level_times = level_times;
        this.point_area = point_area;
    }

    public Level getLevel(String level) {
        return level_map.get(level);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public double getLevel_times() {
        return level_times;
    }

    public void setLevel_times(double level_times) {
        this.level_times = level_times;
    }

    public int[] getPoint_area() {
        return point_area;
    }

    public void setPoint_area(int[] point_area) {
        this.point_area = point_area;
    }
}
