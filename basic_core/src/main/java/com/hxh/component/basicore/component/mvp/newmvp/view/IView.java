package com.hxh.component.basicore.component.mvp.newmvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 标题: IView.java
 * 作者: hxh
 * 日期: 2018/3/6 17:40
 * 描述: View的基础接口
 */
public interface IView {

    View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 相当于onCreate
     * @param saveInstanceState
     */
    void initView(Bundle saveInstanceState);


    //释放
    void release();

}
