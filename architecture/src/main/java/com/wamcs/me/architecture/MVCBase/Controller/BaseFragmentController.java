package com.wamcs.me.architecture.MVCBase.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/6.
 */
public abstract class BaseFragmentController extends BaseController {

    private Fragment mFragment;

    public BaseFragmentController(Fragment fragment, BaseActivityController controller, View view) {
        super(controller.getContext());
        mFragment = fragment;

    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void onStart(){}

    public void onResume(){}

    public void onPause(){}

    public void onStop(){}

    public void onDestroy() {}

    public void onSaveInstanceState(Bundle outState) {}

    public void onDetach() {}

    public void onDestroyView() {}


    public void startActivity(Intent intent) {
        mFragment.startActivity(intent);
    }

    public void startActivity(Intent intent, @Nullable Bundle bundle) {
        mFragment.startActivity(intent, bundle);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        mFragment.startActivityForResult(intent, requestCode);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public FragmentManager getChildFragmentManager() {
        return mFragment.getChildFragmentManager();
    }

    public FragmentManager getFragmentManager() {
        return mFragment.getFragmentManager();
    }

}
