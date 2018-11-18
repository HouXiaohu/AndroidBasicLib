package com.hxh.component.basicore.Base.app.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hxh.component.basicore.Base.topbar.ActionBarProvider;
import com.hxh.component.basicore.R;
import com.hxh.component.basicore.ui.ToastUIType;
import com.hxh.component.basicore.ui.stateview.StateView;
import com.hxh.component.basicore.util.Utils;

/**
 * Created by hxh on 2018/4/24.
 */

public class UIProvider {
    private UIProvider() {
    }

    public UIProvider(UIProvider uiProvider) {
        if(uiProvider.getToastUIType() == ToastUIType.INSIDE)
        {
            TextView tv = (TextView) LayoutInflater.from(Utils.getApplicationContext()).inflate(R.layout.layout_default_toastview,null);
            uiProvider.mToastView = tv;
            uiProvider.mToastViewTextViewId = R.id.tv_default_toast;
        }
    }

    /**
     * 全局Dialog
     */
//    private ILoadingProgressDialog mLoadingDialog;
    private int mLoadingDialogViewResId;
    private boolean mLoadingDialogViewisCancelable;

    /**
     * 默认使用系统的
     */
    private int mToastUIType = ToastUIType.SYSTEM;
    private View mToastView;
    private int mToastViewTextViewId;
    private int mToastGravity;
    private StateView mStateView;

    /**
     * 全局性ActionBar
     */
    private ActionBarProvider mActionBarProvider;



    public static class Builder
    {

        private boolean mLoadingDialogViewisCancelable;
        private int mToastUIType;
        private View mToastView;
        private int mToastViewTextViewId;
        private ActionBarProvider mActionBarProvider;
        private int mLoadingDialogViewLayoutId;
        private StateView mStateView;

        public Builder configLoadingDialogUI(int resid)
        {
            this.mLoadingDialogViewLayoutId = resid;
            this.mLoadingDialogViewisCancelable = false;
            return this;
        }

        public Builder configLoadingDialogUI(int resid,boolean isCancel)
        {
            this.mLoadingDialogViewisCancelable = isCancel;
            this.mLoadingDialogViewLayoutId = resid;
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

        public Builder configActionBar(ActionBarProvider provider)
        {
            this.mActionBarProvider = provider;
            return this;
        }

        public Builder configStateView(StateView provider)
        {
            this.mStateView = provider;
            return this;
        }

        public UIProvider build()
        {
            UIProvider ui = new UIProvider();
            ui.mLoadingDialogViewResId = mLoadingDialogViewLayoutId;
            ui.mLoadingDialogViewisCancelable = mLoadingDialogViewisCancelable;
            ui.mToastUIType = mToastUIType;
            ui.mToastView = mToastView;
            ui.mToastViewTextViewId = mToastViewTextViewId;
            ui.mStateView = mStateView;
            return ui;
        }

    }


    public int getLoadingDialogViewLayoutId() {
        return mLoadingDialogViewResId;
    }

    public boolean isLoadingDialogViewisCancelable() {
        return mLoadingDialogViewisCancelable;
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

    public ActionBarProvider getActionBarProvider() {
        return mActionBarProvider;
    }

    public void setActionBarProvider(ActionBarProvider mActionBarProvider) {
        this.mActionBarProvider = mActionBarProvider;
    }

    public int getToastGravity() {
        return mToastGravity;
    }

    public void setLoadingDialogViewLayoutId(int layoutid) {
        this.mLoadingDialogViewResId = layoutid;
    }

    public void setLoadingDialogViewisCancelable(boolean mLoadingDialogViewisCancelable) {
        this.mLoadingDialogViewisCancelable = mLoadingDialogViewisCancelable;
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

    public void setToastGravity(int mToastGravity) {
        this.mToastGravity = mToastGravity;
    }

    public StateView getStateView() {
        return mStateView;
    }
}
