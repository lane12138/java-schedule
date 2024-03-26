package com.choupangxia.schedule.demo.task.two;

import java.util.Arrays;
import java.util.List;

public  interface Config4 {

    //1. 用户id 为哪个人创建日程   该userId 需要填unionid 的值
    /**
     *           userId   "095263360226358665",  梁乾昆  unionid   2iS0jWuBky6CycFa0IQFEZwiEiE
     *           userId  "23017559",   徐昊冉  unionid   kK6Cd5h5WA2nhGeHmhcFTAiEiE
     *           userId "18011198",  张小干 unionid  aedKTr1JJQfVBoiPRo9SyMAiEiE
     *           userId  "23049298",  余德礼 unionid  atvUJuWFRiP2ycFa0IQFEZwiEiE
     *           userId   "024655313132700871", 胡立云  unionid  Z3E0usUz2IunhGeHmhcFTAiEiE
     *           userId  "426659366626132330",  朱学伟       unionid   rmBSQf5Xii9Q9flwo1DbJiSwiEiE
     *           userId  "1534124845675924", 刘彬  unionid  69IdBVDpqgLkFZz82q54wAiEiE
     *           userId  "21027457"  李之  unionid  1C5YsDRaIjVq1LGwWOpZiPgiEiE
     *           userId  "21024585" 雷天娟  unionid  A3hZEhMe20kiE
     */
    String userId = "rmBSQf5Xii9Q9flwo1DbJiSwiEiE";


    //2. 抢占几天后的   会议室1,2,3,5,6 默认开放7天后的    会议室4 默认开放14天后的
    //注意部分人员开了特殊权限 不受会议室预定时间限制  像 李雪 、闫强强、陈嘉慧、奚晓燕等等
    // 本程序只具有普通权限 无法预定规定时间之外的会议室
    int day = 7;


    //3. 强占哪个时间段
    List<List<String>> times= Arrays.asList(
            Arrays.asList("13:30:00","15:30:00")
    );



    /**
     *		"roomId":"38d947ce6e51b512bec93b0bbc44cabe2210717f845d69a6",
     * 			"roomName":"金砖洽谈室（二）"
     *
     *	"roomId":"7e6abb953d0f0a5b7aed3da1eecbf0602210717f845d69a6",
     * 			"roomName":"金砖洽谈室（三）"
     *
     * 				"roomId":"c02adade7999b4e00a4a9e2e6c6475882210717f845d69a6",
     * 			"roomName":"金砖洽谈室（四）"
     *
     * 		"roomId":"3aa78455c493cb653bd7b68489097d4d2210717f845d69a6",
     * 			"roomName":"金砖洽谈室（五）"
     *
     * 			"roomId":"42d06869cee9ea0350b08813297b990a2210717f845d69a6",
     * 			"roomName":"金砖洽谈室（一）"
     *
     * 		"roomId":"ecab20f3482f79d39909c4bde72ec9bc2210717f845d69a6",
     * 			"roomName":"金砖洽谈室（六）"
     *
     *
     */

    //4.  时间段对应的会议室  对应times 数据的下标
    List<String> room = Arrays.asList(
            "38d947ce6e51b512bec93b0bbc44cabe2210717f845d69a6");



    //所有会议室 排除会议室1  当room中的会议室都被占用时  会从rooms中循环抢占
   List<String> rooms = Arrays.asList(
           "ecab20f3482f79d39909c4bde72ec9bc2210717f845d69a6",
           "3aa78455c493cb653bd7b68489097d4d2210717f845d69a6",
           "c02adade7999b4e00a4a9e2e6c6475882210717f845d69a6",
           "7e6abb953d0f0a5b7aed3da1eecbf0602210717f845d69a6",
           "38d947ce6e51b512bec93b0bbc44cabe2210717f845d69a6");











}
