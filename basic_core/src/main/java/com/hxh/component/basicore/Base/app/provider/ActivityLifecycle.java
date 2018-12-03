package com.hxh.component.basicore.Base.app.provider;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.statistics.IStatistics;
import com.umeng.analytics.MobclickAgent;

/**
 * 所有的Activity都会走这个接口！ 简化BaseActivity.. 一些逻辑，集中抽取到此类
 * 1. 统一管理Activity
 * 2. 增加u盟统计
 * 3. 后续更新.....
 */
public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        AppManager.addActivity(activity);
        checkIsEnableImmeriveMode(activity);
        checkIsEnableStatics(activity);
    }




    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        openOrCloseStatics(activity, true);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        openOrCloseStatics(activity, false);
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }


    @Override
    public void onActivityDestroyed(Activity activity) {
        AppManager.removeActivity(activity);
    }


    private void checkIsEnableImmeriveMode(Activity activity) {
        if (null != CoreLib.getInstance().getAppComponent().globalUIProvider().getActionBarProvider()) {
            if (CoreLib.getInstance().getAppComponent().globalUIProvider().getActionBarProvider().isEnableImmeriveMode()) {
                Utils.SystemUtil.enableImmersiveMode(activity);
            }
        }

    }


    /**
     * 在仅有Activity的应用中，SDK自动帮助开发者调用了
     * onPageStart/onPageEnd方法，并把Activity 类名作为页面名称统计。
     * 但是在包含fragment的程序中我们希望统计更详细的页面，所以需要自己
     * 调用onPageStart/onPageEnd方法做更详细的统计。
     * 首先，需要在程序入口处，调用 MobclickAgent.openActivityDurationTrack(false) 禁止默认的页面统计功能，
     * 这样将不会再自动统计Activity页面。
     *
     * @param activity
     */
    private void checkIsEnableStatics(Activity activity) {
        if (null != CoreLib.getInstance().getAppComponent().globalAppStatistics()) {
            MobclickAgent.openActivityDurationTrack(false);
        }
    }


    //region --------------------------------统计----------------------------
    //region UMeng 单Acitivyt格式示例
    //    public void onResume() {
    //        super.onResume();
    //        MobclickAgent.onPageStart("SplashScreen"); //手动统计页面("SplashScreen"为页面名称，可自定义)
    //        MobclickAgent.onResume(this); //统计时长
    //    }
    //
    //    public void onPause() {
    //        super.onPause();
    //        MobclickAgent.onPageEnd("SplashScreen"); //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
    //        MobclickAgent.onPause(this);
    //    }
    //endregion

    //region UMeng FragmentAcitivyt+Fragment格式示例
    //    Activity:
    //    public void onResume() {
    //        super.onResume();
    //        MobclickAgent.onResume(this); //统计时长
    //    }
    //    public void onPause() {
    //        super.onPause();
    //        MobclickAgent.onPause(this); //统计时长
    //    }
    //
    //    Fragment:
    //    public void onResume() {
    //        super.onResume();
    //        MobclickAgent.onPageStart("MainScreen"); //统计页面("MainScreen"为页面名称，可自定义)
    //    }
    //    public void onPause() {
    //        super.onPause();
    //        MobclickAgent.onPageEnd("MainScreen");
    //    }
    //endregion

    private void openOrCloseStatics(Activity activity, boolean isopen) {
        IStatistics sta = CoreLib.getInstance().getAppComponent().globalAppStatistics();
        if (activity instanceof FragmentActivity) {
            if (isopen) {
                if (null != ((FragmentActivity) activity).getSupportFragmentManager().getFragments() && ((FragmentActivity) activity).getSupportFragmentManager().getFragments().size() == 0) {
                    sta.onFragmentPageStatistics(activity.getClass().getSimpleName());
                    sta.onActivityPageStatistics((FragmentActivity) activity);
                } else {
                    sta.onActivityPageStatistics((FragmentActivity) activity);
                }
            } else {
                if (null != ((FragmentActivity) activity).getSupportFragmentManager().getFragments() && ((FragmentActivity) activity).getSupportFragmentManager().getFragments().size() == 0) {
                    sta.onFragmentStatisticsEnd(activity.getClass().getSimpleName());
                    sta.onActivityStatisticsEnd((FragmentActivity) activity);
                } else {
                    sta.onActivityStatisticsEnd((FragmentActivity) activity);
                }
            }
        }

    }
    //endregion
}
