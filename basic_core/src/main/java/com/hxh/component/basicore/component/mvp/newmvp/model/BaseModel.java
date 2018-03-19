package com.hxh.component.basicore.component.mvp.newmvp.model;


import android.widget.EditText;
import android.widget.TextView;

import com.hxh.component.basicore.component.mvp.newmvp.presenter.IPresenter;
import com.hxh.component.basicore.component.net.IApiError;

import java.util.List;

import retrofit2.Response;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;


/**
 * 标题: BaseModel.java
 * 作者: hxh
 * 日期: 2018/3/10 17:18
 * 描述: BaseModel
 */
public abstract class BaseModel
        implements IModel,
        IModelRelated
{
    private IPresenter mCurPresenters;
    protected CompositeSubscription mSubscription;//管理subscription
    protected String mCurrentRequestTag;
    protected BaseModelDelegate mDelegate;

    public BaseModel() {
        this.mDelegate = new BaseModelDelegate();
    }

    public void registerP(IPresenter p) {
        mCurPresenters = p;
    }

    public <B> void dispatchResponseEvent(String tag, B baseBean) {
        if (null != mCurPresenters) {

            mCurPresenters.dispatchResponseEvent(tag, baseBean);
        }
    }

    public <B> void dispatchResponseEvent(B baseBean) {
        dispatchResponseEvent(mCurrentRequestTag, baseBean);
    }

    @Override
    public void release() {
        unSubscription();
        mSubscription = null;
        mCurPresenters = null;
        mCurrentRequestTag = null;
        mDelegate = null;
    }


    @Override
    public void addSubscription(Subscription sub)
    {
        mDelegate.addSubscription(sub);
    }


    @Override
    public void unSubscription()
    {
        mDelegate.unSubscription();
    }

    @Override
    public void newCompositeSubscription()
    {
        mDelegate.newCompositeSubscription();
    }



    //region model辅助方法
    @Override
    public boolean isEmpty(List list) {
        return mDelegate.isEmpty(list);
    }

    @Override
    public boolean isEmpty(String msg) {
        return mDelegate.isEmpty(msg);
    }

    @Override
    public boolean isEmpty(CharSequence str) {
        return mDelegate.isEmpty(str);
    }

    @Override
    public boolean isEmpty(String... args) {
        return mDelegate.isEmpty(args);
    }

    @Override
    public boolean isEmpty(EditText text) {
        return mDelegate.isEmpty(text);
    }

    @Override
    public boolean isEmpty(EditText text, String tipmsg) {
        return mDelegate.isEmpty(text,tipmsg);
    }

    @Override
    public boolean isEmpty(TextView tv) {
        return mDelegate.isEmpty(tv);
    }

    @Override
    public boolean isEmptyJson(String text) {
        return mDelegate.isEmptyJson(text);
    }

    //endregion

    //region APiError
    @Override
    public int getErrorCode() {
        return mDelegate.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return mDelegate.getErrorMessage();
    }

    @Override
    public int getErrorCode(Throwable msg) {
        return mDelegate.getErrorCode(msg);
    }

    @Override
    public String getErrorMessage(Throwable msg) {
        return mDelegate.getErrorMessage(msg);
    }

    @Override
    public void handleApiError(Throwable msg) {
        mDelegate.handleApiError(msg);
    }

    @Override
    public boolean checkResponseBodyContainErrorBody(Response body) {
        return mDelegate.checkResponseBodyContainErrorBody(body);
    }



    @Override
    public String checkResponseBodyContainErrorBodyReturnErrorMessage(Response body) {
        return mDelegate.checkResponseBodyContainErrorBodyReturnErrorMessage(body);
    }

    @Override
    public IApiError checkResponseBodyContainErrorBodyReturnErrorBody(Response body) {
        return mDelegate.checkResponseBodyContainErrorBodyReturnErrorBody(body);
    }

    @Override
    public IApiError getApiError(Throwable e) {
        return mDelegate.getApiError(e);
    }



    //endregion

}
