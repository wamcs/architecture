package com.wamcs.me.architecture.MVCBase.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.wamcs.me.architecture.R;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/5.
 */
public abstract class BaseActivityController extends BaseController{

    private View mRootView;
    private AppCompatActivity mActivity;
    private Toolbar mToolbar;

    public BaseActivityController(AppCompatActivity activity, View view) {
        super(activity);
        mActivity = activity;
        mRootView = view;
        if (isToolBarEnable()){
            mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        }
        baseInit();
    }

    public View getmRootView(){
        return mRootView;
    }

    public AppCompatActivity getActivity(){
        return mActivity;
    }

    private void baseInit(){
        if (isToolBarEnable()){
            initToolbar();
        }
    }

    protected boolean isToolBarEnable(){return false;}

    private void initToolbar(){
        mActivity.setSupportActionBar(mToolbar);
        mToolbar.setContentInsetsAbsolute(0,0);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeAsUpIndicator(null);
        }
    }

    public void onStart(){}

    public void onResume(){}

    public void onPause(){}

    public void onStop(){}

    public void onDestory(){}

    public void onSaveInstanceState(Bundle outState) {}

    public void onActivityResult(int requestCode, int resultCode, Intent data){}

    public void overridePendingTransition(int enterAnim, int exitAnim) {
        mActivity.overridePendingTransition(enterAnim, exitAnim);
    }

    public void startActivity(Intent intent){
        mActivity.startActivity(intent);
    }

    public View findViewById(int resId){
        return mActivity.findViewById(resId);
    }

    public ActionBar getSupportActionBar() {
        return mActivity.getSupportActionBar();
    }

    public boolean onBackPressed(){
        return false;
    }

    public FragmentManager getFragmentManager(){
        return mActivity.getSupportFragmentManager();
    }

    public Intent getIntent(){
        return mActivity.getIntent();
    }

    public void onReqeustPermissionResult(int requestCode, String[] permissions, int[] grantResults){

    }

}
