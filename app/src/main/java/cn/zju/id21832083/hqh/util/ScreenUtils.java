package cn.zju.id21832083.hqh.util;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author Weimore
 *         2018/4/4.
 *         description:
 */

public class ScreenUtils {
    /**
     * 得到屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 得到屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 把dp转换成px
     */
    public static int dp2px(Context context, int dp) {
        int i = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return i;
    }

    /**
     * 把sp转换成px
     */
    public static int sp2px(Context context, int sp) {
        int i = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return i;
    }

}
