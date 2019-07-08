package com.lily.teacupx.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lily.teacup.basicclass.BaseBean;

import java.util.List;

/**
 * @author Lily
 * @date 2019/7/8 0008.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */
public class TpxAdapter extends RecyclerView.Adapter<TpxViewHolder> {

    private final List<BaseBean> beanList;

    public TpxAdapter(List<BaseBean> baseBeans){
        this.beanList=baseBeans;
    }

    @NonNull
    @Override
    public TpxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TpxViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }
}
