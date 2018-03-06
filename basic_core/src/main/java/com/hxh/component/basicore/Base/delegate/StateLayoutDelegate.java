package com.hxh.component.basicore.Base.delegate;

import com.hxh.component.basicore.Base.delegate.interfaces.IStateLayoutRelated;
import com.hxh.component.basicore.mvp.persenter.BasePresenter;

/**
 * Created by hxh on 2018/2/28.
 */

public class StateLayoutDelegate<P extends BasePresenter> implements IStateLayoutRelated<P>{

    private P p;
    @Override
    public void operaErrorLayout(boolean isHide) {

    }

    @Override
    public void operaLoadingLayout(boolean isHide) {

    }

    @Override
    public void operaNoNetWorkLayout(boolean isHide) {

    }

    @Override
    public void operaStateLayout(String tag, boolean isHide) {

    }
}
