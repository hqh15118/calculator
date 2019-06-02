package cn.zju.id21832083.hqh.util;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import cn.zju.id21832083.hqh.R;

/**
 * Created by hongganhui on 2018/8/24.
 */

public abstract class BaseDialog extends Dialog {

    public View contentView;

    public BaseDialog(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init();
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    private void init() {
        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        if(getWidth() <= 0)
            lp.width = (int)getWidth();
        else
            lp.width = (int)(ScreenUtils.getScreenWidth(getContext()) * getWidth());
        if(getHeight() <= 0)
            lp.height = (int)getHeight();
        else
            lp.height = (int)(ScreenUtils.getScreenWidth(getContext()) * getHeight());
        lp.gravity = getGravity();
        window.setAttributes(lp);
        window.setBackgroundDrawableResource(R.color.transparent);
        window.setWindowAnimations(getAnimation());
        contentView = LayoutInflater.from(getContext()).inflate(getLayout(),null);
        window.setContentView(contentView);
        initView();
    }

    protected abstract void initView();

    public abstract int getLayout();

    public abstract int getHeight();

    public abstract float getWidth();

    public abstract int getGravity();

    public abstract int getAnimation();
}
