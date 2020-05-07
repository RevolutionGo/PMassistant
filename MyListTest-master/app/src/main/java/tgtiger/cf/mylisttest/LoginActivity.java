package tgtiger.cf.mylisttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.githang.statusbar.StatusBarCompat;

import java.lang.String;

import tgtiger.cf.mylisttest.util.Constants;
import tgtiger.cf.mylisttest.util.RegexUtil;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_username;
    private EditText et_password;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_item);
        StatusBarCompat.setStatusBarColor(this, 0xff669900, false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        bt_login = findViewById(R.id.bt_login);

        Log.i("Activiyt2","onCreate()");

        bt_login.setOnClickListener(this);

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("Activity2","onStart()");
    }

    public void onClick(View view) {
        //若有多个按钮，则可以匹配多个按钮
        switch (view.getId()) {
            case R.id.bt_login:
                login();
                break;
        }
    }

    private void login() {
        //获取用户输入的手机号、密码，做校验
        String username = et_username.getText().toString().trim();
        String password = et_password.getText().toString().trim();

        //判断是否输入了手机号
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, R.string.email_hint, Toast.LENGTH_SHORT).show();
            return;
        }

        //通过正则表达式判断手机号格式是否正确
        if (RegexUtil.isPhone(username)) {
            Toast.makeText(this, R.string.email_error, Toast.LENGTH_SHORT).show();
            return;
        }

        //判断是否输入了密码
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, R.string.password_hint, Toast.LENGTH_SHORT).show();
            return;
        }

        //判断密码长度是否正确
        if (password.length() < 6 || password.length() > 15) {
            Toast.makeText(this, R.string.password_length_error, Toast.LENGTH_SHORT).show();
            return;
        }
        //此处将用户和密码写在本地
        if ((Constants.USERNAME.equals(username) && Constants.PASSWORD.equals(password))) {
            //TODO登录完成后保存一个标志，记住登录状态
            sp.setLogin(true);

            //登录成功，进入首页
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("username",username);
            intent.putExtra("password",password);
            startActivity(intent);

            //关闭当前界面
            finish();
        } else {
            //登录失败提示
            Toast.makeText(this, R.string.username_or_password_error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("Activity2","onRestart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("Activity2","onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("Activity2","onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Activity2","onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Activity2","onDestroy()");
    }
}