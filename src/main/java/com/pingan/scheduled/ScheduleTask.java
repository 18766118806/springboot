package com.pingan.scheduled;


import com.pingan.demo.SmsApiHttpSendTest;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


/*
 * @Author:  Yajun_Xu
 * @Create: 2019-05-26 03:20
 **/
@Component
@Configuration
@EnableScheduling
public class ScheduleTask {
    // 每小时比较一次价格
    @Scheduled(fixedRate = 10000 * 60)
    public static void scheduleTask() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault ();
        HttpGet httpGet = new HttpGet ("https://search.jd.com/Search?keyword=%E7%B4%A2%E5%B0%BC%20900N&enc=utf-8&pvid=8e2c9fca68c7405499b76a4bc0d28c3c");
        httpGet.setHeader ("user-agent", "Mozelle/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KABUL, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute (httpGet);
        OutputStreamWriter writer = new OutputStreamWriter (new FileOutputStream ("/Users/logs/SONY.txt", true), "utf-8");
        HttpEntity entity = response.getEntity ();
        if (response.getStatusLine ().getStatusCode () == 200) {
            String html = EntityUtils.toString (entity, Consts.UTF_8);
            Document doc = Jsoup.parse (html);
            //解析 html
            Elements ulList = doc.select ("#J_goodsList").select (".gl-item");
            writer.write (new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format (new Date ()));
            writer.write ("\r\n");
            HashMap<Object, Object> objectObjectHashMap = new HashMap<> ();
            String replace = null;
            for (Element item : ulList) {
                String business = item.select (".J_im_icon").select ("a").text ();

                String price = item.select (".p-price").select ("strong").select ("i").text ();
                if (!"".equals (business) && business != null && !"".equals (price) && price != null) {
                    if (Double.parseDouble (price) < 1200.00 && Double.parseDouble (price) > 1000) {
                        // 短信通知
                        // new SmsApiHttpSendTest ().execute (business + " : " + price);
                        //return;
                    }
                    objectObjectHashMap.put (business, price);
                    writer.write (business);
                    String len = ":";
                    for (int i = 0; i < 20 - business.length (); i++) {
                        len += " ";
                    }
                    writer.write (len + price);
                    writer.write ("\r\n");
                }


            }
            //   replace = objectObjectHashMap.toString ().replace ("{", "").replace ("=", ":").replace ("}", "").replace (",", "，");


            //  new SmsApiHttpSendTest ().execute (replace);
            writer.write ("\r\n");
            writer.write ("\r\n");
            writer.close ();
            response.close ();
        } else {
            EntityUtils.consume (response.getEntity ());
        }
    }
}
