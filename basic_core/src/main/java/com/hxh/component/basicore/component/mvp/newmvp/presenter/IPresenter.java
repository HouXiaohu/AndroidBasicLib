package com.hxh.component.basicore.component.mvp.newmvp.presenter;


import com.hxh.component.basicore.component.mvp.newmvp.view.IView;

public interface IPresenter<V extends IView>
{
    <B>void dispatchResponseEvent(String tag, B baseBean);
}
