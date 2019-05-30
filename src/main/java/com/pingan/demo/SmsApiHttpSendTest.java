package com.pingan.demo;


import com.pingan.demo.common.Config;
import com.pingan.demo.common.HttpUtil;

import java.net.URLEncoder;

/**
 * 短信API发送
 *
 * @author JiangPengFei
 * @version $Id: javaHttpNewApiDemo, v 0.1 2019/1/23 11:42 JiangPengFei Exp $$
 */
public class SmsApiHttpSendTest {

    /**
     * 短信发送(验证码通知，会员营销)
     * 接口文档地址：http://www.miaodiyun.com/doc/https_sms.html
     */
    public void execute(Object obj) throws Exception {
        StringBuilder sb = new StringBuilder ();
        sb.append ("accountSid").append ("=").append (Config.ACCOUNT_SID);
        sb.append ("&to").append ("=").append ("18862347829,18862488527");
        sb.append ("&param").append ("=").append (URLEncoder.encode ((String) obj, "UTF-8"));
        sb.append ("&templateid").append ("=").append ("1700219261");
        // sb.append ("&smsContent").append ("=").append (URLEncoder.encode ("您账户3302于05月26日14:28 入账工资,人民币 8806.68。【招商银行】", "UTF-8"));
        String body = sb.toString () + HttpUtil.createCommonParam (Config.ACCOUNT_SID, Config.AUTH_TOKEN);
        String result = HttpUtil.post (Config.BASE_URL, body);
        System.out.println (result);

    }

    public static void main(String[] args) {
        SmsApiHttpSendTest am = new SmsApiHttpSendTest ();
        try {
            am.execute (":开黑了!! 来自:【王者开黑群】");
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}
