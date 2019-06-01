package cn.zju.id21832083.hqh;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.iipc.android.tweetlib.SubmitProgram;
import cn.zju.id21832083.hqh.layout.CustomDrawLayout;

public class CalculatorActivity extends AppCompatActivity {


    private EditText calEv;
    private Button btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0;
    private Button btnAdd,btnSub,btnDiv,btnMul,btnCE,btnC,btnDot,btnEqual,btnBack,btnAddorSub;
    private Button btnMenu;
    private TextView tvMenuItem;
    private CustomDrawLayout dlMenu;
    private LinearLayout menuItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        initComponment();

        addMenuItem(new String[]{"STANDARD","PROGRAMMER"});

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlMenu.openDrawer(menuItems);
            }
        });

    }

    private void initComponment() {
        calEv = findViewById(R.id.cal_ev);
        btn_1 = findViewById(R.id.cal_btn_1);
        btn_2 = findViewById(R.id.cal_btn_2);
        btn_3 = findViewById(R.id.cal_btn_3);
        btn_4 = findViewById(R.id.cal_btn_4);
        btn_5 = findViewById(R.id.cal_btn_5);
        btn_6 = findViewById(R.id.cal_btn_6);
        btn_7 = findViewById(R.id.cal_btn_7);
        btn_8 = findViewById(R.id.cal_btn_8);
        btn_9 = findViewById(R.id.cal_btn_9);
        btn_0 = findViewById(R.id.cal_btn_0);

        btnAdd = findViewById(R.id.cal_btn_add);
        btnSub = findViewById(R.id.cal_btn_del);
        btnDiv = findViewById(R.id.cal_btn_div);
        btnMul = findViewById(R.id.cal_btn_mul);

        btnCE = findViewById(R.id.cal_btn_ce);
        btnC = findViewById(R.id.cal_btn_c);
        btnDot = findViewById(R.id.cal_btn_dot);
        btnEqual = findViewById(R.id.cal_btn_equal);
        btnBack = findViewById(R.id.cal_btn_back);
        btnAddorSub = findViewById(R.id.cal_btn_add_del);

        btnMenu = findViewById(R.id.btn_menu);
        tvMenuItem = findViewById(R.id.tvMenuItem);

        dlMenu = findViewById(R.id.cal_dl);

        menuItems = findViewById(R.id.cal_menu_items);
    }


    private void addMenuItem(String[] menuItemNames){
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (String menuItemName : menuItemNames) {
            Button btn = new Button(this);
            btn.setLayoutParams(layoutParams);
            btn.setText(menuItemName);
            btn.setTextColor(getResources().getColor(R.color.black));
            btn.setOnClickListener(menuItemListener);
            btn.setBackground(getResources().getDrawable(R.drawable.btn_item));
            menuItems.addView(btn);
        }
    }

    private View.OnClickListener menuItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String menuName = ((Button) view).getText().toString();
            switch (menuName){
                case "STANDARD":break;
                case "PROGRAMMER" : break;
            }
        }
    };
}
