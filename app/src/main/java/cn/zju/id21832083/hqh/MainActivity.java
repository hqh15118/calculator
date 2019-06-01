package cn.zju.id21832083.hqh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.iipc.android.tweetlib.SubmitProgram;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_main = findViewById(R.id.btn_submit_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SubmitProgram().doSubmit(MainActivity.this,"A1");
            }
        });
    }
}
