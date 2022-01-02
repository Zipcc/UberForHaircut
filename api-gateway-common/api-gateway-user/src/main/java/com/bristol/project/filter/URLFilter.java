package com.bristol.project.filter;

public class URLFilter {

    private static final String permittedURL = "/ios/uaa/login,/ios/users";

    public static boolean needAuthorize(String url){

        String[] urls = permittedURL.split(",");

        for(String uri : urls){
            if(url.equals(uri)){
                return false;
            }
        }
        return true;
    }
}
