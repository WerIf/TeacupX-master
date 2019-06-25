package com.lily.teacup.base_class;

import com.lily.teacup.httptool.operation.GetHttpUrlConnection;
import com.lily.teacup.httptool.operation.JPostHttpUrlConnection;
import com.lily.teacup.httptool.operation.XPostHttpUrlConnection;
import com.lily.teacup.httptool.type.TAP_HttpMethodMenu;

public class TAPCreateObjectManager {
    /**
     *  创建网络请求类
     * @param methodMenu
     * @return
     */
    public static TAPHttp createHttpObject(TAP_HttpMethodMenu methodMenu) {
        TAPHttp tapHttp = null;
        switch (methodMenu.getValue()) {
            case TAP_HttpMethodMenu.GET_REQUEST:
                tapHttp = new GetHttpUrlConnection();
                break;
            case TAP_HttpMethodMenu.POST_REQUEST_JSON:
                tapHttp = new JPostHttpUrlConnection();
                break;
            case TAP_HttpMethodMenu.POST_REQUEST_XML_FORM:
                tapHttp = new XPostHttpUrlConnection();
                break;
            default:
                tapHttp = null;
                break;
        }
        return tapHttp;
    }
}
