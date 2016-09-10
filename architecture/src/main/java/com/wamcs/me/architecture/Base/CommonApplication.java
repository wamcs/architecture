package com.wamcs.me.architecture.Base;

import android.app.Application;

import com.wamcs.me.architecture.utils.DircUtil;


/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/6.
 */
public class CommonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppData.init(this);

        try {
            DircUtil.init(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
