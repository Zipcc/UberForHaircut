package com.bristol.project.filter;

public class URLFilter {

    // if the request is about register or login, permit without checking token.
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
