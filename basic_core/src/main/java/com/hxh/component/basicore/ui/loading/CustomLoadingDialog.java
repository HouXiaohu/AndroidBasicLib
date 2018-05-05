package com.hxh.component.basicore.ui.loading;

import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;

import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.R;

/**
* 创建者：hxh
* 时间：  2017/3/4
* 描述： 加入一个单例
*/
public class CustomLoadingDialog implements ILoadingProgressDialog{


    private Dialog mLoadingDialog;
    private View contentView;
    private boolean iscanceable;
    private int mstyleRes = R.style.loading_dialog;
    public CustomLoadingDialog(View view) {
        init(view,true,mstyleRes);
    }

    public CustomLoadingDialog(View view, boolean iscanceable) {
        init(view,iscanceable,mstyleRes);
    }

    public CustomLoadingDialog(View view, boolean iscanceable,int styleres) {
        init(view,iscanceable,styleres);
    }

    private void init(View contentView,boolean isCanceable,int styleres)
    {
        this.mstyleRes = styleres;
        this.iscanceable = isCanceable;
        this.contentView = contentView;
    }

    @Override
    public void show()
    {
        if(null == mLoadingDialog)
        {
            mLoadingDialog = new Dialog(AppManager.getCurrentActivity(),mstyleRes );
            mLoadingDialog.setCancelable(iscanceable);
            mLoadingDialog.setContentView(contentView, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
        }

        if(!mLoadingDialog.isShowing())
        {
            mLoadingDialog.show();
        }
    }

    @Override
    public void close()
    {
        if(mLoadingDialog.isShowing())
        {
            mLoadingDialog.dismiss();
        }
    }



}
