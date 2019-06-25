package com.lily.teacup.httptool.operation;

import com.lily.teacup.base_class.TAPBean;
import com.lily.teacup.base_class.TAPHttp;
import com.lily.teacup.httptool.string.StringJsonAndAppend;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class XPostHttpUrlConnection extends TAPHttp {
    /**
     *  同步请求
     *      UrlConnection
     *      application/x-www-form-urlencoded
     * @param params        请求参数
     * @param requestUrl    请求网络地址
     * @return
     */
    public <T extends TAPBean> T request(Map<String,String> params, String requestUrl,Class<T> tClass){
        StringBuilder sb=new StringBuilder();
        FutureTask<String> task=new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                DataOutputStream out=null;
                BufferedReader br=null;
                HttpURLConnection conn;

                try{
                    URL postUrl=new URL(requestUrl);
                    //创建连接
                    conn= (HttpURLConnection) postUrl.openConnection();
                    conn.setConnectTimeout(30000);
                    conn.setReadTimeout(30000);
                    //允许写入数据
                    conn.setDoInput(true);
                    //允许输入数据
                    conn.setDoOutput(true);
                    //设置
                    //获取输出流
                    out=new DataOutputStream(conn.getOutputStream());
                    StringBuilder builder=new StringBuilder();
                    //拼接请求参数
                    for (String key: params.keySet()) {
                        builder.append(key+"="+URLEncoder.encode(params.get(key),"UTF-8")+"&");
                    }

                    //写入数据
                    out.writeBytes(builder.toString());
                    out.flush();
                    out.close();

                    br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line;
                    while ((line=br.readLine())!=null){
                        sb.append(line);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(br!=null){
                        br.close();
                    }

                    if(out!=null){
                        out.close();
                    }
                }
                return sb.toString();
            }
        });

        String response=null;

        new Thread(task).start();

        try {
            response=task.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return StringJsonAndAppend.TAPJsonObject(response,tClass);
    }
}
