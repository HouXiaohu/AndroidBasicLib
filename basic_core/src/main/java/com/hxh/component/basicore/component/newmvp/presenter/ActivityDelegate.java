package com.hxh.component.basicore.component.newmvp.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hxh.component.basicore.component.newmvp.model.BaseModel;
import com.hxh.component.basicore.component.newmvp.model.IModel;
import com.hxh.component.basicore.component.newmvp.view.IView;
import com.hxh.component.basicore.util.AutoUtils;

import java.util.ArrayList;


/**
 * Created by hxh on 2017/7/31.
 */
public class ActivityDelegate<A extends AppCompatActivity> {

    public ActivityDelegate(IView view, A activity)
    {
        this.mView = view;
        this.mActivity = activity;
    }

    //我的页面
    private IView mView;
    //页面的Presenter
    private A mActivity;


    //存储当前的Model
    private ArrayList<BaseModel> mModels;

    public void onCreate(Bundle savedInstanceState) {
        if(null != mActivity && null != mView)
        {
            mActivity.setContentView(mView.onCreate(mActivity.getLayoutInflater(),null,savedInstanceState));
            mView.initView(savedInstanceState);
            AutoUtils.auto(mActivity);

        }
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

    public void onResume() {
        if(null != mModels)
        {
            for (BaseModel m : mModels) {
                m.newCompositeSubscription();
            }
        }
    }

    public void onPause() {
        if(null != mModels)
        {
            for (BaseModel m : mModels) {
                m.unSubscription();
            }
        }
    }

    public void onDestroy() {
        if(null != mModels)
        {
            for (IModel m : mModels) {
                m.release();
            }
        }
        mView = null;
        mActivity = null;
        if(null != mModels)   mModels.clear();
        mModels = null;
    }

}
