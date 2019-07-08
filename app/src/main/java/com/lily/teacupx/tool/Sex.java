package com.lily.teacupx.tool;

import androidx.annotation.IntDef;
import androidx.transition.Visibility;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lily
 * @date 2019/7/8 0008.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */
public class Sex {
    public static final int MAN=0;
    public static final int WOMAN=1;

    @IntDef(flag = true,value = {MAN,WOMAN})
    @Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender{

    }

    private @Gender int value=getValue();

    public void setValue(@Gender int value){
        this.value=value;
    }

    public @Gender int getValue(){
        return value;
    }


}
