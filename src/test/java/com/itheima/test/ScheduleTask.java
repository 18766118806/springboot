package com.itheima.test;


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
import java.time.LocalDateTime;
import java.util.Date;


/*
 * @Author:  Yajun_Xu
 * @Create: 2019-05-26 02:10
 **/
@Component
@Configuration
@EnableScheduling
public class ScheduleTask {
    @Scheduled(fixedRate = 10000)
    public static void scheduleTaskTest() throws Exception {
        System.err.println (LocalDateTime.now ());
        String url = "https://search.jd.com/Search?keyword=%E7%B4%A2%E5%B0%BC%20900N&enc=utf-8&pvid=8e2c9fca68c7405499b76a4bc0d28c3c";
        CloseableHttpClient httpclient = HttpClients.createDefault ();
        HttpGet httpGet = new HttpGet (url);
        httpGet.setHeader ("user-agent", "Mozelle/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KABUL, like Gecko) Chrome/70.0.3538.102 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute (httpGet);
        OutputStreamWriter writer = new OutputStreamWriter (new FileOutputStream ("/Users/test/SONY.txt", true), "utf-8");
        int statusCode = response.getStatusLine ().getStatusCode ();
        try {
            HttpEntity entity = response.getEntity ();
            if (statusCode == 200) {
                String html = EntityUtils.toString (entity, Consts.UTF_8);
                Document doc = Jsoup.parse (html);
                Elements ulList = doc.select ("#J_goodsList").select (".gl-item");
                writer.write (new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss" + "  >>>>>>>>>>>>>>>>").format (new Date ()));
                writer.write ("\r\n");
                for (Element item : ulList) {
                    String business = item.select (".J_im_icon").select ("a").text ();
                    String price = item.select (".p-price").select ("strong").select ("i").text ();
                    if (!"".equals (business) && business != null && !"".equals (price) && price != null) {
                        writer.write (business);
                        String len = ":";
                        for (int i = 0; i < 20 - business.length (); i++) {
                            len += " ";
                        }
                        writer.write (len + price);
                        writer.write ("\r\n");
                    }
                }
                writer.write ("\r\n");
            } else {
                EntityUtils.consume (response.getEntity ());
            }
        } finally {
            writer.close ();
            response.close ();
        }
    }
}
