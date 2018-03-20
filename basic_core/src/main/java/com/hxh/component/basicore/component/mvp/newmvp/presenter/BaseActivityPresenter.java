package com.hxh.component.basicore.component.mvp.newmvp.presenter;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.hxh.component.basicore.Base.delegate.IntentDelegate;
import com.hxh.component.basicore.Base.delegate.interfaces.IIntentRelated;
import com.hxh.component.basicore.Base.view.AppCompartAutoLayoutActivity;
import com.hxh.component.basicore.component.mvp.newmvp.model.BaseModel;
import com.hxh.component.basicore.component.mvp.newmvp.view.IView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by hxh on 2018/3/6.
 */

public abstract class BaseActivityPresenter<V extends IView>
        extends AppCompartAutoLayoutActivity
        implements
        IPresenter<V>
        ,IIntentRelated {


    protected V mView;
    private IntentDelegate mParceableDelegate;
    private ActivityDelegate mDelegate =null;


    public BaseActivityPresenter() {
        try {
            mView = getV().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate = new ActivityDelegate(mView,this);
        mDelegate.onCreate(savedInstanceState);
        mParceableDelegate = new IntentDelegate(getIntent().getExtras());
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDelegate.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDelegate.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelegate.onDestroy();
        mDelegate = null;
        mView = null;
        mParceableDelegate = null;
    }

    /**
     * 注册一个数据源
     * @param model
     */
    private void registerModel(BaseModel model)
    {
        mDelegate.registerModel(model);
    }

    /**
     * 创建一个Model,这个Model需是IModel的子类
     * @param classzz
     * @param <M>
     * @return
     */
    protected <M> M createModel(Class<M> classzz)
    {
        return (M) mDelegate.createModel(classzz);
    }


    @Override
    public <B> void dispatchResponseEvent(final String tag, final B baseBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                onModelResponse(tag,baseBean);
            }
        });
    }

    public abstract <B>void onModelResponse(String tag,B bean);

    public abstract Class<V> getV();

    //region parceable支持

    @Override
    public Parcelable getExtra_Parceable(String key) {
        return mParceableDelegate.getExtra_Parceable(key);
    }

    @Override
    public String getExtra_String(String key) {
        return mParceableDelegate.getExtra_String(key);
    }

    @Override
    public Integer getExtra_Int(String key, int defaultvalue) {
        return mParceableDelegate.getExtra_Int(key,defaultvalue);
    }

    @Override
    public Boolean getExtra_Boolean(String key, boolean defaultvalue) {
        return mParceableDelegate.getExtra_Boolean(key,defaultvalue);
    }

    @Override
    public Double getExtra_Double(String key, double defaultvalue) {
        return mParceableDelegate.getExtra_Double(key,defaultvalue);
    }

    @Override
    public Float getExtra_Float(String key, float defaultvalue) {
        return mParceableDelegate.getExtra_Float(key,defaultvalue);
    }

    @Override
    public Serializable getExtra_Seriallizable(String key) {
        return mParceableDelegate.getExtra_Seriallizable(key);
    }

    @Override
    public Parcelable getExtra_Parceable() {
        return mParceableDelegate.getExtra_Parceable();
    }

    @Override
    public Serializable getExtra_Seriallizable() {
        return mParceableDelegate.getExtra_Seriallizable();
    }

    @Override
    public ArrayList getExtra_ParceableArray(String key) {
        return mParceableDelegate.getExtra_ParceableArray(key);
    }

    @Override
    public ArrayList getExtra_ParceableArray() {
        return mParceableDelegate.getExtra_ParceableArray();
    }

    @Override
    public Integer getExtra_Int(int defaultvalue) {
        return mParceableDelegate.getExtra_Int(defaultvalue);
    }

    @Override
    public Boolean getExtra_Boolean(boolean defaultvalue) {
        return mParceableDelegate.getExtra_Boolean(defaultvalue);
    }

    @Override
    public Double getExtra_Double(double defaultvalue) {
        return mParceableDelegate.getExtra_Double(defaultvalue);
    }

    @Override
    public Float getExtra_Float(float defaultvalue) {
        return mParceableDelegate.getExtra_Float(defaultvalue);
    }

    @Override
    public Parcelable getExtra_Parceable(String key, Parcelable defaultvalue) {
        return mParceableDelegate.getExtra_Parceable(key,defaultvalue);
    }

    @Override
    public String getExtra_String(String key, String defaultvalue) {
        return mParceableDelegate.getExtra_String(key,defaultvalue);
    }

    @Override
    public String getExtra_StringDefa(String defavalue) {
        return mParceableDelegate.getExtra_StringDefa(defavalue);
    }

    @Override
    public Parcelable getExtra_Parceable(Parcelable defaultvalue) {
        return mParceableDelegate.getExtra_Parceable(defaultvalue);
    }

    @Override
    public Serializable getExtra_Seriallizable(Serializable defaultvalue) {
        return mParceableDelegate.getExtra_Seriallizable(defaultvalue);
    }

    @Override
    public String getExtra_String() {
        return mParceableDelegate.getExtra_String();
    }


    @Override
    public void startActivity(Class classzz) {
        mParceableDelegate.startActivity(classzz);
    }

    @Override
    public void startActivity(Class classzz, String key, String value) {
        mParceableDelegate.startActivity(classzz,key,value);
    }

    @Override
    public void startActivityForResult(Class classzz) {
        mParceableDelegate.startActivityForResult(classzz);
    }

    @Override
    public void startActivity(Class classzz, Bundle data) {
        mParceableDelegate.startActivity(classzz,data);
    }

    @Override
    public void startActivityForResult(Class classzz, Bundle data) {
        mParceableDelegate.startActivityForResult(classzz,data);
    }
    //endregion

}
