package com.hxh.component.basicore.ui.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.R;

/**
 * Created by hxh on 2018/4/23.
 */

public class CirCleLoadingDialog implements ILoadingProgressDialog{

    public CirCleLoadingDialog(Context context,String loadingText) {
        init(context,loadingText,true);
    }

    public CirCleLoadingDialog(Context context,String loadingText,boolean isCancel) {
        init(context,loadingText,isCancel);
    }



    private LVCircularRing mLoadingView;
    private Dialog mLoadingDialog;
    private TextView loadingText;
    private LinearLayout main_view;


    private void init(Context context,String msg,boolean isCancelable)
    {
        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(
                R.layout.loading_dialog_view, null);
        // 获取整个布局
        main_view = (LinearLayout) view.findViewById(R.id.dialog_view);
        // 页面中的LoadingView
        mLoadingView = (LVCircularRing) view.findViewById(R.id.lv_circularring);
        // 页面中显示文本
        loadingText = (TextView) view.findViewById(R.id.loading_text);
        if ("".equals(msg) || null == msg) {
            loadingText.setVisibility(View.GONE);
        } else {
            // 显示文本
            loadingText.setText(msg);
        }

    }

    @Override
    public void show() {
        if(null == mLoadingDialog)
        {
            // 创建自定义样式的Dialog
            mLoadingDialog = new Dialog(AppManager.getCurrentActivity(), R.style.dialog_style_nodarken);
            // 设置返回键无效
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setContentView(main_view, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
        }

        if (!mLoadingDialog.isShowing()) {
            mLoadingView.startAnim();
            mLoadingDialog.show();

        }
    }

    public void setText(String text)
    {
        loadingText.setVisibility(View.VISIBLE);
        loadingText.setText(text);
        loadingText.invalidate();
    }

    @Override
    public void close() {
        if (mLoadingDialog != null) {
            mLoadingView.stopAnim();
            try {
                mLoadingDialog.cancel();

                mLoadingDialog.dismiss();
                //mLoadingView = null;
                // mLoadingDialog = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void setLoadingText(String loadmsg)
    {
        loadingText.setText(loadmsg);
    }
}
