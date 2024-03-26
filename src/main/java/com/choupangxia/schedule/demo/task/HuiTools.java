package com.choupangxia.schedule.demo.task;

import com.aliyun.dingtalkcalendar_1_0.models.AddMeetingRoomsResponse;
import com.aliyun.dingtalkcalendar_1_0.models.CreateEventRequest;
import com.aliyun.dingtalkcalendar_1_0.models.CreateEventResponse;
import com.aliyun.dingtalkcalendar_1_0.models.CreateEventResponseBody;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class HuiTools {


    /**
     * 获取token
     * @return
     */
    public   String getToken(){


        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest req = new OapiGettokenRequest();
            req.setHttpMethod("GET");
            req.setAppkey("dingllaynzcrinsoiqad");
            req.setAppsecret("2j7poB61sMX2Fl1o960borBc3Kyux0fB87-bT8hOWA7hgsvf7aoFwsEeQWoRjgKL");
            OapiGettokenResponse rsp = client.execute(req);

            if(rsp.isSuccess()){
                return  rsp.getAccessToken();
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return  null;
    }

    /**
     * 创建日程
     * @param token
     * @param userId
     * @param start
     * @param end
     * @return
     */

    public   String createRiCheng(String token, String userId, Map<String,String> start, Map<String,String> end){
        try {

            com.aliyun.dingtalkcalendar_1_0.Client client = createClient();
            com.aliyun.dingtalkcalendar_1_0.models.CreateEventHeaders createEventHeaders = new com.aliyun.dingtalkcalendar_1_0.models.CreateEventHeaders();


            CreateEventRequest createEventRequest = new CreateEventRequest();

            //token
            createEventHeaders.xAcsDingtalkAccessToken = token;

            //主日志
            String calendarId = "primary";
            //标题
            createEventRequest.setSummary("详设评审");
            //开始时间
            Map map = new HashMap();
            map.put("dateTime","2023-12-09T13:15:30+08:00");
            map.put("timeZone","Asia/Shanghai");

            CreateEventRequest.CreateEventRequestStart startO = CreateEventRequest.CreateEventRequestStart.build(start);
            createEventRequest.setStart(startO);

            //结束时间
            Map map2 = new HashMap();
            map2.put("dateTime","2023-12-09T13:15:30+08:00");
            map2.put("timeZone","Asia/Shanghai");

            CreateEventRequest.CreateEventRequestEnd end0 = CreateEventRequest.CreateEventRequestEnd.build(end);
            createEventRequest.setEnd(end0);

            //是否全天
            createEventRequest.setIsAllDay(false);
            createEventRequest.setReminders(new ArrayList<>());

            CreateEventResponse eventWithOptions = client.createEventWithOptions(userId, calendarId, createEventRequest, createEventHeaders, new RuntimeOptions());

            CreateEventResponseBody body = eventWithOptions.getBody();

            String id = body.getId();

            log.info("创建成功 "+id);

            return  id;

        }  catch (Exception _err) {

            log.info("创建失败 "+_err.getMessage());

        }
        return "";

    }


    public   String createRiCheng2(String token, String userId, Map<String,String> start, Map<String,String> end){
        try {

            com.aliyun.dingtalkcalendar_1_0.Client client = createClient();
            com.aliyun.dingtalkcalendar_1_0.models.CreateEventHeaders createEventHeaders = new com.aliyun.dingtalkcalendar_1_0.models.CreateEventHeaders();


            CreateEventRequest createEventRequest = new CreateEventRequest();

            //token
            createEventHeaders.xAcsDingtalkAccessToken = token;

            //主日志
            String calendarId = "primary";
            //标题
            createEventRequest.setSummary("详设评审");
            //开始时间
            Map map = new HashMap();
            map.put("dateTime","2023-12-09T13:15:30+08:00");
            map.put("timeZone","Asia/Shanghai");

            CreateEventRequest.CreateEventRequestStart startO = CreateEventRequest.CreateEventRequestStart.build(start);
            createEventRequest.setStart(startO);

            //结束时间
            Map map2 = new HashMap();
            map2.put("dateTime","2023-12-09T13:15:30+08:00");
            map2.put("timeZone","Asia/Shanghai");

            CreateEventRequest.CreateEventRequestEnd end0 = CreateEventRequest.CreateEventRequestEnd.build(end);
            createEventRequest.setEnd(end0);

            //是否全天
            createEventRequest.setIsAllDay(false);


            CreateEventResponse eventWithOptions = client.createEventWithOptions(userId, calendarId, createEventRequest, createEventHeaders, new RuntimeOptions());

            CreateEventResponseBody body = eventWithOptions.getBody();

            String id = body.getId();

            log.info("创建成功 "+id);

            return  id;

        }  catch (Exception _err) {

            log.info("创建失败 "+_err.getMessage());

        }
        return "";

    }

    public   void deleteRiCheng(String token,String userId, String eventId)  {
        try{
            com.aliyun.dingtalkcalendar_1_0.Client client = createClient();
            com.aliyun.dingtalkcalendar_1_0.models.DeleteEventHeaders deleteEventHeaders = new com.aliyun.dingtalkcalendar_1_0.models.DeleteEventHeaders();
            deleteEventHeaders.xAcsDingtalkAccessToken =token;
            com.aliyun.dingtalkcalendar_1_0.models.DeleteEventRequest deleteEventRequest = new com.aliyun.dingtalkcalendar_1_0.models.DeleteEventRequest();
            client.deleteEventWithOptions(userId, "primary", eventId, deleteEventRequest, deleteEventHeaders, new com.aliyun.teautil.models.RuntimeOptions());
            log.info("取消日程成功 {}",eventId);
        }catch (Exception e){
            log.info("取消日程失败 {}",eventId);
        }

    }


    public   boolean addHuiyishi(String token,String userId,String eventId,String roomId){
        try {
            com.aliyun.dingtalkcalendar_1_0.Client client = createClient();
            com.aliyun.dingtalkcalendar_1_0.models.AddMeetingRoomsHeaders addMeetingRoomsHeaders = new com.aliyun.dingtalkcalendar_1_0.models.AddMeetingRoomsHeaders();
            addMeetingRoomsHeaders.xAcsDingtalkAccessToken = token;
            com.aliyun.dingtalkcalendar_1_0.models.AddMeetingRoomsRequest.AddMeetingRoomsRequestMeetingRoomsToAdd meetingRoomsToAdd0 = new com.aliyun.dingtalkcalendar_1_0.models.AddMeetingRoomsRequest.AddMeetingRoomsRequestMeetingRoomsToAdd()
                    .setRoomId(roomId);
            com.aliyun.dingtalkcalendar_1_0.models.AddMeetingRoomsRequest addMeetingRoomsRequest = new com.aliyun.dingtalkcalendar_1_0.models.AddMeetingRoomsRequest()
                    .setMeetingRoomsToAdd(Arrays.asList(
                            meetingRoomsToAdd0
                    ));

            AddMeetingRoomsResponse primary = client.addMeetingRoomsWithOptions(userId, "primary", eventId, addMeetingRoomsRequest, addMeetingRoomsHeaders, new RuntimeOptions());

            if(primary.getStatusCode()==200){
                log.info("添加会议室成功  room {}  eventId {}",roomId,eventId);
                return true;
            }

        } catch (Exception _err) {
            log.info("添加会议室失败"+_err.getMessage());
            return false;

        }
        return false;
    }


    public static com.aliyun.dingtalkcalendar_1_0.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkcalendar_1_0.Client(config);
    }
}
