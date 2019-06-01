package cn.zju.id21832083.hqh;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import cn.zju.id21832083.hqh.layout.CustomDrawLayout;
import cn.zju.id21832083.hqh.util.Calculator;

public class CalculatorActivity extends AppCompatActivity {

    private static final String TAG = "CalculatorActivity";
    private EditText calEv;
    private Button btnMenu;
    private TextView tvMenuItem;
    private CustomDrawLayout dlMenu;
    private LinearLayout menuItems;
    private RelativeLayout rlRoot , calStandard , calProgrammer;

    private StringBuffer calculatorStringBuilder = new StringBuffer(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_standard);

        initComponment();

        addMenuItem(new String[]{"STANDARD","PROGRAMMER"});

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlMenu.openDrawer(menuItems);
            }
        });

        //load view later
        Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() {
            @Override
            public boolean queueIdle() {
                calProgrammer = (RelativeLayout) LayoutInflater.
                        from(CalculatorActivity.this).inflate(getResources().getLayout(R.layout.calculator_programmer),rlRoot,false);
                CalculatorProgrammerHelper calculatorProgrammerHelper = new CalculatorProgrammerHelper();
                calculatorProgrammerHelper.attach(calProgrammer);
                return false;
            }
        });

    }

    private void initComponment() {
        calEv = findViewById(R.id.cal_ev);
        Button btn_1 = findViewById(R.id.cal_btn_1);
        Button btn_2 = findViewById(R.id.cal_btn_2);
        Button btn_3 = findViewById(R.id.cal_btn_3);
        Button btn_4 = findViewById(R.id.cal_btn_4);
        Button btn_5 = findViewById(R.id.cal_btn_5);
        Button btn_6 = findViewById(R.id.cal_btn_6);
        Button btn_7 = findViewById(R.id.cal_btn_7);
        Button btn_8 = findViewById(R.id.cal_btn_8);
        Button btn_9 = findViewById(R.id.cal_btn_9);
        Button btn_0 = findViewById(R.id.cal_btn_0);

        Button btnAdd = findViewById(R.id.cal_btn_add);
        Button btnSub = findViewById(R.id.cal_btn_del);
        Button btnDiv = findViewById(R.id.cal_btn_div);
        Button btnMul = findViewById(R.id.cal_btn_mul);

        Button btnCE = findViewById(R.id.cal_btn_ce);
        Button btnC = findViewById(R.id.cal_btn_c);
        Button btnDot = findViewById(R.id.cal_btn_dot);
        Button btnEqual = findViewById(R.id.cal_btn_equal);
        Button btnBack = findViewById(R.id.cal_btn_back);
        Button btnAddorSub = findViewById(R.id.cal_btn_add_del);

        btnMenu = findViewById(R.id.btn_menu);
        tvMenuItem = findViewById(R.id.tvMenuItem);

        dlMenu = findViewById(R.id.cal_dl);

        menuItems = findViewById(R.id.cal_menu_items);

        rlRoot = findViewById(R.id.cal_root);
        calStandard = findViewById(R.id.cal_standard);

        setCalculateListener(btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,
                btnAdd, btnSub, btnDiv, btnMul, btnCE, btnC, btnDot, btnEqual, btnBack, btnAddorSub);

        appendCalculateExpression("0");
    }

    private void setCalculateListener(Button...buttons) {
        for (Button button : buttons) {
            button.setOnClickListener(calculatorListener);
        }
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
            tvMenuItem.setText(menuName);
            switch (menuName){
                case "STANDARD":
                    rlRoot.removeAllViews();
                    rlRoot.addView(calStandard);
                    break;
                case "PROGRAMMER" :
                    rlRoot.removeAllViews();
                    rlRoot.addView(calProgrammer);
                    break;
            }
            dlMenu.closeDrawer(menuItems);
        }
    };

    private View.OnClickListener calculatorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id){
                case R.id.cal_btn_equal:
                    Log.d(TAG,calculatorStringBuilder.toString());
                    String expression = calculatorStringBuilder.toString().replace("÷","/")
                            .replace("×","*").replace("—","-");
                    double value = Calculator.conversion(expression);
                    String res = String.valueOf(value);
                    if (res.endsWith(".0")){
                        res = res.replace(".0","");
                    }
                    calEv.setText(res);
                    calculatorStringBuilder.delete(0,calculatorStringBuilder.length());
                    calculatorStringBuilder.append(res);
                    break;
                case R.id.cal_btn_ce:
                    calculatorStringBuilder.delete(0,calculatorStringBuilder.length());
                    calEv.setText("0");
                    break;
                case R.id.cal_btn_c:
                    calculatorStringBuilder.delete(0,calculatorStringBuilder.length());
                    calEv.setText("0");
                    break;
                case R.id.cal_btn_add_del:
                    if (!"NaN".equals(calEv.getText().toString())){
                        calculatorStringBuilder.append(new char[]{'-'},0,1);
                        calEv.setText(calculatorStringBuilder.toString());
                    }
                    break;
                case R.id.cal_btn_back:
                    if (calculatorStringBuilder.length() <= 1){
                        calculatorStringBuilder.delete(0, calculatorStringBuilder.length());
                        calEv.setText("0");
                    }else {
                        calculatorStringBuilder.delete(calculatorStringBuilder.length()-1, calculatorStringBuilder.length());
                        calEv.setText(calculatorStringBuilder.toString());
                    }

                    break;
                default:
                    appendCalculateExpression(((Button) view).getText().toString());
                    break;
            }
        }
    };

    private void appendCalculateExpression(String expression){
        calculatorStringBuilder.append(expression);
        calEv.setText(calculatorStringBuilder.toString());
    }




}
