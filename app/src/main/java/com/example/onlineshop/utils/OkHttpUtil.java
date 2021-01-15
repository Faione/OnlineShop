package com.example.onlineshop.utils;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    private static final OkHttpClient CLIENT = new OkHttpClient();


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    /**
     * get请求
     * @param url
     * @param callback
     */
    public static void get(String url, OkHttpCallback callback){
        callback.setUrl(url);
        Request request = new Request.Builder().url(url).build();
        CLIENT.newCall(request).enqueue(callback);
    }

    public static void get_ex(String url, OkHttpCallback callback){
        callback.setUrl(url);
        Request request = new Request.Builder().url(url).build();
        CLIENT.newCall(request).enqueue(callback);
    }

    /**
     * post请求
     * @param url
     * @param json
     * @param callback
     */
    public static void post(String url, String json, OkHttpCallback callback){
        callback.setUrl(url);
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).build();
        CLIENT.newCall(request).enqueue(callback);
    }

}
