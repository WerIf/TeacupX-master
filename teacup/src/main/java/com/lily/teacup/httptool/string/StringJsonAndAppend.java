package com.lily.teacup.httptool.string;

import com.alibaba.fastjson.JSONObject;
import com.lily.teacup.base_class.TAPBean;

import java.io.BufferedReader;
import java.io.IOException;

public class StringJsonAndAppend {

    /**
     * @param bufferedReader 传入字符缓冲流
     * @return                返回拼接字符串结果
     * @throws IOException
     */
    public static String appendStreamData(BufferedReader bufferedReader) throws IOException {
        String responseData=null;
        StringBuilder sb=new StringBuilder();
        while ((responseData=bufferedReader.readLine())!=null){
            sb.append(responseData);
        }
        return sb.toString();
    }

    public static <T extends TAPBean> T TAPJsonObject(String result, Class<T> tClass){
        T obj=JSONObject.parseObject(result,tClass);
        return obj;
    }
}
