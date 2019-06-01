package cn.zju.id21832083.hqh.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by hongqianhui on 2019/6/2.
 */

public class CommonUtil {
    private static Toast toast;
    @SuppressLint("ShowToast")
    public static synchronized void showToast(Context context, String text){
        if (toast == null){
            toast = Toast.makeText(context,text,Toast.LENGTH_SHORT);
        }else{
            toast.setText(text);
        }
        toast.show();
    }
}
