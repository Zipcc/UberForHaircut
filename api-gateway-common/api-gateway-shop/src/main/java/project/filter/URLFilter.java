package project.filter;

import java.util.Arrays;

public class URLFilter {

    private static final String permittedURL = "/ios/shops";

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
