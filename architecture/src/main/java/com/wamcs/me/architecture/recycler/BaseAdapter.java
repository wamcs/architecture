package com.wamcs.me.architecture.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/7.
 */
public abstract class BaseAdapter<Data> extends RecyclerView.Adapter<BaseViewHolder<Data>> {

    private List<Data> dataList;

    @Override
    public void onBindViewHolder(BaseViewHolder<Data> holder, int position) {
        holder.bindData(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Data> data){
        if (null == data){
            Log.e("BaseAdapter","the list of data mustn't be null");
            return;
        }
        if (null == dataList){
            setData(data);
            return;
        }
        int i = dataList.size();
        dataList.addAll(data);
        notifyItemInserted(i);
    }

    public void setData(List<Data> data){
        if (null == data){
            Log.e("BaseAdapter","the list of data mustn't be null");
            return;
        }
        dataList = data;
        notifyDataSetChanged();
    }

    public List<Data> getData(){
        return dataList;
    }
}
