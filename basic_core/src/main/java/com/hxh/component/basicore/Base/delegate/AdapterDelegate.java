package com.hxh.component.basicore.Base.delegate;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxh.component.basicore.Base.delegate.interfaces.IAdapterRelated;
import com.hxh.component.basicore.Base.onRecyItemClick;
import com.hxh.component.basicore.Base.onRecyItemLongClick;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hxh on 2017/10/19.
 */

public class AdapterDelegate<T,H extends RecyclerView.ViewHolder> implements IAdapterRelated<T,H> {
    private ViewRelatedDelegate viewRelatedDelegate;
    private CheckNullDelegate mCheckNullDelegate;
    protected List<T> mDatas;
    private onRecyItemClick<T,H> mItemClickCallBack;
    private onRecyItemLongClick<T,H> mItemLongClickCallBack;
    private RecyclerView.Adapter<H> mAdapter;



    public AdapterDelegate(RecyclerView.Adapter<H> adapter)
    {
        this.mAdapter = adapter;
        mDatas  = new ArrayList<>();
        mCheckNullDelegate = new CheckNullDelegate();
        viewRelatedDelegate = new ViewRelatedDelegate();
    }

    //region view的操作
    @Override
    public void visibe(View view) {
        viewRelatedDelegate.visibe(view);
    }

    @Override
    public void visibe(View... views) {
        viewRelatedDelegate.visibe(views);
    }

    @Override
    public void gone(View view) {
        viewRelatedDelegate.gone(view);
    }

    @Override
    public void gone(View... views) {
        viewRelatedDelegate.gone(views);
    }

    @Override
    public void inVisibe(View view) {
        viewRelatedDelegate.inVisibe(view);
    }

    @Override
    public void inVisibe(View... views) {
        viewRelatedDelegate.inVisibe(views);
    }

    @Override
    public void toastShort(String msg) {
        viewRelatedDelegate.toastShort(msg);
    }

    @Override
    public void toastShort(String msg, int gravity) {
        viewRelatedDelegate.toastShort(msg,gravity);
    }

    @Override
    public void toastLong(String msg) {
        viewRelatedDelegate.toastShort(msg);
    }

    @Override
    public String getRES_String(int resId) {
        return viewRelatedDelegate.getRES_String(resId);
    }

    @Override
    public int getRES_Color(int resId) {
        return viewRelatedDelegate.getRES_Color(resId);
    }

    @Override
    public Drawable getRES_drawable(int resId) {
        return viewRelatedDelegate.getRES_drawable(resId);
    }

    @Override
    public boolean getRES_boolean(int resId) {
        return viewRelatedDelegate.getRES_boolean(resId);
    }

    @Override
    public float getRES_dimen(int resId) {
        return viewRelatedDelegate.getRES_dimen(resId);
    }

    //region TextView的操作
    @Override
    public String getText(EditText et) {
        return viewRelatedDelegate.getText(et);
    }

    @Override
    public String getText(TextView tv) {
        return viewRelatedDelegate.getText(tv);
    }

    @Override
    public String getText(EditText et, String defaulttext) {
        return viewRelatedDelegate.getText(et,defaulttext);
    }

    @Override
    public String getText(TextView tv, String defaulttext) {
        return viewRelatedDelegate.getText(tv,defaulttext);
    }

    @Override
    public String getText(TextView tv, int defaulttextResId) {
        return viewRelatedDelegate.getText(tv,defaulttextResId);
    }

    @Override
    public String getText(EditText et, int defaulttextResId) {
        return viewRelatedDelegate.getText(et,defaulttextResId);
    }

    @Override
    public String getHint(EditText et) {
        return viewRelatedDelegate.getHint(et);
    }

    @Override
    public String getHint(EditText et, String defaulttext) {
        return viewRelatedDelegate.getHint(et,defaulttext);
    }

    @Override
    public String getHint(EditText et, int defaulttextResId) {
        return viewRelatedDelegate.getHint(et,defaulttextResId);
    }

    @Override
    public <V extends TextView> void setText(V tv, String msg) {
        viewRelatedDelegate.setText(tv,msg);
    }

    @Override
    public <V extends TextView> void setText(V tv, int msgResId) {
        viewRelatedDelegate.setText(tv,msgResId);
    }


    @Override
    public void loadimg(ImageView iv, String url) {
        viewRelatedDelegate.loadimg(iv,url);
    }

    @Override
    public void loadimg(ImageView iv, String url, int errorImg) {
        viewRelatedDelegate.loadimg(iv,url,errorImg);
    }

    @Override
    public void loadimg(ImageView iv, int resid) {
        viewRelatedDelegate.loadimg(iv,resid);
    }

    @Override
    public boolean isEmpty(List list) {
        return mCheckNullDelegate.isEmpty(list);
    }

    @Override
    public boolean isEmpty(String msg) {
        return mCheckNullDelegate.isEmpty(msg);
    }

    @Override
    public boolean isEmpty(CharSequence str) {
        return mCheckNullDelegate.isEmpty(str);
    }

    @Override
    public boolean isEmpty(String... args) {
        return mCheckNullDelegate.isEmpty(args);
    }

    @Override
    public boolean isEmpty(EditText text) {
        return mCheckNullDelegate.isEmpty(text);
    }

    @Override
    public boolean isEmpty(EditText text, String tipmsg) {
        return mCheckNullDelegate.isEmpty(text,tipmsg);
    }

