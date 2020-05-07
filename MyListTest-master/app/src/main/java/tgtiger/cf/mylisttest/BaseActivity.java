package tgtiger.cf.mylisttest;

import android.support.v7.app.AppCompatActivity;

import tgtiger.cf.mylisttest.util.SharedPreferencesUtil;

public class BaseActivity extends AppCompatActivity {
    protected SharedPreferencesUtil sp;

    //重写了setContentView方法，在子类调用了setContentView设置布局
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        //配置文件
        sp = SharedPreferencesUtil.getInstance(getApplicationContext());
    }
}
