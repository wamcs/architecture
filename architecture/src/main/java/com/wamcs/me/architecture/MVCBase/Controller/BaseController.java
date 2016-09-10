package com.wamcs.me.architecture.MVCBase.Controller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.view.WindowManager;

/**
 * Created by kaili on 16/9/5.
 */
public abstract class BaseController {

    private Context mContext;

    BaseController(Context mContext){
        this.mContext = mContext;
    }

    public Context getContext(){
        return mContext;
    }

    public WindowManager getWindowManager(){
        return (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
    }

    public AudioManager getAuidoManager(){
        return (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    public String getString(int resId){
        return mContext.getResources().getString(resId);
    }

    public Drawable getDrawable(int resId){
        return mContext.getResources().getDrawable(resId);
    }

    public Resources getResources(){
        return mContext.getResources();
    }

}
