package com.lily.teacup.md5;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;


/**
 * Md5 加密类
 */
public class Md5Util {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5(TreeMap<String, String> parmMap) {
        String paramterString = ParamterUtil.getParamtersString(parmMap);
        if (TextUtils.isEmpty(paramterString)) {
            return "请求参数拼接字符串为空";
        }

        try {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(paramterString.getBytes("UTF-8"));

            byte[] bytes = msgDigest.digest();
            int l = bytes.length;
            char[] out = new char[l << 1];
            // two characters form the hex value.
            for (int i = 0, j = 0; i < l; i++) {
                out[j++] = DIGITS[(0xF0 & bytes[i]) >>> 4];
                out[j++] = DIGITS[0x0F & bytes[i]];
            }
            return new String(out).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("System doesn't support MD5 algorithm.");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("System doesn't support your  EncodingException.");
        }
    }
}
