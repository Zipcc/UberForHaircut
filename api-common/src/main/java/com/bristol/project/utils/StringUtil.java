package com.bristol.project.utils;

public class StringUtil {

    public static boolean notExist(String s){
        return s == null || s.isEmpty() || s.trim().isEmpty();
    }
}
