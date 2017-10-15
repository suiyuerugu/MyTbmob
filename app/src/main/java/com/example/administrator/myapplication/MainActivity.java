package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etUserName,etPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"5db4c488cf76efadfaf9c669d898fe97");
        init();

    }
    private void init(){
        etUserName=(EditText) findViewById(R.id.et_user_name);
        etPassword=(EditText)findViewById(R.id.et_password);
        btnLogin=(Button)findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                login();
                break;
        }
    }
    private void login(){
        String strUserName=etUserName.getText().toString();
        String strPassword=etPassword.getText().toString();

//        if (strUserName.equals("abc")&&strPassword.equals("123456")){
//            Toast.makeText(this,"登陆成功！",Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
//        }
        BmobUser bu2 = new BmobUser();
        bu2.setUsername(strUserName);
        bu2.setPassword(strPassword);
        bu2.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this,"登录成功:",Toast.LENGTH_SHORT).show();
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                }else{
                    Toast.makeText(MainActivity.this,"登录失败："+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
