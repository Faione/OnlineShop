package com.example.onlineshop.common;

/**
 * 返回给前端的统一类
 */

public class ServerResponse<T> {

    private int status; // 0：状态调用成功

    private T data; // status=0，将返回数据封装到data中

    private String msg; //提示信息

    public ServerResponse(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
