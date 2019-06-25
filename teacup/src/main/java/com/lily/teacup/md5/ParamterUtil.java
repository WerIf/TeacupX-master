package com.lily.teacup.md5;

import android.text.TextUtils;

import java.util.Map;
import java.util.TreeMap;


public class ParamterUtil {

    /**
     * 获取键值对拼接字符串
     * @param parmMap
     * @return
     */
    public static String getParamtersString(TreeMap<String, String> parmMap) {

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : parmMap.entrySet()) {
            String key = entry.getKey();
            //当jsonp跨域访问时会出现该参数
            if (TextUtils.equals("SIGN", key) || "callback".equalsIgnoreCase( key)) {
                continue;
            }
            //如果值为空不参与拼接
            if (TextUtils.isEmpty(parmMap.get(key))) {
                continue;
            }
            builder.append(key + "=" + parmMap.get(key) + "&");
        }
        builder.setLength(builder.length()-1);
        return builder.toString();
    }
}
