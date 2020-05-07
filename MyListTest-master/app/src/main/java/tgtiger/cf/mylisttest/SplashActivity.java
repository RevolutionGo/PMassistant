package tgtiger.cf.mylisttest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager;
import tgtiger.cf.mylisttest.util.SharedPreferencesUtil;
import tgtiger.cf.mylisttest.MainActivity;
import tgtiger.cf.mylisttest.R;

public class SplashActivity extends BaseActivity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            next();
        }
    };


    private void  next(){
        Intent intent = null;
        if (sp.isLogin()) {
            //已登录，则跳转到首页
            intent = new Intent(SplashActivity.this, MainActivity.class);
        }else {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
        }

        startActivity(intent);
        //关闭当前界面
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //2s后调用，这里调用是在子线程，不能直接操作UI，要调用handler切换到主线程
                //用多个线程的目的解决，如果有耗时任务，那么就会卡界面
                //而用了多线程后，将耗时任务放到子线程，这样主线程（UI线程）就不会卡住
                handler.sendEmptyMessage(0);
            }
        },2000);
    }
}
