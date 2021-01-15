package com.example.onlineshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPreferencesUtil {
    private static SharedPreferencesUtil sharedPreferencesUtil;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String FILENAME = "localdata";


    private SharedPreferencesUtil(Context context){
        sharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    // 要求线程安全
    public static SharedPreferencesUtil getInstance(Context context){
        if(sharedPreferencesUtil==null){
            synchronized (SharedPreferencesUtil.class){
                if (sharedPreferencesUtil==null){
                    sharedPreferencesUtil = new SharedPreferencesUtil(context);
                }
            }
        }
        return sharedPreferencesUtil;
    }

    // 存储数据
    public void putBoolean(String key, boolean value){
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void putObject(String key, Object value){
        Gson gson = new Gson();
        String str = gson.toJson(value);
        editor.putString(key, str);
        editor.commit();
    }

    // 读取数据
    public boolean readBoolean(String key){
        return sharedPreferences.getBoolean(key, false);
    }

    public Object readObject(String key, Class clazz){
        String str = sharedPreferences.getString(key, "");
        Gson gson = new Gson();
        return gson.fromJson(str, clazz);
    }

    // 删除数据
    public void delete(String key){
        editor.remove(key).commit();
    }

    public void clean(){
        editor.clear().commit();
    }

}
