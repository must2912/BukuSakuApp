package com.stematel.bukusaku.Service;

/**
 * Created by tofa-pc on 1/24/2018.
 */

public class PresenterService<T> implements IPresenterService<T> {
    private T mViewService;

    @Override
    public void injectView(T apiView) {
        mViewService = apiView;
    }

    @Override
    public void deInjectView() {
        mViewService = null;
    }

    public T getApiView() {
        return mViewService;
    }
}

