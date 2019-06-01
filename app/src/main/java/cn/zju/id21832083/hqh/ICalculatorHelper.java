package cn.zju.id21832083.hqh;

import android.view.View;

/**
 * Created by hongqianhui on 2019/6/1.
 */

public interface ICalculatorHelper<T extends View> {

    /**
     * 注入视图
     * @param view
     */
    void attach(T view);

    void init(T view);
}
