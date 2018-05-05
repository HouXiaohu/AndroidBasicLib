package com.hxh.component.basicore.Base.app;

import android.view.View;

import com.hxh.component.basicore.ui.loading.ILoadingProgressDialog;
import com.hxh.component.basicore.util.ToastUIType;

/**
 * Created by hxh on 2018/4/24.
 */

public class UIProvider {

    private ILoadingProgressDialog mLoadingDialog;
    /**
     * 默认使用系统的
     */
    private int mToastUIType = ToastUIType.SYSTEM;
    private View mToastView;
    private int mToastViewTextViewId;


    public static class Builder
    {
        private ILoadingProgressDialog mLoadingDialog;
        private int mToastUIType;
        private View mToastView;
        private int mToastViewTextViewId;


        public Builder configLoadingDialogUI(ILoadingProgressDialog dialog)
        {
            this.mLoadingDialog = dialog;
            return this;
        }

        public Builder configToastUI(int type)
        {
            this.mToastUIType = type;
            if(mToastUIType == ToastUIType.CUSTOM)
            {
                throw new IllegalStateException("if you want config toast contentview,you can call configToastUI(View ...)");
            }
            return this;
        }

        public Builder configToastUI(View toastview)
        {
            this.mToastView = toastview;
            this.mToastUIType = ToastUIType.CUSTOM;
            return this;
        }

        /**
         *
         * @param toastview  展示view
         * @param textId 展示view中的textviewid，用于动态更改，如果你不提供，那么展示view是静态的
         * @return
         */
        public Builder configToastUI(View toastview,int textId)
        {
            this.mToastView = toastview;
            this.mToastUIType = ToastUIType.CUSTOM;
            this.mToastViewTextViewId = textId;
            return this;
        }

        public UIProvider build()
        {
            UIProvider ui = new UIProvider();
            ui.mLoadingDialog = mLoadingDialog;
            ui.mToastUIType = mToastUIType;
            ui.mToastView = mToastView;
            ui.mToastViewTextViewId = mToastViewTextViewId;
            return ui;
        }

    }


    public ILoadingProgressDialog getLoadingDialog() {
        return mLoadingDialog;
    }

    public int getToastUIType() {
        return mToastUIType;
    }

    public View getToastView() {
        return mToastView;
    }

    public int getToastViewTextViewId() {
        return mToastViewTextViewId;
    }

    public void setLoadingDialog(ILoadingProgressDialog mLoadingDialog) {
        this.mLoadingDialog = mLoadingDialog;
    }

    public void setToastUIType(int mToastUIType) {
        this.mToastUIType = mToastUIType;
    }

    public void setToastView(View mToastView) {
        this.mToastView = mToastView;
    }

    public void setToastViewTextViewId(int mToastViewTextViewId) {
        this.mToastViewTextViewId = mToastViewTextViewId;
    }
}
