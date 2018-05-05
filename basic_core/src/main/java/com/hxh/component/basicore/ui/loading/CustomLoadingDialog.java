package com.hxh.component.basicore.ui.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.hxh.component.basicore.R;

import java.lang.ref.WeakReference;

/**
* 创建者：hxh
* 时间：  2017/3/4
* 描述： 加入一个单例
*/
public class LoadingDialog {


    private Dialog mLoadingDialog;
    private WeakReference<Context> mContext;

    public LoadingDialog(Context context) {
        init(context,null,true);

    }

    public LoadingDialog(Context context, View view) {
        init(context,view,true);

    }

    public LoadingDialog(Context context, View view,boolean iscanceable) {
        init(context,view,iscanceable);
    }

    private void init(Context context,View contentView,boolean isCanceable)
    {
        this.mContext = new WeakReference<Context>(context);
        mLoadingDialog = new Dialog(this.mContext.get(), R.style.loading_dialog);
        mLoadingDialog.setCancelable(isCanceable);
        mLoadingDialog.setContentView(contentView, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    //
    public void show()
    {
        if(!mLoadingDialog.isShowing())
        {
            mLoadingDialog.show();
        }
    }


    public void close()
    {
        if(mLoadingDialog.isShowing())
        {
            mLoadingDialog.dismiss();
            this.mContext = null;
        }
    }



}
