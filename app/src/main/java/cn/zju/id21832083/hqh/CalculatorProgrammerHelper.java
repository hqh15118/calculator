package cn.zju.id21832083.hqh;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.zju.id21832083.hqh.util.CommonUtil;
import cn.zju.id21832083.hqh.util.VibratorUtil;

/**
 * Created by hongqianhui on 2019/6/1.
 */

public class CalculatorProgrammerHelper extends BaseCalculatorHelper<RelativeLayout> {

    private static final String TAG = "ProgrammerHelper";

    private EditText calPEv;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    private TextView tvHex,tvBin,tvOct,tvDec;
    private Button btnA,btnB,btnC,btnD,btnE,btnF;
    private TextView rowHex,rowDec,rowBin,rowOct;
    private List<TextView> rowTextViews = new ArrayList<>(4);
    private List<Button> keyboardBtns = new ArrayList<>(16);
    private RelativeLayout rlBin,rlHex,rlDec,rlOct;
    private Button btnClear;
    private int lastRadix = KEY_BOARD_BIN;          //初始为二进制
    private boolean textChange = true;
    private Button btnBack;

    private static final int KEY_BOARD_BIN = 2,
                             KEY_BOARD_HEX = 16,
                             KEY_BOARD_OCT = 8,
                             KEY_BOARD_DEC = 10;

    private StringBuilder programExpressionBuilder = new StringBuilder(50);

