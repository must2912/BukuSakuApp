package com.stematel.bukusaku.Activity.SplashScreen;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.LogInterceptor;
import com.stematel.bukusaku.Activity.Home.HomeActivity;
import com.stematel.bukusaku.Activity.Login.LoginActivity;
import com.stematel.bukusaku.Model.User.UserResponse;
import com.stematel.bukusaku.R;
import com.stematel.bukusaku.Service.LocalService;

public class SplashActivity extends AppCompatActivity {

    private CountDownTimer mCountDownTimer;
    final static int INTERVAL = 1000; // 1 second
    final static int TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkStartup();
    }

    private void checkLogin() {
        UserResponse player = LocalService.getLogin();
        if(player != null){
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }else{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }

    }

    private void initHawk() {
        Hawk.init(this).setLogInterceptor(new LogInterceptor() {
            @Override
            public void onLog(String message) {
                Log.d("Hawk", message);
            }
        }).build();

    }

    private void checkStartup(){
        mCountDownTimer = new CountDownTimer(TIMEOUT,INTERVAL) {
            @Override
            public void onTick(long l) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },1500);
            }

            @Override
            public void onFinish() {
                initHawk();
                checkLogin();
            }
        }.start();
    }
}
