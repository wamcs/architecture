package com.wamcs.me.architecture.MVCBase.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.wamcs.me.architecture.MVCBase.Controller.BaseActivityController;
import com.wamcs.me.architecture.MVCBase.Controller.ControllerHolder;

public abstract class BaseActivity extends AppCompatActivity implements ControllerHolder{

    BaseActivityController activityController;

    @Override
    protected void onStart() {
        super.onStart();
        activityController = getController();
        if (activityController != null){
            activityController.onStart();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (activityController != null){
            activityController.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (activityController != null){
            activityController.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (activityController != null){
            activityController.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (activityController != null){
            activityController.onDestory();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != activityController){
            activityController.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != activityController){
            activityController.onSaveInstanceState(outState);
        }
    }

    @Override
    public void onBackPressed() {
        if (null != activityController && !activityController.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (null != activityController){
            activityController.onReqeustPermissionResult(requestCode, permissions, grantResults);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public abstract BaseActivityController getController();
}
