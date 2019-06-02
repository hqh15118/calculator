package cn.zju.id21832083.hqh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hongqianhui on 2019/6/2.
 *
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<String> mainStrings;

    private MainBeanCheckBoxEnableListener checkedChangeListener;

    public RecycleViewAdapter(List<String> mainStrings,
                              MainBeanCheckBoxEnableListener mainBeanCheckBoxEnableListener){
        this.mainStrings = mainStrings;
        this.checkedChangeListener = mainBeanCheckBoxEnableListener;

    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView content;
        public CheckBox enable;

        public CustomViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.tv);
            enable = itemView.findViewById(R.id.cb);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choice_item,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CustomViewHolder cvh = ((CustomViewHolder) holder);
        String content = mainStrings.get(position);
        cvh.content.setText(content);
        if (checkedChangeListener!=null) {
            cvh.enable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    checkedChangeListener.checkStateChange(b,
                            mainStrings.get(cvh.getAdapterPosition()),cvh.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mainStrings.size();
    }


    public interface MainBeanCheckBoxEnableListener{
        void checkStateChange(boolean enable,String content,int position);
    }
}
