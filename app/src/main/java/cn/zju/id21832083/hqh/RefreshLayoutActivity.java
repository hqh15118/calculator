package cn.zju.id21832083.hqh;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

public class RefreshLayoutActivity extends AppCompatActivity {

    private static final String TAG = "refresh_layout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_layout);
        final RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.d(TAG,"more");
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.d(TAG,"refresh");
            }
        });
    }
}
