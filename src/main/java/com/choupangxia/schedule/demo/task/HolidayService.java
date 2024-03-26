package com.choupangxia.schedule.demo.task;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 度假服务
 *
 * @author hjljy
 * @date 2021/07/27
 */
public class HolidayService {

    static List<String> holiday =new ArrayList<>();
    static List<String> extraWorkDay =new ArrayList<>();



    public static Boolean isWorkingDay2(long time) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.of("+8"));
        String formatTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        initHoliday();
        initExtraWorkDay();
        //是否加班日
        if(extraWorkDay.contains(formatTime)){
            return true;
        }
        //是否节假日
        if(holiday.contains(formatTime)){
            return false;
        }
        //如果是1-5表示周一到周五  是工作日

        // 创建Calendar对象
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(time);

        // 设置为当前时间
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);


        if(dayOfWeek==Calendar.SATURDAY||dayOfWeek==Calendar.SUNDAY){
            return false;
        }
        return true;

    }
    public static Boolean isWorkingDay(long time) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.of("+8"));
        String formatTime = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        initHoliday();
        initExtraWorkDay();
        //是否加班日
        if(extraWorkDay.contains(formatTime)){
            return true;
        }
        //是否节假日
        if(holiday.contains(formatTime)){
            return false;
        }
        //如果是1-5表示周一到周五  是工作日
        DayOfWeek week = dateTime.getDayOfWeek();
        if(week==DayOfWeek.SATURDAY||week==DayOfWeek.SUNDAY){
            return false;
        }
        return true;

    }

    public static Boolean isZhouwu2(long time) {

        //判断是否是周五

        // 创建Calendar对象
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(time);

        // 设置为当前时间
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        if(dayOfWeek==Calendar.FRIDAY){
            return true;
        }
        return false;

    }

    public static Boolean isZhouwu(long time) {


        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.of("+8"));

        //如果是1-5表示周一到周五  是工作日
        DayOfWeek week = dateTime.getDayOfWeek();
        if(week==DayOfWeek.FRIDAY){
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        String dateString = DateUtil.format(new Date(), "yyyy-MM-dd");
        DateTime dateTime = DateUtil.parseDate(dateString);
        String dayString = DateUtil.format(DateUtil.offsetDay(dateTime, 8), "yyyy-MM-dd");
        List< Map<String, Map<String,String>>> list = new ArrayList<>();

        isXiuxi(dayString);
    }

    /**
     *  初始化节假日
     */
    public static void initHoliday(){
        String[] dates = {"2024-01-01", "2024-01-06", "2024-01-07", "2024-01-13", "2024-01-14", "2024-01-20", "2024-01-21", "2024-01-27", "2024-01-28", "2024-01-25", "2024-01-26", "2024-01-27", "2024-02-03", "2024-02-10", "2024-02-11", "2024-02-12", "2024-02-13", "2024-02-14", "2024-02-15", "2024-02-16", "2024-02-17", "2024-02-24", "2024-02-25", "2024-03-02", "2024-03-03", "2024-03-09", "2024-03-10", "2024-03-16", "2024-03-17", "2024-03-23", "2024-03-24", "2024-03-30", "2024-03-31", "2024-04-04", "2024-04-05", "2024-04-06", "2024-04-13", "2024-04-14", "2024-04-20", "2024-04-21", "2024-04-27", "2024-05-01", "2024-05-02", "2024-05-03", "2024-05-04", "2024-05-05", "2024-05-12", "2024-05-18", "2024-05-19", "2024-05-25", "2024-05-26", "2024-06-01", "2024-06-02", "2024-06-08", "2024-06-09", "2024-06-10", "2024-06-15", "2024-06-16", "2024-06-22", "2024-06-23", "2024-06-29", "2024-06-30", "2024-07-06", "2024-07-07", "2024-07-13", "2024-07-14", "2024-07-20", "2024-07-21", "2024-07-27", "2024-07-28", "2024-08-03", "2024-08-04", "2024-08-10", "2024-08-11", "2024-08-17", "2024-08-18", "2024-08-24", "2024-08-25", "2024-08-31", "2024-09-01", "2024-09-07", "2024-09-08", "2024-09-15", "2024-09-16", "2024-09-17", "2024-09-21", "2024-09-22", "2024-09-28", "2024-10-01", "2024-10-02", "2024-10-03", "2024-10-04", "2024-10-05", "2024-10-06", "2024-10-07", "2024-10-13", "2024-10-19", "2024-10-20", "2024-10-26", "2024-10-27", "2024-11-02", "2024-11-03", "2024-11-09", "2024-11-10", "2024-11-23", "2024-11-24", "2024-11-30", "2024-12-01", "2024-12-07", "2024-12-08", "2024-12-14", "2024-12-15", "2024-12-21", "2024-12-22", "2024-12-28", "2024-12-29"};


        for (String date : dates) {
            holiday.add(date);
        }

/*        holiday.add("2021-01-01");
        holiday.add("2021-01-02");*/

    }
    /**
     *  初始化额外加班日
     */
    public static void initExtraWorkDay(){
        extraWorkDay.add("2021-02-07");

    }

    public static boolean isXiuxi(String date){


        try{
            String newDate= date.replaceAll("-", "");
            //1.拿到okHttpClient对象,可以设置连接超时等
            OkHttpClient okHttpClient=new OkHttpClient();

            //2.构造Request请求对象，可以增加头addHeader等
            Request.Builder builder = new Request.Builder();
            //url()中可以放入网址
            Request request = builder.
                    get().
                    url("https://tool.bitefu.net/jiari/?d="+newDate)
                    .build();
            Call call = okHttpClient.newCall(request);
            //4.执行call  工作日 0   正常休息日 1    双重休息日 2
            Response response=call.execute();//汇抛出IO异常，同步方法
            String string = response.body().string();

            System.out.println("日期判断函数 isXiuxi  result"+date+ "   "+string);

            return  !"0".equals(string);

        }catch (Exception e){
            System.out.println("日期判断出现问题 "+e.getMessage());
            return  false;
        }

    }
}
