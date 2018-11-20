package com.hxh.component.basicore.component.mvp.persenter;

import android.widget.EditText;
import android.widget.TextView;

import com.hxh.component.basicore.component.mvp.persenter.delegate.BasePresenterRelated;
import com.hxh.component.basicore.component.mvp.persenter.delegate.IPresenterRelated;
import com.hxh.component.basicore.component.mvp.view.IView;
import com.hxh.component.basicore.component.net.IApiError;

import java.util.List;

import retrofit2.Response;
import rx.Subscription;

/**
 * Created by hxh on 2017/4/11.
 */
public abstract class BasePresenter<V extends IView> implements IPresenter<V>,IPresenterRelated {
    protected V mView; //当前view
    protected BasePresenterRelated mDelegate = new BasePresenterRelated();//管理subscription
    @Override
    public V getView()
    {
        if(null == mView)
        {
            throw new IllegalStateException("v can not null");
        }
        return mView;
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

    @Override
    public void AttachView(V view) {
        if(null == view)
        {
            throw new IllegalStateException("v can not be null");
        }
        this.mView = view;
    }

    @Override
    public void DetachView() {
        mView = null;
    }


    //region Presenter辅助方法
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
