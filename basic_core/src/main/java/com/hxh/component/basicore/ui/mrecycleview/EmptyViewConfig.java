package com.hxh.component.basicore.ui.mrecycleview;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.hxh.component.basicore.ui.mrecycleview.callback.EmpViewClickOtherPlaceRefreshCallBack;

/**
 * Created by hxh on 2017/12/4.
 */

public class EmptyViewConfig {

    public EmptyViewConfig(String tipmsg, int tipmsg_id, int tipIcon, String tipIcon_url, Bitmap tipIcon_map, Drawable tipIcon_drawable, View contentView, boolean isReversal,
                           boolean isClickOtherRefresh,
                           String UpdateTipMsg, int UpdateTipMsg_id) {
        this.tipmsg = tipmsg;
        this.tipmsg_id = tipmsg_id;
        this.tipIcon = tipIcon;
        this.tipIcon_url = tipIcon_url;
        this.tipIcon_map = tipIcon_map;
        this.tipIcon_drawable = tipIcon_drawable;
        this.contentView = contentView;
        this.isReversal = isReversal;
        this.isClickOtherRefresh = isClickOtherRefresh;

        this.mUpdateTipMsg = UpdateTipMsg;
        this.mUpdateTipMsg_id = UpdateTipMsg_id;
    }

    private String tipmsg;
    private int tipmsg_id;
    private boolean isClickOtherRefresh;
    private EmpViewClickOtherPlaceRefreshCallBack empViewClickOtherPlaceRefreshCallBack;
    private int tipIcon;
    private String tipIcon_url;
    private Bitmap tipIcon_map;
    private Drawable tipIcon_drawable;
    private String mUpdateTipMsg;
    private int mUpdateTipMsg_id;
    private View contentView;
    private boolean isReversal;//是否颠倒文字和图片的顺序

    public static class Build
    {
        private String tipmsg;
        private int tipmsg_id;
        private int tipIcon;
        private String tipIcon_url;
        private Bitmap tipIcon_map;
        private Drawable tipIcon_drawable;


        private View contentView;
        private boolean isReversal;//是否颠倒文字和图片的顺序

        private boolean isClickOtherRefresh;

        private String mUpdateTipMsg;
        private int mUpdateTipMsg_id;
        public Build setTipIcon(int resId)
        {
            this.tipIcon = resId;
            return this;
        }

        public Build setTipIcon(Bitmap icon)
        {
            this.tipIcon_map = icon;
            return this;
        }



        public Build setTipIcon(Drawable drawable)
        {
            this.tipIcon_drawable = drawable;
            return this;
        }

        public Build setTipIcon(String url)
        {
            this.tipIcon_url = url;
            return this;
        }

        public Build setTipMsg(String content)
        {
            this.tipmsg = content;
            return this;
        }

        public Build setTipMsg(int resId)
        {
            this.tipmsg_id = resId;
            return this;
        }

        public Build setUpdateTipMsg(int resId)
        {
            this.mUpdateTipMsg_id = resId;
            return this;
        }

        public Build setUpdateTipMsg(String msg)
        {
            this.mUpdateTipMsg = msg;
            return this;
        }

        public Build setTipView(View view)
        {
            this.contentView = view;
            return this;
        }

        public Build enableReversal()
        {
            this.isReversal = true;
            return this;
        }

        public Build enableRefreshWhenClickOtherPlace()
        {
            this.isClickOtherRefresh = true;
            return this;
        }


        public EmptyViewConfig build()
        {
            return new EmptyViewConfig(tipmsg,tipmsg_id,tipIcon,tipIcon_url,tipIcon_map,tipIcon_drawable,
                    contentView,isReversal,isClickOtherRefresh,mUpdateTipMsg,mUpdateTipMsg_id);
        }
    }

    public String getTipmsg() {
        return tipmsg;
    }

    public int getTipmsg_id() {
        return tipmsg_id;
    }

    public int getTipIcon() {
        return tipIcon;
    }

    public String getTipIcon_url() {
        return tipIcon_url;
    }

    public Bitmap getTipIcon_map() {
        return tipIcon_map;
    }

    public Drawable getTipIcon_drawable() {
        return tipIcon_drawable;
    }

    public View getContentView() {
        return contentView;
    }

    public boolean isReversal() {
        return isReversal;
    }

    public boolean isClickOtherRefresh() {
        return isClickOtherRefresh;
    }

    public EmpViewClickOtherPlaceRefreshCallBack getEmpViewClickOtherPlaceRefreshCallBack() {
        return empViewClickOtherPlaceRefreshCallBack;
    }

    public String getUpdateTipMsg() {
        return mUpdateTipMsg;
    }

    public int getUpdateTipMsg_id() {
        return mUpdateTipMsg_id;
    }

    public void setEmpViewClickOtherPlaceRefreshCallBack(EmpViewClickOtherPlaceRefreshCallBack empViewClickOtherPlaceRefreshCallBack) {
        this.empViewClickOtherPlaceRefreshCallBack = empViewClickOtherPlaceRefreshCallBack;
    }
}
