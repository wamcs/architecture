package com.wamcs.me.architecture.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wamcs.me.architecture.Base.AppData;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/6.
 */
public class Inflater {

    public static View inflater(int resId, ViewGroup parent, boolean attach){
        return LayoutInflater.from(AppData.getContext()).inflate(resId, parent, attach);
    }

}
