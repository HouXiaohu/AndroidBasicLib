package com.hxh.component.basicore.component.mvp.newmvp.presenter;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hxh.component.basicore.Base.delegate.CheckNullDelegate;
import com.hxh.component.basicore.Base.delegate.IntentDelegate;
import com.hxh.component.basicore.Base.delegate.interfaces.IIntentRelated;
import com.hxh.component.basicore.Base.delegate.interfaces.ICheckNullRelated;
import com.hxh.component.basicore.Base.view.AppCompartAutoLayoutFragment;
import com.hxh.component.basicore.component.mvp.newmvp.model.BaseModel;
import com.hxh.component.basicore.component.mvp.newmvp.view.IView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxh on 2018/3/6.
 */

public abstract class BaseFragmentPresenter<V extends IView> extends AppCompartAutoLayoutFragment implements
        IPresenter<V>
        , IIntentRelated
        , ICheckNullRelated {

    protected V mView;
    private IntentDelegate mParceableDelegate;
    private FragmentDelegate mFragmentDelegate;
    private CheckNullDelegate mCheckNullRelated;
    
    public BaseFragmentPresenter() {

        try {
            mView = getV().newInstance();
            mFragmentDelegate = new FragmentDelegate(mView);
         
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }



    //region 生命周期

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentDelegate.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParceableDelegate = new IntentDelegate(getArguments());
        mCheckNullRelated = new CheckNullDelegate();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return mFragmentDelegate.onCrateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mFragmentDelegate.onVisible();
        mFragmentDelegate.setEnableLazyLoad(isLazyLoad());
        mFragmentDelegate.onSupportVisible();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFragmentDelegate.onDetach();
        mFragmentDelegate = null;
        mView = null;
        mParceableDelegate = null;
    }


    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
        mFragmentDelegate.onInVisible();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFragmentDelegate.onDestroy();
    }


    @Nullable
    @Override
    public View getView() {
        return mFragmentDelegate.rootView;
    }

    public Context getmContext() {
        return mFragmentDelegate.getmContext();
    }

    private void registerModel(BaseModel model) {
        mFragmentDelegate.registerModel(model);
    }

    /**
     * 创建一个Model,这个Model需是IModel的子类
     *
     * @param classzz
     * @param <M>
     * @return
     */
    protected <M> M createModel(Class<M> classzz) {
        return (M) mFragmentDelegate.createModel(classzz);
    }

    /**
     * 是否开启懒加载
     */
    private boolean isOpenLazy = true;

    public boolean isLazyLoad() {
        return this.isOpenLazy;
    }
    //endregion

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
        return mParceableDelegate.getExtra_Int(key, defaultvalue);
    }

    @Override
    public Boolean getExtra_Boolean(String key, boolean defaultvalue) {
        return mParceableDelegate.getExtra_Boolean(key, defaultvalue);
    }

    @Override
    public Double getExtra_Double(String key, double defaultvalue) {
        return mParceableDelegate.getExtra_Double(key, defaultvalue);
    }

    @Override
    public Float getExtra_Float(String key, float defaultvalue) {
        return mParceableDelegate.getExtra_Float(key, defaultvalue);
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
        return mParceableDelegate.getExtra_Parceable(key, defaultvalue);
    }

    @Override
    public String getExtra_String(String key, String defaultvalue) {
        return mParceableDelegate.getExtra_String(key, defaultvalue);
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
        mParceableDelegate.startActivity(classzz, key, value);
    }

    @Override
    public void startActivityForResult(Class classzz) {
        mParceableDelegate.startActivityForResult(classzz);
    }

    @Override
    public void startActivity(Class classzz, Bundle data) {
        mParceableDelegate.startActivity(classzz, data);
    }

    @Override
    public void startActivityForResult(Class classzz, Bundle data) {
        mParceableDelegate.startActivityForResult(classzz, data);
    }
    //endregion

    //region 非空

    @Override
    public boolean isEmpty(List list) {
        return mCheckNullRelated.isEmpty(list);
    }

    @Override
    public boolean isEmpty(String msg) {
        return mCheckNullRelated.isEmpty(msg);
    }

    @Override
    public boolean isEmpty(CharSequence str) {
        return mCheckNullRelated.isEmpty(str);
    }

    @Override
    public boolean isEmpty(String... args) {
        return mCheckNullRelated.isEmpty(args);
    }

    @Override
    public boolean isEmpty(EditText text) {
        return mCheckNullRelated.isEmpty(text);
    }

    @Override
    public boolean isEmpty(TextView tv, String msg) {
        return mCheckNullRelated.isEmpty(tv, msg);
    }

    @Override
    public boolean isEmpty(EditText text, String tipmsg) {
        return mCheckNullRelated.isEmpty(text, tipmsg);
    }

    @Override
    public boolean isEmpty(TextView tv) {
        return mCheckNullRelated.isEmpty(tv);
    }

    @Override
    public boolean isEmpty(Object obj) {
        return mCheckNullRelated.isEmpty(obj);
    }
    
    //endregion 
    
    
    
    
    
}
