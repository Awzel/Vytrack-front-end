package com.vytrack.pages;

public class Test {
    public static void main(String[] args) {
        String str = "Hello I am %s blah blah";
        String newStr = String.format(str,"Flora");
        System.out.println(newStr);
    }
}
