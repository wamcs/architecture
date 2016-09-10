package com.wamcs.me.architecture.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import com.wamcs.me.architecture.Base.AppData;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/7.
 */
public class Displayutil {

    public static DisplayMetrics display(){
        if (AppData.getContext() == null) {
            return null;
        }
        return AppData.getContext().getResources().getDisplayMetrics();
    }

    public static int width(){
        DisplayMetrics display = display();
        if (display == null) {
            return 0;
        }
        return display.widthPixels;
    }

    public static int height(){
        DisplayMetrics display = display();
        if (display == null){
            return 0;
        }
        return display.heightPixels;
    }

    public static int dip2px(float dp) {
        DisplayMetrics display = display();
        if (display == null) {
            return 0;
        }
        final float scale = display.density;
        return (int) (dp * scale + 0.5f);
    }

    public static float px2dip(int px) {
        DisplayMetrics display = display();
        if (display == null) {
            return 0;
        }
        return px / display.density;
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

}
