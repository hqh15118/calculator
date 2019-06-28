package cn.zju.id21832083.hqh;

import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class WeiboMainActivity extends AppCompatActivity {


    private ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_mine:
                    viewPager.setCurrentItem(0,false);
                    return true;
                case R.id.navigation_add:
                    viewPager.setCurrentItem(1,false);
                    return true;
                case R.id.navigation_notifications:
                    viewPager.setCurrentItem(2,false);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager = findViewById(R.id.weibo_main_vp);
        LayoutInflater inflater=getLayoutInflater();
        View mine = inflater.inflate(R.layout.weibo_mina,null);
        View add = inflater.inflate(R.layout.weibo_add,null);
        View notification = inflater.inflate(R.layout.weibo_notification,null);

        viewPager.setAdapter(new ViewPagerAdapter(Arrays.asList(mine,add,notification)));
    }


    private static class ViewPagerAdapter extends PagerAdapter{

        private List<View> viewList;

        ViewPagerAdapter(List<View> viewList){
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }
    }

}
