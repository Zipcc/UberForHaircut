package com.bristol.project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class TimeUtil {

    public static String getTime() {

        SimpleDateFormat sdfTime = new SimpleDateFormat("yy-MM-dd HH:mm:ss:SS");
        String time =  sdfTime.format(new Date());
        return time.replaceAll("[[\\s-:punct:]]", "");
    }

    public static String getRandomNum(){
        Random r = new Random();
        return String.valueOf(r.nextInt(9000)+1000);
    }
}