    @Override
    public boolean isEmpty(TextView tv) {
        return mCheckNullDelegate.isEmpty(tv);
    }

    @Override
    public boolean isEmpty(TextView tv, String msg) {
        return mCheckNullDelegate.isEmpty(tv,msg);
    }

    @Override
    public boolean isEmpty(Object obj) {
        return mCheckNullDelegate.isEmpty(obj);
    }
    //endregion
    //endregion

    @Override
    public void loadimg(ImageView iv, String ossStr, Class<?> classzz) {

    }

    @Override
    public T getDataAtPosition(int position)
    {
        if(null != mDatas&& 0!= mDatas.size())
        {
            return mDatas.get(position);
        }
        throw new IllegalStateException("you datas is null!");
    }

    @Override
    public List<T> getmDatas() {
        return mDatas !=null?mDatas:null;
    }

    @Override
    public int getmDataSize() {
        return mDatas!=null?mDatas.size():0;
    }

    @Override
    public void setDatas(List<T> mDatas) {
        if(null != mDatas)
        {
            this.mDatas.clear();
            this.mDatas.addAll(mDatas);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setDatas(T[] mDatas) {
        if(null != mDatas)
        {
            setDatas(Arrays.asList(mDatas));
        }
    }

    @Override
    public void addDatas(List<T> mDatas) {
        int presize = this.mDatas.size();
        if(null != mDatas && mDatas.size() >0)
        {
            if(this.mDatas == null)
            {
                this.mDatas = new ArrayList<>();
            }
            this.mDatas.addAll(mDatas);
            //通知有数据添加，实现动画
            mAdapter.notifyItemRangeInserted(presize,mDatas.size());
        }
        mAdapter. notifyDataSetChanged();
    }

    @Override
    public void addDatas(T[] mDatas) {
        if(null != mDatas && mDatas.length > 0)
        {
            addDatas(Arrays.asList(mDatas));
        }
    }

    @Override
    public void removeData(T data) {
        removeData(data,null);
    }

    @Override
    public void removeData(int position) {
        removeData(position,null);
    }

    @Override
    public void removeData(T data,Object payload) {
        if(null != mDatas)
        {
            if(mDatas.contains(data))
            {
                int position = mDatas.indexOf(data);
                mDatas.remove(position);
                mAdapter.notifyItemRemoved(position);
                if(position != mDatas.size()){ // 如果移除的是最后一个，忽略
                    mAdapter.notifyItemRangeChanged(position,mDatas.size()-position,payload);
                }

            }
        }
    }

    @Override
    public void removeData(int position,Object payload) {
        if(null != mDatas && mDatas.size() > position)
        {

            mDatas.remove(position);
            mAdapter.notifyItemRemoved(position);
            if(position != mDatas.size()){ // 如果移除的是最后一个，忽略
                mAdapter.notifyItemRangeChanged(position,mDatas.size()-position,payload);
            }
        }
    }

    @Override
    public void addData(T data) {
        if(null != mDatas && null != data)
        {
            mDatas.add(data);
            mAdapter.notifyItemInserted(this.mDatas.size());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void addData(int position, T data) {
        if(null != mDatas && null != data && mDatas.size() >position)
        {
            mDatas.remove(data);
            mDatas.add(position,data);
            mAdapter.notifyItemInserted(position);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void refreshDatas(List<T> datas) {
        if(null != datas && 0!=datas.size())
        {
            if (null == mDatas || mDatas.size() == 0) {
                setDatas(datas);
            } else {
                if(mDatas.size()<=10 && datas.size()<=10)
                {
                    setDatas(datas);
                    return;
                }
                int insertposition=0;
                int insertcount =0;

                for (int i = 0; i < datas.size(); i++) {
                    if(i <= mDatas.size()-1)
                    {
                        mDatas.set(i,datas.get(i));
                    }else{
                        mDatas.add(datas.get(i));
                        insertposition = i;
                        ++insertcount;
                    }
                }

                mAdapter.notifyItemRangeChanged(0,datas.size());
                if(insertcount>0)
                {
                    mAdapter. notifyItemRangeInserted(insertposition,insertcount);
                }
                mAdapter. notifyDataSetChanged();
            }

        }
    }

    @Override
    public void loadData(List<T> datas) {
        if(null != datas && 0!=datas.size())
        {
            int oldsize = mDatas.size();
            mDatas.addAll(datas);
            mAdapter.notifyItemRangeInserted(oldsize+1,datas.size());
        }
    }

    @Override
    public onRecyItemClick<T, H> getmItemClickCallBack() {
        return mItemClickCallBack;
    }

    @Override
    public void setmItemClickCallBack(onRecyItemClick<T, H> mItemClickCallBack) {
        this.mItemClickCallBack = mItemClickCallBack;
    }

    @Override
    public onRecyItemLongClick<T, H> getmItemLongClickCallBack() {
        return mItemLongClickCallBack;
    }

    @Override
    public void setmItemLongClickCallBack(onRecyItemLongClick<T, H> mItemLongClickCallBack) {
        this.mItemLongClickCallBack = mItemLongClickCallBack;
    }

    @Override
    public void clearDatas() {
        if (null != mDatas)
        {
            mDatas.clear();
        }
        mAdapter.  notifyDataSetChanged();
    }
}
