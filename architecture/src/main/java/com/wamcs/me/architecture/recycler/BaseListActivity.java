package com.wamcs.me.architecture.recycler;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wamcs.me.architecture.MVCBase.Controller.BaseActivityController;

import java.util.List;

import rx.Observable;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/7.
 */
public abstract class BaseListActivity<Data> extends BaseActivityController
        implements ListController,BaseListControllerImpl.DataInteractListener<Data> {

    private BaseListControllerImpl<Data> baseListController;

    public BaseListActivity(AppCompatActivity activity, View view) {
        super(activity, view);
        baseListController = new BaseListControllerImpl<>(this);
    }


    @Override
    public void refreshTop() {
        baseListController.refreshTop();
    }

    @Override
    public void refreshBottom() {
        baseListController.refreshBottom();
    }

    @Override
    public boolean isRefresh() {
        return baseListController.isRefresh();
    }

    @Override
    public abstract Observable<List<Data>> requestData(int page);

    @NonNull
    @Override
    public abstract BaseAdapter<Data> getAdapter();

    @Override
    public abstract void onRefreshStateChanged(boolean isRefreshing);

    @Override
    public abstract void onError(Throwable t);
}

