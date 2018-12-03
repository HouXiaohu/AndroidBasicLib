package com.hxh.component.basicore.util.rx.resetfulhttpstyle;

import android.content.Context;

import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.ui.loading.CustomLoadingDialog;
import com.hxh.component.basicore.ui.loading.ILoadingProgressDialog;
import com.hxh.component.basicore.ui.stateview.IRequestState;
import com.hxh.component.basicore.util.Utils;

import java.lang.ref.WeakReference;
import java.net.ConnectException;

import rx.Subscriber;


/**
 * 标题: ProgressSubScribe.java
 * 作者: hxh
 * 日期: 2018/4/24 10:14
 * 描述: 带进度条的Subscribe
 */
public abstract class ProgressSubScribe<T> extends Subscriber<T> {
    private ILoadingProgressDialog mDialog;
    private WeakReference<Context> mContext;

    private boolean isself; //是否自己处理异常
    private boolean isNoConnection; //是否有网络连接

    public ProgressSubScribe() {
        this.mContext = new WeakReference<Context>(AppManager.getCurrentActivity());

        mDialog = new CustomLoadingDialog(mContext.get(),CoreLib.getInstance().getUIProvider().getLoadingDialogViewLayoutId(),CoreLib.getInstance().getUIProvider().isLoadingDialogViewisCancelable());

    }

    public ProgressSubScribe(boolean IsExceptionSelfCommand) {
        this.isself = IsExceptionSelfCommand;
        this.mContext = new WeakReference<Context>(AppManager.getCurrentActivity());
        mDialog = new CustomLoadingDialog(mContext.get(),CoreLib.getInstance().getUIProvider().getLoadingDialogViewLayoutId(),CoreLib.getInstance().getUIProvider().isLoadingDialogViewisCancelable());
    }


    /**
     * 虽说并不推荐在onStart()中去展示Dialog，But，MVP框架每个人都有自己的写法，
     * onStart()之所以不能展示Dialog主要原因是因为: onStart()的线程是由subscribe()时所处的线程，所以，
     * 万一subscribe()时所处的线程是 子线程，由于Android并不允许你在子线程中更新UI，所以会导致错误。
     * 但是本框架的写法不会导致这个情况的发生，本框架的M层由框架生成，P层肩负着一点M层的责任，后期会改
     *
     * @time 2017/11/27 10:27
     * @author
     */
    @Override
    public void onStart() {
        super.onStart();
        //检查网络
        if (Utils.NetWork.isConnected()) {
            showDialog();
        } else {
            //没有网络，直接结束
            isNoConnection = true;
            CoreLib.getInstance().getNetProvider().getRequestState().sendState(IRequestState.onNoNet);
            onError(new ConnectException("当前没有网络"));
        }

    }

    //
    @Override
    public void onError(Throwable e) {
        dissmisDialog();
        CoreLib.getInstance().getNetProvider().getRequestState().sendState(IRequestState.onError,e);
        if (isself) {
            _OnError(e);
            isself = false;
        } else {
            CoreLib.getInstance().getNetProvider().getApiErrorClasszz().handleApiError(e);
        }
    }



    @Override
    public void onCompleted() {
        dissmisDialog();

    }

    @Override
    public void onNext(T t) {
        dissmisDialog();
        _OnNet(t);
    }

    /**
     * 调用时机是当Loading结束，如：  报错;执行完毕
     */
    public void onCancel() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    public abstract void _OnError(Throwable msg);

    public abstract void _OnNet(T t);

    //关闭Loading
    private void dissmisDialog() {
        if (null != mDialog) mDialog.close();

        onCancel();
        if (null != mContext) {
            mContext.clear();
        }

        mContext = null;
        AppManager.clearLoadingDialog();

    }

    //打开Loading
    private void showDialog() {
        if (null != mDialog)
            mDialog.show();
        AppManager.addHttpLoadingDialog(mDialog);
    }



}
