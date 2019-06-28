package cn.zju.id21832083.hqh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.codec.binary.StringUtils;

import java.util.List;

import cn.iipc.android.tweetlib.Status;
import cn.iipc.android.tweetlib.YambaClient;
import cn.iipc.android.tweetlib.YambaClientException;
import cn.zju.id21832083.hqh.util.CommonUtil;

public class WeiboLoginActivity extends AppCompatActivity {

    private EditText userName,password;
    private static final String TAG = "WeiboLoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weibo_login_activity);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(loginRequest);

        userName = findViewById(R.id.et_user);
        password = findViewById(R.id.et_password);
    }



    private View.OnClickListener loginRequest = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if (TextUtils.isEmpty(userName.getText().toString())){
                CommonUtil.showToast(view,"用户名为空");
                return;
            }
             if (TextUtils.isEmpty(password.getText().toString())){
                 CommonUtil.showToast(view,"密码为空");
                 return;
             }
             login(userName.getText().toString(),password.getText().toString());
        }
    };

    private void login(String name, String password) {
        YambaClient cloud = new YambaClient(name, password);
        try {
            List<Status> timeline = cloud.getTimeline(20);
        } catch (YambaClientException e) {
            CommonUtil.showToast(userName,"登录失败，未知错误！");
        }
        startActivity(new Intent(this,WeiboMainActivity.class));
    }
}
