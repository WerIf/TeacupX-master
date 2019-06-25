package com.lily.teacup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TeacupManager<T,A,B,C> {

    private static TeacupManager teacupManager;

    private TeacupManager(){}

    public static TeacupManager getInstance(){
        if(null==teacupManager){
            synchronized (TeacupManager.class){
                if(null==teacupManager){
                    teacupManager=new TeacupManager();
                }
            }
        }
        return teacupManager;
    }

    public T getFunctionService(Class<T> tClass){
        try {
            T t = tClass.newInstance();
            return t;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public T getFunctionService(Class<T> tClass,Class<A> param1OfClass,Object... params){
        try {
            Constructor<T> declaredConstructor = tClass.getDeclaredConstructor(param1OfClass);
            T t = declaredConstructor.newInstance(params[0], params[1], params[2]);
            return t;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getFunctionService(Class<T> tClass,Class<A> param1OfClass,Class<B> param2Class,Object... params){
        try {
            Constructor<T> declaredConstructor = tClass.getDeclaredConstructor(param1OfClass, param2Class);
            T t = declaredConstructor.newInstance(params[0], params[1]);
            return t;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T getFunctionService(Class<T> tClass,Class<A> param1OfClass,Class<B> param2Class,Class<C> param3Class,Object... params){
        try {
            Constructor<T> declaredConstructor = tClass.getDeclaredConstructor(param1OfClass, param2Class, param3Class);
            T t = declaredConstructor.newInstance(params[0], params[1], params[2]);
            return t;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