    @Override
    public void init(RelativeLayout view) {
        calPEv = getComponmentById(R.id.calp_ev,EditText.class);
        btnClear = getComponmentById(R.id.calp_clear,Button.class);
        btnBack = getComponmentById(R.id.calp_back,Button.class);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shake();
                setExpression("0");
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shake();
                delExpression();
            }
        });
        //init btn
        initComponments(
                new int[]{R.id.calp_btn_0,R.id.calp_btn_1, R.id.calp_btn_2, R.id.calp_btn_3, R.id.calp_btn_4, R.id.calp_btn_5, R.id.calp_btn_6
                        , R.id.calp_btn_7, R.id.calp_btn_8, R.id.calp_btn_9,
                        R.id.calp_btn_a, R.id.calp_btn_b, R.id.calp_btn_c,R.id.calp_btn_d,R.id.calp_btn_e, R.id.calp_btn_f},
                Button.class, new addCallback<Button>() {
                    @Override
                    public void callback(Button t) {
                        t.setOnClickListener(btnListener);
                        keyboardBtns.add(t);
                    }
                },
                new String[]{"btn0", "btn1", "btn2", "btn3", "btn4", "btn5",
                        "btn6", "btn7", "btn8", "btn9", "btnA", "btnB", "btnC", "btnD"
                        , "btnE", "btnF"}
        );

        //init row tv
        initComponments(
                new int[]{R.id.row_hex, R.id.row_oct, R.id.row_dec, R.id.row_bin},
                TextView.class, new addCallback<TextView>() {
                    @Override
                    public void callback(TextView t) {
                        rowTextViews.add(t);
                    }
                },new String[]{"rowHex", "rowOct", "rowDec", "rowBin"}
        );

        initComponments(
                new int[]{R.id.calp_tv_hex, R.id.calp_tv_oct, R.id.calp_tv_dec, R.id.calp_tv_bin},
                TextView.class, null,new String[]{"tvHex", "tvOct", "tvDec", "tvBin"}
        );

        //init rl
        initComponments(new int[]{R.id.rl_hex, R.id.rl_bin, R.id.rl_dec, R.id.rl_oct},
                 RelativeLayout.class, new addCallback<RelativeLayout>() {
                    @Override
                    public void callback(RelativeLayout t) {
                        t.setOnClickListener(rlListener);
                    }
                },new String[]{"rlHex", "rlBin", "rlDec", "rlOct"});

        calPEv.setText("0");

        setKeyboardStyle(KEY_BOARD_BIN);

        calPEv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //只有是setExpression方法改变才修改
                if (textChange) {
                    radixChange(lastRadix, lastRadix, false);
                }
            }
        });
    }

    /**
     * 这里只能用反射的方法进行修改
     * @param ids
     * @param type
     * @param callback
     * @param ts
     * @param <T>
     */
    private <T> void initComponments(int[] ids,Class<T> type,addCallback<T> callback
                                     ,String[] ts)  {
        int i = ids.length;
        Class clazz = this.getClass();
        for (int j = 0; j < i; j++) {
            T t = getComponmentById(ids[j],type);
            Field field = null;
            try {
                field = clazz.getDeclaredField(ts[j]);
                field.set(this,t);
                if (callback!=null){
                    callback.callback(t);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }


    private View.OnClickListener btnListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Button clickButton = ((Button) view);
            shake();
            appendExpression(clickButton.getText().toString());
        }
    };

    private View.OnClickListener rlListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int keyBoardType = -1;
            switch (id){
                case R.id.rl_bin:setTargetRowTextVisible(rowBin);
                keyBoardType = KEY_BOARD_BIN;
                break;
                case R.id.rl_dec:setTargetRowTextVisible(rowDec);
                keyBoardType = KEY_BOARD_DEC;
                break;
                case R.id.rl_hex:setTargetRowTextVisible(rowHex);
                keyBoardType = KEY_BOARD_HEX;
                break;
                case R.id.rl_oct:setTargetRowTextVisible(rowOct);
                keyBoardType = KEY_BOARD_OCT;
                break;
                default:
                    Log.e(TAG,"program error");
            }

            if (keyBoardType >= 0) {
                setKeyboardStyle(keyBoardType);
                radixChange(lastRadix,keyBoardType,true);
                lastRadix = keyBoardType;
            }
            shake();
        }
    };

    private void radixChange(int fromRadix,int toRadix,boolean changeEdit) {
        try {
            long i = Long.parseLong(calPEv.getText().toString(), fromRadix);
            setTextViewContent(tvBin, Long.toBinaryString(i));
            setTextViewContent(tvDec, String.valueOf(i));
            setTextViewContent(tvHex, Long.toHexString(i).toUpperCase());
            setTextViewContent(tvOct, Long.toOctalString(i));
            if (changeEdit) {
                textChange = false;
                setExpression(Long.toString(i, toRadix));
            }
            textChange = true;
        }catch (NumberFormatException e){
            CommonUtil.showToast(calPEv,"数据长度超过限制");
        }
    }

    private void setTextViewContent(TextView tv,String content){
        tv.setText(content);
    }

    /**
     * 设置选中的tv可见
     * @param tv 可见的tv
     */
    private void setTargetRowTextVisible(TextView tv){
        for (TextView rowTextView : rowTextViews) {
            if (rowTextView == tv){
                rowTextView.setVisibility(View.VISIBLE);
            }else{
                rowTextView.setVisibility(View.INVISIBLE);
            }
        }
    }


    /**
     * 'F' = 70 ...
     * '7' = 55
     * '1' = 49
     * '9' = 57
     * @param keyboardStyle
     */
    private void setKeyboardStyle(int keyboardStyle){
        int maxChar = -1;
        if (keyboardStyle == KEY_BOARD_BIN){
            maxChar = 49;
        }else if (keyboardStyle == KEY_BOARD_DEC){
            maxChar = 57;
        }else if(keyboardStyle == KEY_BOARD_HEX){
            maxChar = 70;
        }else if(keyboardStyle == KEY_BOARD_OCT){
            maxChar = 55;
        }
        for (Button keyboardBtn : keyboardBtns) {
            int btnFigure = keyboardBtn.getText().charAt(0);
            if (!(btnFigure<=maxChar)){
                keyboardBtn.setEnabled(false);
            }else{
                keyboardBtn.setEnabled(true);
            }
        }
    }


    private void appendExpression(String text){
        if ("0".equals(calPEv.getText().toString())){
            setExpression(text);
        }else {
            String upperText = text.toUpperCase();
            programExpressionBuilder.append(upperText);
            calPEv.setText(programExpressionBuilder.toString());
        }
    }

    private void setExpression(String text){
        String upperText = text.toUpperCase();
        programExpressionBuilder.delete(0,programExpressionBuilder.length());
        programExpressionBuilder.append(upperText);
        calPEv.setText(upperText);
    }

    private void delExpression(){
        if (programExpressionBuilder.length() > 1) {
            programExpressionBuilder.delete(programExpressionBuilder.length() - 1, programExpressionBuilder.length());
            calPEv.setText(programExpressionBuilder.toString());
        }else{
            setExpression("0");
        }
    }


    private void shake(){
        if (Common.isShake()){
            VibratorUtil.Vibrate(context(),100);
        }
    }

    private interface addCallback<S>{
        void callback(S t);
    }
}
