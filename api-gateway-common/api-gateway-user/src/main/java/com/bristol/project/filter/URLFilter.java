package com.bristol.project.filter;

public class URLFilter {
    // dont forget the begin '/'   !!!
    private static final String permittedURL = "/ios/uaa/login,/ios/users";

    public static boolean needAuthorize(String url){

        String[] urls = permittedURL.split(",");

        for(String uri : urls){
            if(url.equals(uri)){
                return false;
            }
            System.out.println(uri);
            System.out.println(url);
        }
        return true;
    }
}
