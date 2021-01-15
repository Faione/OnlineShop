package com.example.onlineshop.ui.load;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import com.example.onlineshop.R;
import com.example.onlineshop.common.Const;
import com.example.onlineshop.ui.home.HomeActivity;
import com.example.onlineshop.ui.login.LoginActivity;
import com.example.onlineshop.utils.SharedPreferencesUtil;





public class LoadActivity extends AppCompatActivity {
    private boolean Login;
    private SharedPreferencesUtil sharedPreferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        sharedPreferencesUtil = SharedPreferencesUtil.getInstance(this);


        new Thread(){
            @Override
            public void run() {
                super.run();
                Login = sharedPreferencesUtil.readBoolean(Const.SHARED_LOGIN);
                // 4.模拟读取的时间
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = Login? new Intent(getApplicationContext(), HomeActivity.class): new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();

    }


}
