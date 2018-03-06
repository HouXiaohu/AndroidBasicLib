package com.hxh.component.basicore.Base.delegate.interfaces;

import com.hxh.component.basicore.mvp.persenter.BasePresenter;

/**
 * Created by hxh on 2018/2/28.
 */

public interface IStateLayoutRelated<P extends BasePresenter> {

    void operaErrorLayout(boolean isHide);
    void operaLoadingLayout(boolean isHide);
    void operaNoNetWorkLayout(boolean isHide);
    void operaStateLayout(String tag,boolean isHide);

}
