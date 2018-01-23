package com.hxh.component.basicore.Base.delegate.interfaces;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxh.component.basicore.Base.topbar.ActionBarConfig;

/**
 * ToolBar相关功能
 */
public interface IToolBarRelated {




    ActionBarConfig getActionBarConfig();


    /**
     * 得到toolbar 的顶级view
     *
     * @return
     */
    View getToolBarRootView();

    /**
     * 设置ActionbarTitle
     *
     * @param title
     */
    void setActionBar_Title(String title);

    void setActionbar_rightText(String text);

    void setActionbar_rightImg(int resId);

    void setActionbar_rightImg(Drawable drawable);

    void setActionBar_Title(TextView tv_title);


    void setActionBarConfig(ActionBarConfig config);
    /**
     * 得到
     *
     * @return
     */
    LinearLayout getActionbar_rightImageViewButtons();

    ImageView getActionbar_rightImageView();

    View getActionbar_rightView();

    TextView getActionbar_title();

    void setBackViewTitle(String title);

}
