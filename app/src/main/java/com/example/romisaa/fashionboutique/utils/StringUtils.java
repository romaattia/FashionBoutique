package com.example.romisaa.fashionboutique.utils;

public class StringUtils {

    public static boolean isNull(String value) {
        return value == null;
    }

    public static boolean isNullEmpty(String value) {
        return value == null || value.equals("");
    }

}
