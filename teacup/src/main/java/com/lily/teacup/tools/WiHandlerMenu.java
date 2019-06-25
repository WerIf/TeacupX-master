package com.lily.teacup.tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import androidx.annotation.IntDef;

/**
 * 自定义枚举
 */
public class WiHandlerMenu {
    public static final int DELAY_JUMP=0;
    public static final int DELAY_LOGIN=1;

    @IntDef(flag = true,value = {DELAY_JUMP,DELAY_LOGIN})
    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Model{

    }

    private @Model int value=getValue();

    public void setValue(@Model int value){
        this.value=value;
    }

    @Model
    public int getValue(){
        return value;
    }
}
