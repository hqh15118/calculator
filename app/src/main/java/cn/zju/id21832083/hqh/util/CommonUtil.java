package cn.zju.id21832083.hqh.util;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Toast;

/**
 * Created by hongqianhui on 2019/6/2.
 */

public class CommonUtil {
    private static Toast toast;
    @SuppressLint("ShowToast")
    public static void showToast(final View view, final String text){
        view.post(new Runnable() {
            @Override
            public void run() {
                if (toast == null){
                    toast = Toast.makeText(view.getContext(),text,Toast.LENGTH_SHORT);
                }else{
                    toast.setText(text);
                }
                toast.show();
            }
        });
    }
}
