package cn.zju.id21832083.hqh;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import cn.zju.id21832083.hqh.util.BaseDialog;

/**
 * Created by hongqianhui on 2019/6/2.
 *
 */

public class MainDialog extends BaseDialog {
    private static final String TAG = "MainDialog";


    public MainDialog(@NonNull Context context) {
        super(context);
    }

    public MainDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MainDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void initView() {
        RecyclerView rv = findViewById(R.id.rv_choices);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        List<String> items = new ArrayList<String>(){
            {
                add("shake");
            }
        };
        rv.setAdapter(new RecycleViewAdapter(items,
                new RecycleViewAdapter.MainBeanCheckBoxEnableListener(){

                    @Override
                    public void checkStateChange(boolean enable, String content, int position) {
                        switch (content){
                            case "shake":Common.setShake(enable);break;
                        }
                    }
                }));
    }

    @Override
    public int getLayout() {
        return R.layout.main_dialog;
    }

    @Override
    public int getHeight() {
        return 1;
    }

    @Override
    public float getWidth() {
        return 0.9f;
    }

    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }

    @Override
    public int getAnimation() {
        return R.style.CenterDialog;
    }
}
