package com.choupangxia.schedule.demo.task.two;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.choupangxia.schedule.demo.task.HolidayService;
import com.choupangxia.schedule.demo.task.HuiTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("huiyi4Job")
@Slf4j
public class HuiYi4Task {



    public static void main(String[] args) {
        HuiYi4Task HuiYiTask = new HuiYi4Task();
        HuiYiTask.qiangHuiyishi();
    }


/*    @Scheduled(cron = "01 00 00 * * ?")*/
    public   void qiangHuiyishi(){

        HuiTools huiTools = new HuiTools();


        //1. 用户id 为哪个人创建日程   该userId 需要填unionid 的值
        String userId = Config4.userId;



        //===========      会议室强占逻辑         =========




         //获取到day天后的日期
        String dateString = DateUtil.format(new Date(), "yyyy-MM-dd");
        DateTime dateTime = DateUtil.parseDate(dateString);
        String dayString = DateUtil.format(DateUtil.offsetDay(dateTime, Config4.day), "yyyy-MM-dd");
        List< Map<String,Map<String,String>>> list = new ArrayList<>();


        //判断当前时间是否是工作日
        if(HolidayService.isXiuxi(dayString)){
            System.out.println("今天是休息日");
            log.info("今天是休息日");
            return;
        }

        //获取所有日期信息
        for (List<String> time : Config4.times) {
            Map<String,Map<String,String>> result1 = new HashMap();
            Map<String,String> map1Start = new HashMap();
            map1Start.put("dateTime",dayString+"T"+time.get(0)+"+08:00");
            map1Start.put("timeZone","Asia/Shanghai");
            Map<String,String> map1End = new HashMap();
            map1End.put("dateTime",dayString+"T"+time.get(1)+"+08:00");
            map1End.put("timeZone","Asia/Shanghai");
            result1.put("start",map1Start);
            result1.put("end",map1End);
            list.add(result1);

        }


        //获取token
        String token = huiTools.getToken();


        //按照时间段循环抢占会议室

        for (int i = 0; i < list.size(); i++) {
            Map<String, Map<String, String>> stringMapMap= list.get(i);

            Map<String,String> start = stringMapMap.get("start");
            Map<String,String> end = stringMapMap.get("end");

            log.info("================开始会议室抢占333 ===============  ");
            log.info("================"+start.get("dateTime")+"-"+end.get("dateTime")+" ===============  ");


            //2.创建日程
            String riChengId = huiTools.createRiCheng(token, userId, start, end);

            //3.添加会议室
            boolean  flag= huiTools.addHuiyishi(token, userId, riChengId, Config4.room.get(i));


            //如果添加会议室失败，取消日程
            if(!flag){
                huiTools.deleteRiCheng(token,userId,riChengId);
            }
        }



    }





}
