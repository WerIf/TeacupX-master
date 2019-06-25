package com.lily.teacup.httptool.operation;

import com.lily.teacup.base_class.TAPBean;
import com.lily.teacup.base_class.TAPHttp;
import com.lily.teacup.httptool.string.StringJsonAndAppend;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JPostHttpUrlConnection extends TAPHttp {
    /**
     *  同步请求
     *      PostHttpUrlConnection
     *      application/json
     * @param params
     * @param requestUrl
     * @return
     */
    public <T extends TAPBean> T request(JSONObject params, String requestUrl, Class<T> tClass){

        HttpURLConnection conn;
        BufferedReader bf;
        OutputStream op;
        try {
            URL url=new URL(requestUrl);
            conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type","application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            op=conn.getOutputStream();

            op.write(params.toString().getBytes());
            int responseCode=conn.getResponseCode();
            if(responseCode==200){
                bf=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String s = StringJsonAndAppend.appendStreamData(bf);
                return StringJsonAndAppend.TAPJsonObject(s,tClass);
            }

            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
