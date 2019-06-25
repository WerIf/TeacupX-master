package com.lily.teacup.httptool.operation;

import com.lily.teacup.base_class.TAPBean;
import com.lily.teacup.base_class.TAPHttp;
import com.lily.teacup.httptool.string.StringJsonAndAppend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHttpUrlConnection extends TAPHttp {

    /**
     *  同步请求
     *          GetHttpUrlConnection
     * @param requestUrl    请求路径
     * @param tClass
     * @return              请求结果 Bean
     */
    public  <T extends TAPBean> T request(String requestUrl, Class<T> tClass){
        BufferedReader bf=null;
        try {
            URL url=new URL(requestUrl);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            //设置连接超时
            conn.setConnectTimeout(5000);
            //设置请求方式
            conn.setRequestMethod("GET");
            //获取请求返回代码
            int responseCode=conn.getResponseCode();

            //请求成功
            if(responseCode==200){
                InputStream input=conn.getInputStream();
                bf=new BufferedReader(new InputStreamReader(input));
                String s = StringJsonAndAppend.appendStreamData(bf);
                return StringJsonAndAppend.TAPJsonObject(s,tClass);
            }

            //请求失败
            return null;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
