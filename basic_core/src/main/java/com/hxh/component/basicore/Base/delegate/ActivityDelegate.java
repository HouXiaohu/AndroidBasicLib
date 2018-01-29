package com.hxh.component.basicore.Base.delegate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;

import com.hxh.component.basicore.mvp.persenter.BasePresenter;
import com.hxh.component.basicore.mvp.persenter.IPresenter;
import com.hxh.component.basicore.mvp.view.IView;
import com.hxh.component.basicore.util.AutoUtils;

/**
 * Created by hxh on 2017/7/31.
 */
public class ActivityDelegate<P extends IPresenter> {

    public ActivityDelegate(IView<P> activity)
    {
        this.mActivity = activity;
    }

    private IView<P> mActivity;

    private View rootView;
    //存储当前的View
    private SparseArray<View> mViews;
    private P p;




    public View onCreate(Bundle savedInstanceState) {

        mViews = new SparseArray<>();

        if(mActivity.getLayoutId() > 0)
        {
            //如果开启toolbar
            rootView = LayoutInflater.from(((AppCompatActivity) mActivity)).inflate(mActivity.getLayoutId(), null);
            ((AppCompatActivity) mActivity).setContentView(rootView);
            //AppManager.addActivity(this);
            AutoUtils.auto(((AppCompatActivity) mActivity));

        }

        mActivity.initData(savedInstanceState);

        return rootView;
    }




    public P getP()
    {
        if(null == p) {
            p = mActivity.newP();
            if (p != null)
            {
                p.AttachView(mActivity);
            }
        }
        return p;
    }



    public void onResume() {
        if(null != ((BasePresenter) getP()))((BasePresenter) getP()).newCompositeSubscription();
    }


    public void onPause() {
        if(null != ((BasePresenter) getP()))((BasePresenter) getP()).unSubscription();
    }


    public void onDestroy() {

        if(null !=p)  {
            p.DetachView();
            p = null;
        }
        mViews.clear();
        mViews = null;
    }


    public <V extends View>V findViewBy(int resId)
    {
        View view = mViews.get(resId);
        if(null == view)
        {
            view = ((AppCompatActivity) mActivity).findViewById(resId);
            mViews.put(resId,view);
        }
        return (V) view;
    }

}
