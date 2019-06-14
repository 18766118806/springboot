package com.pingan.scheduled;

import com.pingan.service.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * @Author:  Yajun_Xu
 * @Create: 2019-05-23 15:53
 **/
// @Component
// @Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    //3.添加定时任务
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒


    @Scheduled(fixedRate = 5000)
    private void configureTasks() {
        System.err.println ("执行静态定时任务时间: " + LocalDateTime.now ());
        // TODO Auto-generated method stub
        Process process;
        //.sh文件的绝对路径
        String command = "chmod 777/bin/sh/Users/test/install.sh";
        // String command1 = "chmod 777 " + shpath;
        List<String> processList = new ArrayList<String> ();

        try {
            process = Runtime.getRuntime ().exec (command);
            int res = process.waitFor ();
            BufferedReader input = new BufferedReader (new InputStreamReader (process.getInputStream ()));
            String line = "";
            while ((line = input.readLine ()) != null) {
                processList.add (line);
            }
            input.close ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        for (String line : processList) {
            System.out.println (">>>>>>>>>>>>>>>" + line);
        }

    }
}