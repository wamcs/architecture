package com.wamcs.me.architecture.MVCBase.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.wamcs.me.architecture.MVCBase.Controller.BaseActivityController;
import com.wamcs.me.architecture.MVCBase.Controller.ControllerHolder;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/6.
 */
public abstract class BaseFragment extends Fragment implements ControllerHolder {

    private ControllerHolder controller;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ControllerHolder){
            controller = (ControllerHolder) context;
        }else {
            System.err.print(context.toString()
                    + "must implement ControllerHolder");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (null != getFragmentController()){
            getFragmentController().onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != getFragmentController()){
            getFragmentController().onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != getFragmentController()){
            getFragmentController().onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (null != getFragmentController()){
            getFragmentController().onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != getFragmentController()){
            getFragmentController().onDestroy();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (null != getFragmentController()){
             getFragmentController().onDetach();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != getFragmentController()){
            getFragmentController().onDestroyView();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (null != getFragmentController()){
            getFragmentController().onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (null != getFragmentController()){
            getFragmentController().onSaveInstanceState(outState);
        }
    }

    public BaseActivityController getActivityController(){
        if (null != controller){
            return (BaseActivityController) controller;
        }
        throw new RuntimeException("controller must has been binding in fragment.attach()");
    }

    public abstract BaseFragment getFragmentController();
}
