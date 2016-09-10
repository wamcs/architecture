package com.wamcs.me.architecture.recycler;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.wamcs.me.architecture.MVCBase.Controller.BaseActivityController;
import com.wamcs.me.architecture.MVCBase.Controller.BaseFragmentController;

import java.util.List;

import rx.Observable;

/**
 * Author: Wamcs
 * mail: kaili@hustunique.com
 * Created on 16/9/8.
 */
public abstract class BaseListFragment<Data> extends BaseFragmentController
        implements BaseListControllerImpl.DataInteractListener<Data>, ListController {

    private BaseListControllerImpl<Data> impl = new BaseListControllerImpl<>(this);

    public BaseListFragment(Fragment fragment, BaseActivityController controller, View view) {
        super(fragment, controller, view);
    }

    @Override
    public void refreshTop() {
        if (impl != null) {
            impl.refreshTop();
        }
    }

    @Override
    public void refreshBottom() {
        impl.refreshBottom();
    }

    @Override
    public boolean isRefresh() {
        return impl.isRefresh();
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

