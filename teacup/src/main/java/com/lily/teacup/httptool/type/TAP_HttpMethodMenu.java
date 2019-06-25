package com.lily.teacup.httptool.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;

public class TAP_HttpMethodMenu {
    public static final int GET_REQUEST=0;
    public static final int POST_REQUEST_JSON=1;
    public static final int POST_REQUEST_XML_FORM=2;

    @IntDef(flag = true,value = {GET_REQUEST,POST_REQUEST_JSON,POST_REQUEST_XML_FORM})
    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Model{

    }

    public @Model int value=getValue();

    public void setValue(@Model int value){
        this.value=value;
    }

    @Model
    public int getValue(){
        return value;
    }
}
