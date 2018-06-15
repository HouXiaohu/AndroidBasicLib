package com.hxh.component.basicore;

import android.app.Application;
import android.content.Context;
import android.support.v4.util.ArrayMap;

import com.hxh.component.basicore.Base.app.provider.ActivityLifecycle;
import com.hxh.component.basicore.Base.app.provider.AppComponentCallbacks;
import com.hxh.component.basicore.Base.app.provider.AppProvider;
import com.hxh.component.basicore.Base.app.provider.UIProvider;
import com.hxh.component.basicore.component.imageLoader.ImageFactory;
import com.hxh.component.basicore.component.net.BaseAPI;
import com.hxh.component.basicore.component.net.NetProvider;
import com.hxh.component.basicore.component.net.api.RetrofitBaseApiImpl;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.log.DefalutLog;
import com.hxh.component.basicore.util.log.ILog;
import com.hxh.component.basicore.util.statistics.IStatistics;

/**
 * 关于此库的说明
 * AppCompartAutoLayoutActivity/AppCompartAutoLayoutFragment
 * 是Activity/Fragment 的顶级接口（可扩展，没有继承于任何第三方库）
 * <p>
 * <p>
 * NetProvider       主要是网络相关的东西
 * AppComponent      从这里面你能得到大部分所使用的组件
 * Utils             工具类
 * TopBarXXXActivity/Fragment   提供了一个简易版TopBar的Activyt或者Fragment，通过ActionBarConfig 配置
 * BaseRecyAdapter            提供了RecycleView的Adapter
 * <p>
 * <p>
 * MRecycleView 包下提供了一个 自带缓存，上拉，下拉..功能的RecycleView
 * <p>
 * ApiServices 注解提供了一个生成请求代码的功能
 * ApiServicesOtherPath 辅助ApiServices 进行使用
 * Safe   注解提供了一个安全的执行环境(保证不会崩溃)
 * SingleClick 注解提供了双击检测操作
 *
 * @author hxh
 * @更新时间 2017.7.27
 * @正在进行 1. Base类的完善封装(将支持 swipeBack)
 * 2.业务抽离为单独的Delagate (7.31 已完成)
 * 3.BaseViewHolder
 * 4. Glide 的封装
 */
public class CoreLib {

    /**
     * 得到App组件类
     */
    private AppProvider mAppComponent;


    private static CoreLib INSTANCE = null;


    public static CoreLib getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new CoreLib();
        }
        return INSTANCE;
    }

    private CoreLib init(CoreLib.Builder builer) {
        if (Utils.Text.isEmpty(builer.mAppcontext)) {
            throw new IllegalStateException("context must is ApplicationContext");
        }
        Utils.init(builer.mAppcontext);
        Utils.syncIsDebug(builer.mAppcontext);
        BaseAPI.getInstance().registerBaseApi(RetrofitBaseApiImpl.getInstance());

        mAppComponent = new AppProvider();
        if (null != builer.mLog) {
            mAppComponent.globalLogProvider(builer.mLog);
        } else {
            mAppComponent.globalLogProvider(DefalutLog.getInstance(builer.logEnable, builer.logWriteEnable, builer.logtag, builer.logSavePath));
        }

        mAppComponent.globalApplication((Application) builer.mAppcontext);
        mAppComponent.globalUIProvider(builer.mUiProvider);
        mAppComponent.globalNetProvider(builer.mNetProvider);
        mAppComponent.globalImageLoader(ImageFactory.getGlideLoader());
        mAppComponent.globalActivityLifeListener(new ActivityLifecycle());
        mAppComponent.globalAppListener(new AppComponentCallbacks());

        ((Application) builer.mAppcontext).registerActivityLifecycleCallbacks(mAppComponent.globalActivityLifeListener());
        ((Application) builer.mAppcontext).registerComponentCallbacks(mAppComponent.globalAppListener());

        mAppComponent.globalAppStatistics(builer.mStatisticsProvider);
        return this;
    }


    public static class Builder {
        public Context mAppcontext;
        public NetProvider mNetProvider;
        public UIProvider mUiProvider;

        public IStatistics mStatisticsProvider;
        /**
         * 配置Log的Tag名字 ， 配置Log的输出地址
         */
        public String logtag, logSavePath;
        /**
         * 配置是否显示Log  ，配置是否输出Log到日志文件中
         */
        public boolean logEnable, logWriteEnable;
        public ILog mLog;

        public Builder setContext(Context context) {
            if (context instanceof Application) {
                this.mAppcontext = context;
            } else {
                throw new IllegalStateException("context must is ApplicationContext");
            }
            return this;
        }


        public Builder setLogProvider(String logtag, boolean logEnable) {
            this.logEnable = logEnable;
            this.logtag = logtag;
            this.logWriteEnable = false;

            return this;
        }

        public Builder setLogProvider(String logtag, boolean logEnable, boolean logWriteEnable, String savePath) {
            this.logSavePath = savePath;
            this.logtag = logtag;
            this.logEnable = logEnable;
            this.logWriteEnable = logWriteEnable;
            return this;
        }

        public Builder setLogProvider(ILog ilog) {
            this.mLog = ilog;
            return this;
        }


        public Builder setNetProvider(NetProvider pro) {
            this.mNetProvider = pro;
            return this;
        }

        public Builder setUIProvider(UIProvider uipro) {
            this.mUiProvider = uipro;
            return this;
        }


        public Builder setAppStatisticsProvier(IStatistics statistics)
        {
            this.mStatisticsProvider = statistics;
            return this;
        }

        public CoreLib build() {
            CoreLib core = CoreLib.getInstance();
            core.init(this);

            return core;
        }

    }


    //region getter setter

    public boolean isLogEnable() {
        return mAppComponent.globalLogProvider().isEnableLog();
    }

    public boolean isLogWriteEnable() {
        return mAppComponent.globalLogProvider().isEnableWriteLog();
    }

    public NetProvider getNetProvider() {
        return mAppComponent.globalNetProvider();
    }

    public Context getAppcontext() {
        return mAppComponent.globalApplication();
    }

    public String getLogTag() {
        return mAppComponent.globalLogProvider().getLogTag();
    }

    public AppProvider getAppComponent() {
        if (null == mAppComponent) return new AppProvider();
        return mAppComponent;
    }


    public String getBaseUrl(String tagName) {
        return getNetProvider().getBaseUrl(tagName);
    }

    public String getBaseUrl() {
        return getNetProvider().getBaseUrl();
    }

    public void configDynamicHttpUrls(ArrayMap<String, String> urls) {
        this.getNetProvider().configDynamicHttpUrls(urls);
    }

    public UIProvider getUIProvider() {
        return mAppComponent.globalUIProvider();
    }

    public void setAppComponent(AppProvider mAppComponent) {
        this.mAppComponent = mAppComponent;
    }

    //endregion


}
