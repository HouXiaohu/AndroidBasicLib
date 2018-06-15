package com.hxh.component.basicore.Base.app;

import com.hxh.component.basicore.Base.app.provider.AppProvider;
import com.hxh.component.basicore.CoreLib;

/**
 * 标题: Application 委托类
 * 作者: hxh
 * 日期: 2017/11/7 19:03
 * 描述:
 */
public class AppDelegate implements IApp {



    private AppProvider mAppComponent;

    public void onCreate(AppInitDelegate initDelegate) {
        initDelegate.init();
    }

    @Override
    public AppProvider getAppComponent() {
        return CoreLib.getInstance().getAppComponent();
    }

    public void onStop() {
        this.mAppComponent.onStop();
        this.mAppComponent = null;
    }

}
