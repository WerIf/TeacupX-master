package com.lily.teacup.httptool;

import com.lily.teacup.base_class.TAPCreateObjectManager;
import com.lily.teacup.base_class.TAPHttp;
import com.lily.teacup.httptool.type.TAP_HttpMethodMenu;

public class HttpRequestManager {

    public TAPHttp obtainInstance(TAP_HttpMethodMenu methodMenu) {
        return TAPCreateObjectManager.createHttpObject(methodMenu);
    }
}
