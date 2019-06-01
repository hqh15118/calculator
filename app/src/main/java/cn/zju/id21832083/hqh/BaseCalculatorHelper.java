package cn.zju.id21832083.hqh;

import android.view.View;

/**
 * Created by hongqianhui on 2019/6/1.
 */

public abstract class BaseCalculatorHelper<T extends View> implements ICalculatorHelper<T>{
    protected T rootView;

    @Override
    public void attach(T view) {
        rootView = view;
        init(view);
    }


    protected  <U> U getComponmentById(int id, Class<U> componmentType) {
        return componmentType.cast(rootView.findViewById(id));
    }
}
