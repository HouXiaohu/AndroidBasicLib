package com.hxh.component.basicore.Base.app.provider;

import android.app.Application;

import com.hxh.component.basicore.Base.app.BugManager;
import com.hxh.component.basicore.component.imageLoader.IImageLoader;
import com.hxh.component.basicore.component.net.BaseAPI;
import com.hxh.component.basicore.component.net.NetProvider;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.log.ILog;
import com.hxh.component.basicore.util.statistics.IStatistics;

/**
 * Created by hxh on 2018/5/8.
 */

public class AppProvider {

    private NetProvider netProvider;
    private IImageLoader imageLoader;
    private BugManager bugManager;
    private UIProvider uiProvider;

    private Application application;
    /**
     * 全局Activity声明周期管理
     */
    private ActivityLifecycle activityLifecycle;
    /**
     * 全局回调
     */
    private AppComponentCallbacks appComponentCallbacks;
    /**
     * 全局日志工具
     */
    private ILog mILogProvider;
    /**
     * 全局统计工具
     */
    private IStatistics mIStatistics;

    public IStatistics globalAppStatistics()
    {
        return mIStatistics;
    }

    public void globalAppStatistics(IStatistics statistics)
    {
        this.mIStatistics = statistics;
    }



    /**
     * 全局的 网络 提供者
     * @return
     */
    public NetProvider globalNetProvider()
    {
        return netProvider;
    }

    public void globalNetProvider(NetProvider netProvider)
    {
        this.netProvider = netProvider;
        if(null != netProvider)
        {
            BaseAPI.getInstance().register(this.netProvider);
        }
    }


    /**
     * 全局的 ImageLoader
     * @return
     */
    public IImageLoader globalImageLoader()
    {
        return imageLoader;
    }

    public void globalImageLoader(IImageLoader loader)
    {
        this.imageLoader = loader;
    }


    /**
     * 全局的 Application
     * @return
     */
    public Application globalApplication()
    {
        return application;
    }

    public void globalApplication(Application application)
    {
        this.application = application;
        registerAppComponentCallback();
        registerActivityLifecycleCallback();
    }

    /**
     * bug 管理器
     * @return
     */
    public BugManager globalBugManager()
    {
        return bugManager;
    }

    public void globalBugManager(BugManager bugManager)
    {
        this.bugManager = bugManager;
    }

    /**
     * 全局的UI配置
     * @return
     */
    public UIProvider globalUIProvider()
    {
        return uiProvider;
    }

    public void globalUIProvider(UIProvider uiProvider)
    {
        this.uiProvider = uiProvider;
    }


    /**
     * 全局的Application监听器
     * @return
     */
    public AppComponentCallbacks globalAppListener()
    {
        return appComponentCallbacks;
    }

    public void globalAppListener(AppComponentCallbacks callbacks)
    {
        this.appComponentCallbacks = callbacks;
        registerAppComponentCallback();
    }

    /**
     * 全局的Activity生命周期监听器
     * @return
     */
    public ActivityLifecycle globalActivityLifeListener()
    {
        return activityLifecycle;
    }

    public void globalActivityLifeListener(ActivityLifecycle lis)
    {
        this.activityLifecycle = lis;
        registerActivityLifecycleCallback();
    }


    /**
     * 全局的Log管理器
     * @return
     */
    public ILog globalLogProvider()
    {
        return mILogProvider;
    }

    public void globalLogProvider(ILog logprovider)
    {
        if(!Utils.Text.isEmpty(logprovider))
        {
            this.mILogProvider = logprovider;
            this.mILogProvider.init();
        }
    }


    private void registerAppComponentCallback()
    {
        if(null != application && null != appComponentCallbacks)
        {
            this.application.registerComponentCallbacks(appComponentCallbacks);
        }

    }

    private void registerActivityLifecycleCallback()
    {
        if(null != application && null != activityLifecycle)
        {
            this.application.registerActivityLifecycleCallbacks(activityLifecycle);
        }
    }

    public void onStop()
    {
        if(null != appComponentCallbacks)
        {
            this.application.unregisterComponentCallbacks(appComponentCallbacks);
        }
        if(null != activityLifecycle)
        {
            this.application.unregisterActivityLifecycleCallbacks(activityLifecycle);
        }
        this.activityLifecycle = null;
        this.appComponentCallbacks = null;

        this.application = null;
    }
}
