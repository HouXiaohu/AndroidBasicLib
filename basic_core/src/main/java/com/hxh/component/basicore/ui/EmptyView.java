package com.hxh.component.basicore.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import android.support.v4.util.ArraySet;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxh.component.basicore.R;
import com.hxh.component.basicore.ui.recycleview.mrecycleview.EmptyViewConfig;
import com.hxh.component.basicore.ui.recycleview.mrecycleview.callback.EmpViewClickOtherPlaceRefreshCallBack;
import com.hxh.component.basicore.util.Utils;

/**
 * Created by hxh on 2017/6/16.
 */
public class EmptyView extends RelativeLayout implements View.OnClickListener {

    public static final int IMG_TIP_UPDATE = 0x1;
    public static final int TIP_IMG_UPDATE = 0x2;

    private View mView;
    private RelativeLayout view_request;
    private TextView tv_tip;
    private TextView tv_isrefresh;
    private ImageView iv_emptyicon;
    private boolean isEnableRefresh;
    private ArraySet<EmpViewClickOtherPlaceRefreshCallBack> mEmpViewClickOtherPlaceRefreshCallBacks;
    private EmptyViewConfig emptyViewConfig;

    public EmptyView(Context context) {
        super(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mView = LayoutInflater.from(context).inflate(R.layout.layout_emptyview, this, true);
        initView(mView);
    }

    public void setEmptyViewConfig(EmptyViewConfig config) {
        this.emptyViewConfig = config;
        if (null != emptyViewConfig) {
            settingEmptyView();
        }
    }

    private void settingEmptyView() {
        if (null != emptyViewConfig.getContentView()) {
            removeAllViews();
            addView(emptyViewConfig.getContentView());
           // removeView(mView);
        } else {
            //region 图片
            if (-1 != emptyViewConfig.getTipIcon() && 0 != emptyViewConfig.getTipIcon()) {
                setEmptyIcon(emptyViewConfig.getTipIcon());
            } else if (null != emptyViewConfig.getTipIcon_url()) {
                setEmptyIcon(emptyViewConfig.getTipIcon_url());
            } else if (null != emptyViewConfig.getTipIcon_map()) {
                setEmptyIcon(emptyViewConfig.getTipIcon_map());
            } else if (null != emptyViewConfig.getTipIcon_drawable()) {
                setEmptyIcon(emptyViewConfig.getTipIcon_drawable());
            }
            //endregion
            //region 文字
            if (!Utils.Text.isEmpty(emptyViewConfig.getTipmsg())) {
                setTipMsg(emptyViewConfig.getTipmsg());
            } else if (-1 != emptyViewConfig.getTipmsg_id() && 0 != emptyViewConfig.getTipmsg_id()) {
                setTipMsg(emptyViewConfig.getTipmsg_id());
            }
            //endregion

            if (emptyViewConfig.isReversal()) {

            }

            if (emptyViewConfig.isClickOtherRefresh()) {
                if (-1 != emptyViewConfig.getUpdateTipMsg_id() && 0 != emptyViewConfig.getUpdateTipMsg_id()) {
                    setUpdateTip(emptyViewConfig.getUpdateTipMsg_id());
                } else {
                    setUpdateTip(emptyViewConfig.getUpdateTipMsg());
                }
                setEmpViewClickOtherPlaceRefreshCallBack(emptyViewConfig.getEmpViewClickOtherPlaceRefreshCallBack());
            }

        }


    }

    public void setEmpViewClickOtherPlaceRefreshCallBack(EmpViewClickOtherPlaceRefreshCallBack callback) {
        if (null == mEmpViewClickOtherPlaceRefreshCallBacks)
            mEmpViewClickOtherPlaceRefreshCallBacks = new ArraySet<>();
        if (null != callback)
            this.mEmpViewClickOtherPlaceRefreshCallBacks.add(callback);
    }

    private void initView(View mView) {
        view_request = (RelativeLayout) mView.findViewById(R.id.view_request);
        tv_tip = (TextView) mView.findViewById(R.id.tv_tip);
        tv_isrefresh = (TextView) mView.findViewById(R.id.tv_isrefresh);
        iv_emptyicon = (ImageView) mView.findViewById(R.id.iv_emptyicon);

    }

    public void setTipMsg(String msg) {

        tv_tip.setText(Utils.Text.isEmpty(msg) ? "" : msg);
    }

    public void setTipMsg(int resid) {
        tv_tip.setText(resid);
    }

    public void setEmptyIcon(int resid) {
        iv_emptyicon.setImageResource(resid);
    }

    public void setEmptyIcon(Bitmap map) {
        iv_emptyicon.setImageBitmap(map);
    }

    public void setEmptyIcon(String url) {
        Utils.ImageLoadUtils.loadimg(iv_emptyicon, url);
    }

    public void setEmptyIcon(Drawable drawable) {

        iv_emptyicon.setImageDrawable(drawable);
    }


    public void show() {
        if (null != mView) {
            mView.setVisibility(VISIBLE);
        }

    }

    public void hide() {
        if (null != mView) {
            mView.setVisibility(GONE);
        }

    }

    public void setUpdateTip(String msg) {
        tv_isrefresh.setVisibility(VISIBLE);
        tv_isrefresh.setText(Utils.Text.isEmpty(msg) ? "点击空白处刷新" : msg);
        tv_isrefresh.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_isrefresh.setTextColor(Utils.Resource.getColor(R.color.google_blue));
        view_request.setOnClickListener(this);
    }

    public void setUpdateTip(int resid) {
        tv_isrefresh.setVisibility(VISIBLE);
        tv_isrefresh.setText(resid);
        tv_isrefresh.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        tv_isrefresh.setTextColor(Utils.Resource.getColor(R.color.google_blue));

        view_request.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.view_request) {
            if (null != mEmpViewClickOtherPlaceRefreshCallBacks) {
                for (EmpViewClickOtherPlaceRefreshCallBack it : mEmpViewClickOtherPlaceRefreshCallBacks) {
                    it.onClick();
                }
            }
        }
    }


}
