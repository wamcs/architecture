package com.wamcs.me.architecture.Base;

import android.content.Context;
import android.content.res.Resources;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/6.
 */
public class AppData {

    private static Context sContext;

    public static void init(Context context){
        sContext = context.getApplicationContext();
    }

    public static Resources getResources(){
        return sContext.getResources();
    }

    public static Context getContext(){
        return sContext;
    }


}
