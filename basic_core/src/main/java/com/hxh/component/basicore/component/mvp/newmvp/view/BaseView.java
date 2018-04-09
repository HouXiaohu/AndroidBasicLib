package com.hxh.component.basicore.component.mvp.newmvp.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxh.component.basicore.Base.delegate.interfaces.ILogRelated;
import com.hxh.component.basicore.Base.delegate.interfaces.IToolBarRelated;
import com.hxh.component.basicore.Base.delegate.interfaces.IViewRelated;
import com.hxh.component.basicore.Base.topbar.ActionBarConfig;

import java.util.List;

/**
 * Created by hxh on 2018/3/6.
 */

public abstract class BaseView
        implements
        IView,
        ILogRelated,
        IViewRelated,
        IToolBarRelated {


    private BaseViewDelegate mBaseViewDelegate;
    private View rootView;

    public BaseView() {
        mBaseViewDelegate = new BaseViewDelegate();
    }

    @Override
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = mBaseViewDelegate.onCreate(inflater,container,savedInstanceState,getLayoutId(),setActionBarConfig());

        initView(savedInstanceState);

        return rootView;
    }

    public View getRootView() {
        return rootView;
    }

    public abstract int getLayoutId();

    /**
     * 默认关闭
     * @return
     */
    protected ActionBarConfig setActionBarConfig(){
        return null;
    }


    public <V extends View> V findViewBy(int resId) {
        return mBaseViewDelegate.findViewBy(resId);
    }

    public <L extends View.OnClickListener>void setOnClickListener(L clickListener,int... id) {
        mBaseViewDelegate.setOnClickListener(clickListener,id);
    }


    //region 日志
    @Override
    public void d(String msg) {
        mBaseViewDelegate.d(msg);
    }

    @Override
    public void d(Throwable th) {
        mBaseViewDelegate.d(th);
    }

    @Override
    public void d(Exception e) {
        mBaseViewDelegate.d(e);
    }

    @Override
    public void e(String msg) {
        mBaseViewDelegate.e(msg);
    }

    @Override
    public void e(Throwable th) {
        mBaseViewDelegate.e(th);
    }

    @Override
    public void e(Exception e) {
        mBaseViewDelegate.e(e);
    }

    @Override
    public void i(String msg) {
        mBaseViewDelegate.i(msg);
    }

    @Override
    public void i(Throwable th) {
        mBaseViewDelegate.i(th);
    }

    @Override
    public void i(Exception e) {
        mBaseViewDelegate.i(e);
    }
    //endregion

    //region View支持
    @Override
    public void visibe(View view) {
        mBaseViewDelegate.visibe(view);
    }

    @Override
    public void gone(View view) {
        mBaseViewDelegate.gone(view);
    }

    @Override
    public void visibe(View... views) {
        mBaseViewDelegate.visibe(views);
    }

    @Override
    public void gone(View... views) {
        mBaseViewDelegate.gone(views);
    }

    @Override
    public void inVisibe(View... views) {
        mBaseViewDelegate.inVisibe(views);
    }

    @Override
    public void inVisibe(View view) {
        mBaseViewDelegate.inVisibe(view);
    }

    @Override
    public void toastShort(String msg) {
        mBaseViewDelegate.toastShort(msg);
    }

    @Override
    public void toastShort(String msg, int gravity) {
        mBaseViewDelegate.toastShort(msg, gravity);
    }

    @Override
    public void toastLong(String msg) {
        mBaseViewDelegate.toastLong(msg);
    }

    @Override
    public String getRES_String(int resId) {
        return mBaseViewDelegate.getRES_String(resId);
    }

    @Override
    public int getRES_Color(int resId) {
        return mBaseViewDelegate.getRES_Color(resId);
    }

    @Override
    public Drawable getRES_drawable(int resId) {
        return mBaseViewDelegate.getRES_drawable(resId);
    }

    @Override
    public boolean getRES_boolean(int resId) {
        return mBaseViewDelegate.getRES_boolean(resId);
    }

    @Override
    public float getRES_dimen(int resId) {
        return mBaseViewDelegate.getRES_dimen(resId);
    }

    //region TextView的操作
    @Override
    public String getText(EditText et) {
        return mBaseViewDelegate.getText(et);
    }

    @Override
    public String getText(TextView tv) {
        return mBaseViewDelegate.getText(tv);
    }

    @Override
    public String getText(EditText et, String defaulttext) {
        return mBaseViewDelegate.getText(et, defaulttext);
    }

    @Override
    public String getText(TextView tv, String defaulttext) {
        return mBaseViewDelegate.getText(tv, defaulttext);
    }

    @Override
    public String getText(TextView tv, int defaulttextResId) {
        return mBaseViewDelegate.getText(tv, defaulttextResId);
    }

    @Override
    public String getText(EditText et, int defaulttextResId) {
        return mBaseViewDelegate.getText(et, defaulttextResId);
    }

    @Override
    public String getHint(EditText et) {
        return mBaseViewDelegate.getHint(et);
    }

    @Override
    public String getHint(EditText et, String defaulttext) {
        return mBaseViewDelegate.getHint(et, defaulttext);
    }

    @Override
    public String getHint(EditText et, int defaulttextResId) {
        return mBaseViewDelegate.getHint(et, defaulttextResId);
    }

    @Override
    public <V extends TextView> void setText(V tv, String msg) {
        mBaseViewDelegate.setText(tv, msg);
    }

    @Override
    public <V extends TextView> void setText(V tv, int msgResId) {
        mBaseViewDelegate.setText(tv, msgResId);
    }
    //endregion

    @Override
    public boolean isEmpty(List list) {
        return mBaseViewDelegate.isEmpty(list);
    }

    @Override
    public boolean isEmpty(String msg) {
        return mBaseViewDelegate.isEmpty(msg);
    }

    @Override
    public boolean isEmpty(CharSequence str) {
        return mBaseViewDelegate.isEmpty(str);
    }

    @Override
    public boolean isEmpty(String... args) {
        return mBaseViewDelegate.isEmpty(args);
    }

    @Override
    public boolean isEmpty(EditText text) {
        return mBaseViewDelegate.isEmpty(text);
    }

    @Override
    public boolean isEmpty(TextView tv, String msg) {
        return mBaseViewDelegate.isEmpty(tv, msg);
    }

    @Override
    public boolean isEmpty(EditText text, String tipmsg) {
        return mBaseViewDelegate.isEmpty(text, tipmsg);
    }

    @Override
    public boolean isEmpty(TextView tv) {
        return mBaseViewDelegate.isEmpty(tv);
    }

    @Override
    public boolean isEmpty(Object obj) {
        return mBaseViewDelegate.isEmpty(obj);
    }

    @Override
    public void loadimg(ImageView iv, String url) {
        mBaseViewDelegate.loadimg(iv, url);
    }

    @Override
    public void loadimg(ImageView iv, int resid) {
        mBaseViewDelegate.loadimg(iv, resid);
    }

    @Override
    public void loadimg(ImageView iv, String url, int errorId) {
        mBaseViewDelegate.loadimg(iv, url, errorId);
    }
    //endregion

    //region ToolBar相关

    @Override
    public void setActionBarConfig(ActionBarConfig config) {
        mBaseViewDelegate.setActionBarConfig(config);
    }

    @Override
    public void setActionBar_Title(TextView tv_title) {
        mBaseViewDelegate.setActionBar_Title(tv_title);
    }

    @Override
    public ActionBarConfig getActionBarConfig() {
        return mBaseViewDelegate.getActionBarConfig();
    }

    @Override
    public View getToolBarRootView() {
        return mBaseViewDelegate.getToolBarRootView();
    }


    @Override
    public LinearLayout getActionbar_rightImageViewButtons() {
        return mBaseViewDelegate.getActionbar_rightImageViewButtons();
    }

    @Override
    public ImageView getActionbar_rightImageView() {
        return mBaseViewDelegate.getActionbar_rightImageView();
    }

    @Override
    public TextView getActionbar_title() {
        return mBaseViewDelegate.getActionbar_title();
    }


    @Override
    public View getActionbar_rightView() {
        return mBaseViewDelegate.getActionbar_rightView();
    }
    @Override
    public void setActionBar_Title(String title) {
        mBaseViewDelegate.setActionBar_Title(title);
    }

    @Override
    public void setBackViewTitle(String title) {
        mBaseViewDelegate.setBackViewTitle(title);
    }

    @Override
    public void setActionbar_rightText(String text) {
        mBaseViewDelegate.setActionbar_rightText(text);
    }

    @Override
    public void setActionbar_rightImg(int resId) {
        mBaseViewDelegate.setActionbar_rightImg(resId);
    }

    @Override
    public void setActionbar_rightImg(Drawable drawable) {
        mBaseViewDelegate.setActionbar_rightImg(drawable);
    }

    //endregion
}
