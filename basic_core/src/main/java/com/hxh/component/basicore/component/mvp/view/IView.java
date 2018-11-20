package com.hxh.component.basicore.component.mvp.view;

import android.os.Bundle;

import com.hxh.component.basicore.component.mvp.persenter.IPresenter;


/**
 * VIew的基类，需要一个Presenter
 */
public interface IView<P extends IPresenter> {



    int getLayoutId();

    /**
     * 相当于onCreate
     * @param saveInstanceState
     */
    void initData(Bundle saveInstanceState);

    /**
     * 创建一个presenter，Fragment 必需重写，Activity为可选（当Activity作为Presenter时候，那么这个你也必须重写）
     * @return
     */
    P newP();

}
