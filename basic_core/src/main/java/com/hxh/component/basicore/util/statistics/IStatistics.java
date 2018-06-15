package com.hxh.component.basicore.util.statistics;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import java.util.Map;

/**
 * Created by hxh on 2018/6/14.
 */

public interface IStatistics {

    /**
     * 开启统计
     * @param initParam 初始化统计的一些参数，不同统计平台有不同的参数
     */
    void openStatistics(Map<String,String> initParam);

    /**
     * 账号统计
     * @param acount 需要记录的账号
     */
    void onAccountStatistics(String acount);

    /**
     * 账号统计
     * @param provider 需要记录的账号
     * @param acount 来源，例如通过第三方登录，如微信，qq
     */
    void onAccountStatistics(String provider,String acount);

    /**
     * 关闭账号统计
     * 在一些统计平台中，需要客户端主动调用关闭接口，方可关闭
     */
    void onCloseAccountStatistics();

    /**
     * Fragment页面统计
     * @param pagename 需要记录的页面
     */
    void onFragmentPageStatistics(String pagename);

    /**
     * Fragment页面统计结束
     * @param pagename 需要记录的页面
     */
    void onFragmentStatisticsEnd(String pagename);

    /**
     * Activity 页面统计
     * @param fragmentActivity
     */
    void onActivityPageStatistics(FragmentActivity fragmentActivity);

    /**
     * Activity 页面统计
     * @param fragmentActivity
     */
    void onActivityStatisticsEnd(FragmentActivity fragmentActivity);

    /**
     * 事件统计，例如我要统计分享的次数，等等
     * @param eventName
     * @param value
     */
    void onEventStatistics(String eventName,Map<String,String> value);

    /**
     * 事件统计，例如我要统计分享的次数，等等
     * @param eventName
     * @param value
     */
    void onEventStatistics(String eventName,Map<String,String> value,int du);

    /**
     * 启动App，并且记录每个Activity，最终总结出这个App的使用时长
     * @param context Acitivty
     */
    void onStartAppStatistics(Context context);

    /**
     * 关闭App，并且记录每个Activity，最终总结出这个App的使用时长
     * @param context
     */
    void onExitAppStatistics(Context context);

}
