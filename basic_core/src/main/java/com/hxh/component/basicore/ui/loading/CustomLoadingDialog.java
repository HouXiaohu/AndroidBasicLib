package com.hxh.component.basicore.ui.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hxh.component.basicore.R;

/**
 * 创建者：hxh
 * 时间：  2017/3/4
 * 描述： 加入一个单例
 */
public class CustomLoadingDialog implements ILoadingProgressDialog {


    private Dialog mLoadingDialog;
    private View contentView;
    private boolean iscanceable;
    private int mstyleRes = R.style.dialog_style_normal;
    private Context context;

    public CustomLoadingDialog(Context context, int layoutid) {
        init(context, layoutid, true, mstyleRes);
    }

    public CustomLoadingDialog(Context context, int layoutid, boolean iscanceable) {
        init(context, layoutid, iscanceable, mstyleRes);
    }

    public CustomLoadingDialog(Context context, int layoutid, boolean iscanceable, int styleres) {
        init(context, layoutid, iscanceable, styleres);
    }

    private void init(Context context, int layoutid, boolean isCanceable, int styleres) {
        this.context = context;
        this.mstyleRes = styleres;
        this.iscanceable = isCanceable;
        this.contentView = LayoutInflater.from(this.context).inflate(layoutid,null);
        mLoadingDialog = new Dialog(context, mstyleRes);
        mLoadingDialog.setCancelable(iscanceable);
        mLoadingDialog.setContentView(this.contentView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 1.2.2 中，对这里的Actiivity是否运行做了一个判断
     */
    @Override
    public void show() {
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.show();
        }
    }


    @Override
    public void close() {
        if (null != mLoadingDialog && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}
