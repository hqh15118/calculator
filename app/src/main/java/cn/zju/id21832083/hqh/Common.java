package cn.zju.id21832083.hqh;

/**
 * Created by hongqianhui on 2019/6/2.
 *
 */

public class Common {

    private static boolean shake = false;

    public static boolean isShake() {
        return shake;
    }

    public static void setShake(boolean shake) {
        Common.shake = shake;
    }
}
