package com.hxh.component.basicore.Base;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxh.component.basicore.Base.delegate.ToolBarDelegate;
import com.hxh.component.basicore.Base.delegate.interfaces.IToolBarRelated;
import com.hxh.component.basicore.Base.topbar.ActionBarConfig;
import com.hxh.component.basicore.Base.view.EmptyActivity;
import com.hxh.component.basicore.mvp.persenter.IPresenter;
import com.hxh.component.basicore.mvp.view.IView;

public abstract class TopBarBaseActivity<P extends IPresenter>
        extends EmptyActivity<P>
        implements IToolBarRelated {


    private ToolBarDelegate mToolBarDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.mToolBarDelegate = new ToolBarDelegate(setActionBarConfig());
        this.mToolBarDelegate.init();
        this.mToolBarDelegate.fetchToView(getView(),this);
    }


    protected abstract ActionBarConfig setActionBarConfig();


    //region ToolBar相关


    @Override
    public void setActionBarConfig(ActionBarConfig config) {
        mToolBarDelegate.setActionBarConfig(config);
    }

    @Override
    public void setActionBar_title(TextView tv_title) {
        mToolBarDelegate.setActionbar_title(tv_title);
    }

    @Override
    public ActionBarConfig getActionBarConfig() {
        return mToolBarDelegate.getmActionbarconfig();
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
    public ImageView getActionbar_rightview_img() {
        return mToolBarDelegate.getActionbar_rightImageView();
    }

    @Override
    public TextView getActionbar_title() {
        return mToolBarDelegate.getActionbar_title();
    }


    @Override
    public View getActionbar_rightview() {
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
    @Override
    public void setBackViewConfig_title(String title) {
        mToolBarDelegate.setBackViewTitle(title);
    }

    //endregion
}
