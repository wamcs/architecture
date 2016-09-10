package com.wamcs.me.architecture.recycler;


import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/7.
 */
public class BaseListControllerImpl<Data> implements ListController{

    private DataInteractListener<Data> listener;

    private static final int MIN_PAGE = 1;
    private int page = MIN_PAGE;
    private boolean isRefreshing = false;

    public BaseListControllerImpl(DataInteractListener<Data> listener){
        if (listener == null) {
            throw new IllegalArgumentException("DataInteractionListener can not be null.");
        }
        this.listener = listener;
    }


    @Override
    public void refreshTop() {
        if (isRefreshing){
            return;
        }
        page = MIN_PAGE;
        changeRefreshStatus(true);
        listener.requestData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Data>>() {
                    @Override
                    public void call(List<Data> datas) {
                        listener.getAdapter().setData(datas);
                        changeRefreshStatus(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onError(throwable);
                        changeRefreshStatus(false);
                    }
                });
    }

    @Override
    public void refreshBottom() {
        if (isRefreshing){
            return;
        }
        page++;
        changeRefreshStatus(true);
        listener.requestData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Data>>() {
                    @Override
                    public void call(List<Data> datas) {
                        listener.getAdapter().addData(datas);
                        changeRefreshStatus(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onError(throwable);
                        changeRefreshStatus(false);
                    }
                });
    }

    @Override
    public boolean isRefresh() {
        return isRefreshing;
    }

    public void changeRefreshStatus(boolean isRefreshing){
        this.isRefreshing = isRefreshing;
        onRefreshStateChanged(isRefreshing);
    }

    private void onRefreshStateChanged(boolean isRefreshing) {
        if (listener != null) {
            listener.onRefreshStateChanged(isRefreshing);
        }
    }

    public interface DataInteractListener<Data>{

        Observable<List<Data>> requestData(int page);

        @NonNull
        BaseAdapter<Data> getAdapter();

        void onRefreshStateChanged(boolean isRefreshing);

        void onError(Throwable t);
    }
}
