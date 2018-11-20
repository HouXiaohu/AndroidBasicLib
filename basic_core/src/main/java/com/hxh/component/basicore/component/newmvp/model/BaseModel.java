package com.hxh.component.basicore.component.newmvp.model;


import android.widget.EditText;
import android.widget.TextView;

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
 * 2018/04/03
 * 问题1 :  Model为什么要必须依赖于一个Presenter？当前这么做，就把Model给限制死了，
 * Model应该是单独公共的一层，对外提供方法，如果我的Utils中想用怎么办？View中也想用怎么办(简单的逻辑)
 *
 * 问题2:  目前下发结果的函数是dispatchResponseEvent(tag,bean)，按照理想的情况下,Model层由单独人员
 * 编写，当编写完毕，提供给另一个开发人员整合，此时，另一个开发人员对于Model层的调用应该是轻松的，而
 * 不是现在的这种，通过tag区分，而且，Model层返回的数据，可能有好几个，如果还是用这种方法，必然会增加
 * 另一个开发者的难度。
 *
 * 问题3： 关于里氏替换原则，外部应该调用的是我的这个IModel接口，采用的应该是子类实现方式
 *
 */
public abstract class BaseModel
        implements IModel,
        IModelRelated
{

    protected CompositeSubscription mSubscription;//管理subscription
    protected String mCurrentRequestTag;
    protected BaseModelDelegate mDelegate;



    public BaseModel() {
        this.mDelegate = new BaseModelDelegate();
    }



    @Override
    public void release() {
        unSubscription();
        mSubscription = null;
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
