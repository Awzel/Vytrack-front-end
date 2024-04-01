package com.vytrack.pages;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String,String> mappy = new LinkedHashMap<>();
        mappy.put("A","ME");
        mappy.put("B","YOU");
        mappy.put("C","HIM");
        System.out.println(mappy.get(1));
    }
}
