package com.hxh.component.emvp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.Base.delegate.CheckNullDelegate;
import com.hxh.component.basicore.Base.delegate.LogDelegate;
import com.hxh.component.basicore.Base.delegate.ToolBarDelegate;
import com.hxh.component.basicore.Base.delegate.ToolBarDelegate_FullScreenMode;
import com.hxh.component.basicore.Base.delegate.ViewRelatedDelegate;
import com.hxh.component.basicore.Base.delegate.interfaces.ICheckNullRelated;
import com.hxh.component.basicore.Base.delegate.interfaces.ILogRelated;
import com.hxh.component.basicore.Base.delegate.interfaces.IToolBarRelated;
import com.hxh.component.basicore.Base.delegate.interfaces.IViewRelated;
import com.hxh.component.basicore.Base.topbar.ActionBarConfig;
import com.hxh.component.basicore.CoreLib;

import java.util.List;

/**
 * Created by hxh on 2018/3/16.
 */

public class BaseViewDelegate
        implements
        ILogRelated,
        IViewRelated,
        IToolBarRelated,
        ICheckNullRelated {

    //相关支持
    private LogDelegate mLogDelegate;
    private ViewRelatedDelegate mViewRelateDelegate;
    private IToolBarRelated mToolBarDelegate;
    private CheckNullDelegate mCheckNullDelegate;
    protected View rootView;
    private SparseArray<View> mViews; //View的缓存类

    public BaseViewDelegate() {
        mLogDelegate = new LogDelegate();
        mViewRelateDelegate = new ViewRelatedDelegate();
        mViews = new SparseArray<>();
        mCheckNullDelegate = new CheckNullDelegate();
    }

    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, int layourId, ActionBarConfig config) {
        rootView = inflater.inflate(layourId, container, false);

        if (null != config) {
            openToolBar(config);
        }

        return rootView;
    }

    private void openToolBar(ActionBarConfig config) {
        if (CoreLib.getInstance().getAppComponent().globalUIProvider().getActionBarProvider().isEnableImmeriveMode()) {
            this.mToolBarDelegate = new ToolBarDelegate_FullScreenMode(config);
            ((ToolBarDelegate_FullScreenMode) this.mToolBarDelegate).init();
            ((ToolBarDelegate_FullScreenMode) this.mToolBarDelegate).fetchToView(rootView, rootView.getContext());
        } else {
            this.mToolBarDelegate = new ToolBarDelegate(config);
            ((ToolBarDelegate) this.mToolBarDelegate).init();
            ((ToolBarDelegate) this.mToolBarDelegate).fetchToView(rootView, rootView.getContext());
        }
    }


    public <V extends View> V findViewBy(int resId) {
        View view = mViews.get(resId);
        if (null == view) {
            view = rootView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (V) view;
    }

    public <L extends View.OnClickListener> void setOnClickListener(L clickListener, int... id) {
        if (null == id) {
            throw new IllegalArgumentException("id can't is null");
        }
        if (null == clickListener) {
            throw new IllegalStateException("OnClickListener can't is null");
        }
        for (int i : id) {
            findViewBy(i).setOnClickListener(clickListener);
        }
    }

    public Context getContext() {
        if (null != rootView) {
            return rootView.getContext();
        }
        return AppManager.getCurrentCompatActivity();
    }

    public void release() {
        mLogDelegate = null;
        mToolBarDelegate = null;
        mViewRelateDelegate = null;
        mViews = null;
    }

    //region 日志
    @Override
    public void d(String msg) {
        mLogDelegate.d(msg);
    }

    @Override
    public void d(Throwable th) {
        mLogDelegate.d(th);
    }

    @Override
    public void d(Exception e) {
        mLogDelegate.d(e);
    }

    @Override
    public void e(String msg) {
        mLogDelegate.e(msg);
    }

    @Override
    public void e(Throwable th) {
        mLogDelegate.e(th);
    }

    @Override
    public void e(Exception e) {
        mLogDelegate.e(e);
    }

    @Override
    public void i(String msg) {
        mLogDelegate.i(msg);
    }

    @Override
    public void i(Throwable th) {
        mLogDelegate.i(th);
    }

    @Override
    public void i(Exception e) {
        mLogDelegate.i(e);
    }
    //endregion

    //region View支持
    @Override
    public void visibe(View view) {
        mViewRelateDelegate.visibe(view);
    }

    @Override
    public void gone(View view) {
        mViewRelateDelegate.gone(view);
    }

    @Override
    public void visibe(View... views) {
        mViewRelateDelegate.visibe(views);
    }

    @Override
    public void gone(View... views) {
        mViewRelateDelegate.gone(views);
    }

    @Override
    public void inVisibe(View... views) {
        mViewRelateDelegate.inVisibe(views);
    }

    @Override
    public void inVisibe(View view) {
        mViewRelateDelegate.inVisibe(view);
    }

    @Override
    public void toastShort(String msg) {
        mViewRelateDelegate.toastShort(msg);
    }

    @Override
    public void toastShort(String msg, int gravity) {
        mViewRelateDelegate.toastShort(msg, gravity);
    }

    @Override
    public void toastLong(String msg) {
        mViewRelateDelegate.toastLong(msg);
    }

    @Override
    public String getRES_String(int resId) {
        return mViewRelateDelegate.getRES_String(resId);
    }

    @Override
    public int getRES_Color(int resId) {
        return mViewRelateDelegate.getRES_Color(resId);
    }

    @Override
    public Drawable getRES_drawable(int resId) {
        return mViewRelateDelegate.getRES_drawable(resId);
    }

    @Override
    public boolean getRES_boolean(int resId) {
        return mViewRelateDelegate.getRES_boolean(resId);
    }

    @Override
    public float getRES_dimen(int resId) {
        return mViewRelateDelegate.getRES_dimen(resId);
    }

    //region TextView的操作
    @Override
    public String getText(EditText et) {
        return mViewRelateDelegate.getText(et);
    }

    @Override
    public String getText(TextView tv) {
        return mViewRelateDelegate.getText(tv);
    }

    @Override
    public String getText(EditText et, String defaulttext) {
        return mViewRelateDelegate.getText(et, defaulttext);
    }

    @Override
    public String getText(TextView tv, String defaulttext) {
        return mViewRelateDelegate.getText(tv, defaulttext);
    }

    @Override
    public String getText(TextView tv, int defaulttextResId) {
        return mViewRelateDelegate.getText(tv, defaulttextResId);
    }

    @Override
    public String getText(EditText et, int defaulttextResId) {
        return mViewRelateDelegate.getText(et, defaulttextResId);
    }

    @Override
    public String getHint(EditText et) {
        return mViewRelateDelegate.getHint(et);
    }

    @Override
    public String getHint(EditText et, String defaulttext) {
        return mViewRelateDelegate.getHint(et, defaulttext);
    }

    @Override
    public String getHint(EditText et, int defaulttextResId) {
        return mViewRelateDelegate.getHint(et, defaulttextResId);
    }

    @Override
    public <V extends TextView> void setText(V tv, String msg) {
        mViewRelateDelegate.setText(tv, msg);
    }

    @Override
    public <V extends TextView> void setText(V tv, int msgResId) {
        mViewRelateDelegate.setText(tv, msgResId);
    }
    //endregion

    @Override
    public boolean isEmpty(List list) {
        return mCheckNullDelegate.isEmpty(list);
    }

    @Override
    public boolean isEmpty(String msg) {
        return mCheckNullDelegate.isEmpty(msg);
    }

    @Override
    public boolean isEmpty(CharSequence str) {
        return mCheckNullDelegate.isEmpty(str);
    }

    @Override
    public boolean isEmpty(String... args) {
        return mCheckNullDelegate.isEmpty(args);
    }

    @Override
    public boolean isEmpty(EditText text) {
        return mCheckNullDelegate.isEmpty(text);
    }

    @Override
    public boolean isEmpty(TextView tv, String msg) {
        return mCheckNullDelegate.isEmpty(tv, msg);
    }

    @Override
    public boolean isEmpty(EditText text, String tipmsg) {
        return mCheckNullDelegate.isEmpty(text, tipmsg);
    }

    @Override
    public boolean isEmpty(TextView tv) {
        return mCheckNullDelegate.isEmpty(tv);
    }

    @Override
    public boolean isEmpty(Object obj) {
        return mCheckNullDelegate.isEmpty(obj);
    }

    @Override
    public void loadimg(ImageView iv, String url) {
        mViewRelateDelegate.loadimg(iv, url);
    }

    @Override
    public void loadimg(ImageView iv, int resid) {
        mViewRelateDelegate.loadimg(iv, resid);
    }

    @Override
    public void loadimg(ImageView iv, String url, int errorId) {
        mViewRelateDelegate.loadimg(iv, url, errorId);
    }
    //endregion

    //region ToolBar相关

    @Override
    public void setActionBarConfig(ActionBarConfig config) {
        if (null == mToolBarDelegate)
            openToolBar(config);
        else
            mToolBarDelegate.setActionBarConfig(config);
    }

    @Override
    public void setActionBar_Title(TextView tv_title) {
        mToolBarDelegate.setActionBar_Title(tv_title);
    }

    @Override
    public ActionBarConfig getActionBarConfig() {
        return mToolBarDelegate.getActionBarConfig();
    }

    @Override
    public View getToolBarRootView() {
        return mToolBarDelegate.getToolBarRootView();
    }


    @Override
    public LinearLayout getActionbar_rightImageViewButtons() {
        return mToolBarDelegate.getActionbar_rightImageViewButtons();
    }

    @Override
    public ImageView getActionbar_rightImageView() {
        return mToolBarDelegate.getActionbar_rightImageView();
    }

    @Override
    public TextView getActionbar_title() {
        return mToolBarDelegate.getActionbar_title();
    }


    @Override
    public View getActionbar_rightView() {
        return mToolBarDelegate.getActionbar_rightView();
    }

    @Override
    public void setActionBar_Title(String title) {
        mToolBarDelegate.setActionBar_Title(title);
    }

    @Override
    public void setBackViewTitle(String title) {
        mToolBarDelegate.setBackViewTitle(title);
    }

    @Override
    public void setActionbar_rightText(String text) {
        mToolBarDelegate.setActionbar_rightText(text);
    }

    @Override
    public void setActionbar_rightImg(int resId) {
        mToolBarDelegate.setActionbar_rightImg(resId);
    }

    @Override
    public void setActionbar_rightImg(Drawable drawable) {
        mToolBarDelegate.setActionbar_rightImg(drawable);
    }

    //endregion
}
