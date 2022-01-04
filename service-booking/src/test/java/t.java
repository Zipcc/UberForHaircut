import com.alibaba.nacos.common.JustForTest;
import com.bristol.project.utils.TimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class t {

    @JustForTest
    public static void main(String[] args) {

        SimpleDateFormat sdfTime = new SimpleDateFormat("yyMMddHHmmssSS");
        String time =  sdfTime.format(new Date());
        System.out.println(time);
        Long l = Long.parseLong("20220104224708761");
        System.out.println(TimeUtil.getTime());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
        System.out.println( TimeUtil.getRandomNum());
    }
}
