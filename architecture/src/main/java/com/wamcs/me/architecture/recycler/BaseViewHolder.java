package com.wamcs.me.architecture.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/7.
 */
public abstract class BaseViewHolder<Bean> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public BaseViewHolder(ViewGroup viewGroup,int layoutId){
        super(fromResLayout(viewGroup,layoutId));
    }

    public static View fromResLayout(ViewGroup viewGroup,int resId){
       return LayoutInflater.from(viewGroup.getContext()).inflate(resId,viewGroup,false);
    }

    public Context getContext(){
        return itemView.getContext();
    }

    public abstract void bindData(Bean data);
}
