package com.stematel.bukusaku.Service;

/**
 * Created by tofa-pc on 1/24/2018.
 */

public interface IPresenterService<V> {
    void injectView(V ViewService);
    void deInjectView();
}

