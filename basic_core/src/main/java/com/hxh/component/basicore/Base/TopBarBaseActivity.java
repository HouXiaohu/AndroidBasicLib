package com.hxh.component.basicore.Base;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxh.component.basicore.Base.delegate.ToolBarDelegate;
import com.hxh.component.basicore.Base.delegate.ToolBarDelegate_FullScreenMode;
import com.hxh.component.basicore.Base.delegate.interfaces.IToolBarRelated;
import com.hxh.component.basicore.Base.topbar.ActionBarConfig;
import com.hxh.component.basicore.Base.view.EmptyActivity;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.mvp.persenter.IPresenter;

public abstract class TopBarBaseActivity<P extends IPresenter>
        extends EmptyActivity<P>
        implements IToolBarRelated {


    private IToolBarRelated mToolBarDelegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (CoreLib.getInstance().getAppComponent().globalActionBarProvider().isEnableImmeriveMode()) {
            this.mToolBarDelegate = new ToolBarDelegate_FullScreenMode(setActionBarConfig());
            ((ToolBarDelegate_FullScreenMode) this.mToolBarDelegate).init();
            ((ToolBarDelegate_FullScreenMode) this.mToolBarDelegate).fetchToView(getView(),this);
        }else
        {
            this.mToolBarDelegate = new ToolBarDelegate(setActionBarConfig());
            ((ToolBarDelegate) this.mToolBarDelegate).init();
            ((ToolBarDelegate) this.mToolBarDelegate).fetchToView(getView(),this);
        }



    }


    protected abstract ActionBarConfig setActionBarConfig();


    //region ToolBar相关



    @Override
    public void setActionBarConfig(ActionBarConfig config) {
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
