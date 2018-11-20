package com.hxh.component.basicore.component.newmvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hxh.component.basicore.component.newmvp.model.BaseModel;
import com.hxh.component.basicore.component.newmvp.model.IModel;
import com.hxh.component.basicore.component.newmvp.view.IView;
import com.hxh.component.basicore.util.AutoUtils;

import java.util.ArrayList;

/**
 * 应该只专注于Fragment，如ToolBar应该交由ToolBarDelegate去做
 */
public class FragmentDelegate<F extends Fragment> {
    public FragmentDelegate(IView view) {
        this.mView = view;
    }

    //当前的页面
    private IView mView;
    //存储当前的Model
    private ArrayList<BaseModel> mModels;

    private Context context;
    protected View rootView;//代表当前VIew
    private boolean isLazyLoad;
    private Bundle savedInstanceState;
    private boolean isFirstVisible = true;


    //region 生命周期
    public View onCrateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == rootView) {
            rootView = mView.onCreate(inflater, container, savedInstanceState);
            AutoUtils.auto(rootView);
        } else {
            //当重复加载时候，就从跟布局中删除这个布局
            ViewGroup vp = (ViewGroup) rootView.getParent();
            if (null != vp) {
                vp.removeView(rootView);
            }
        }
        this.savedInstanceState = savedInstanceState;
        return rootView;
    }

    /**
     * 正常的Fragment需要实现并调用这个方法
     *
     * @param savedInstanceState
     */
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        mView.initView(savedInstanceState);
    }

    public void onAttach(Context context) {
        this.context = context;
    }

    public void registerModel(BaseModel model)
    {
        if(null == mModels)
        {
            mModels = new ArrayList<>();
        }
        this.mModels.add(model);
    }


    protected <M> M createModel(Class<M> classzz)
    {
        String className = classzz.getSimpleName();

        if(className.contains("IModel"))
        {
            throw new IllegalStateException("classzz can't is IModel!");
        }

        M m = null;
        try {
            m = classzz.newInstance();
            registerModel((BaseModel)m);
            return m;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return m;
    }


    public void onVisible()
    {
        if(null != mModels)
        {
            for (BaseModel m : mModels) {
                m.newCompositeSubscription();
            }
        }

    }


    public void onInVisible() {
        if(null != mModels)
        {
            for (BaseModel m : mModels) {
                m.unSubscription();
            }
        }
    }


    public void onDetach() {

        if(null != mModels)
        {
            for (IModel m : mModels) {
                m.release();
            }
        }
        mView.release();
        mView = null;

        rootView = null;
        context = null;
        if(null != mModels)mModels.clear();
        mModels = null;

    }

    /**
     * onDestory 什么也不做，按照声明周期来说，onDetach()是Fragment的最后一步，但是如果直接在onDestory()
     * 中释放了view的话，那么就会造成，处于这俩之间的事件，调用getView(),getP()崩溃的情况
     */
    public void onDestroy() {

    }

    /**
     * 懒加载的需要实现并调用这个方法
     */
    public void onSupportVisible() {

        if (isFirstVisible && isLazyLoad) {
            mView.initView(savedInstanceState);
            isFirstVisible = false;
        }
    }

    //endregion

    /**
     * 如果你是懒加载的fragment，必须显示指定
     *
     * @param islazy
     */
    public void setEnableLazyLoad(boolean islazy) {
        this.isLazyLoad = islazy;
    }


    public Context getContext() {
        return getmContext();
    }

    public Context getmContext() {
        return context;
    }
}
