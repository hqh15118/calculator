package cn.zju.id21832083.hqh.util;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;

/**
 * Created by hongqianhui on 2019/6/2.
 */


public class VibratorUtil {

    /**
     * final Activity activity ：调用该方法的Activity实例 long milliseconds ：震动的时长，单位是毫秒
     * long[] pattern ：自定义震动模式 。数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长。。。]时长的单位是毫秒
     * boolean isRepeat ： 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     */

    public static void Vibrate(final Context context, long milliseconds) {
        Vibrator vib = (Vibrator) context
                .getSystemService(Service.VIBRATOR_SERVICE);
        if (vib!=null)
            vib.vibrate(milliseconds);
    }

    public static void Vibrate(final Context context, long[] pattern,
                               boolean isRepeat) {
        Vibrator vib = (Vibrator) context
                .getSystemService(Service.VIBRATOR_SERVICE);
        if (vib!=null)
            vib.vibrate(pattern, isRepeat ? 1 : -1);
    }
}
