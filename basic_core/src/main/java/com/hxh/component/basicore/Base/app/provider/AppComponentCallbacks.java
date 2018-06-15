package com.hxh.component.basicore.Base.app.provider;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

import com.hxh.component.basicore.CoreLib;

/**
 * Created by hxh on 2018/5/8.
 */

public class AppComponentCallbacks implements ComponentCallbacks2 {

    @Override
    public void onTrimMemory(int level) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onLowMemory() {
        //内存不足时候，请求清除图片的内存缓存
        CoreLib.getInstance().getAppComponent().globalImageLoader().clearMemoryCache(
                CoreLib.getInstance().getAppcontext()
        );

    }
}
