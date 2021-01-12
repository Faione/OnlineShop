package com.example.onlineshop.data;

import android.app.Activity;
import android.widget.Toast;

import com.example.onlineshop.common.ServerResponse;
import com.example.onlineshop.data.model.UserVO;
import com.example.onlineshop.ui.login.LoginActivity;
import com.example.onlineshop.utils.OkHttpCallback;
import com.example.onlineshop.utils.OkHttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import static com.example.onlineshop.common.Const.SERVER;
import static com.example.onlineshop.common.Const.SERVER_PORT;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<UserVO> login(String username, String password) {
        try {
            // 定义一个返回对象
            OkHttpCallback okHttpCallback = new OkHttpCallback(){
                @Override
                public void onFinish(String status, String msg) {
                    // 生产者-消费者模型，当执行到onFinish，即取到数据时，唤醒一个等待中的线程
                    synchronized (this){
                        this.notify();
                    }
                }
            };

            // 发出请求
            OkHttpUtil.get("http://"+ SERVER +":"+ SERVER_PORT +"/portal/user/login.do?username=" + username + "&password=" + password, okHttpCallback);


            // 若先运行到此，且数据未取到时，此线程进入等待
            synchronized (okHttpCallback){
                if(okHttpCallback.getResult()==null){
                    okHttpCallback.wait();
                }
            }

            // 解析String为类
            Gson gson = new Gson();
            ServerResponse<UserVO> serverResponse = gson.fromJson(okHttpCallback.getResult(), new TypeToken<ServerResponse<UserVO>>() {
            }.getType());

            // 登录成功
            if (serverResponse.getStatus() == 0) {
                UserVO loggedInUser = serverResponse.getData();
                System.out.print("success");

                return new Result.Success<>(loggedInUser);
                // 登录失败
            } else {

                return new Result.Error(serverResponse.getMsg());
            }
        }
        catch (Exception e){
            // 其他错误
            return new Result.Error(e.toString());
        }

    }

    public void logout() {
        // TODO: revoke authentication
    }
}
