package com.hxh.component.basicore.util.statistics;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;

import com.hxh.component.basicore.util.Utils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.Map;

/**
 * 标题: UmengStatistics.java
 * 作者: hxh
 * 日期: 2018/6/14 11:43
 * 描述:
 * UMeng文档： https://developer.umeng.com/docs/66632/detail/66889
 *
 *
 * ------------------------------初始化操作----------------------
 * 方法1 调用openStatistics时候传入 AppKey和Channel
 * 方法2 在App Module下面的清单文件中，加入如下配置：
 *  <meta-data android:value="YOUR_APP_KEY" android:name="UMENG_APPKEY"/>
 *  <meta-data android:value="Channel ID" android:name="UMENG_CHANNEL"/>
 * --------------------------------------------------------------
 *
 * 2. UMeng 统计中，Activity统计的为App使用时长，如果页面是FragmentActivity+Fragment构成，那么
 * 如果需要进行页面统计，那么需要在每个Fragment的onResume()和onPause()中添加统计代码，另外Activity中的
 * onResume()和onPause()也要加统计代码
 *
 *
 *
 */
public class UmengStatistics implements IStatistics
{
    public static String SetSessionContinueMillis = "SessionContinueMillis";
    public static String SetSecret = "Secret";
    public static String SetAppkey = "appkey";
    public static String SetChannel = "channel";



    @Override
    public void openStatistics(Map<String, String> initParam) {
        if(null != initParam)
        {
            if(!isEmpty(initParam.get(SetAppkey)) && !isEmpty(initParam.get(SetChannel)))
            {
                UMConfigure.init(Utils.getApplicationContext(),initParam.get(SetAppkey), initParam.get(SetChannel),UMConfigure.DEVICE_TYPE_PHONE,null);
            }else
                UMConfigure.init(Utils.getApplicationContext(),UMConfigure.DEVICE_TYPE_PHONE,null);


            if(!isEmpty(initParam.get(SetSessionContinueMillis)))
                MobclickAgent.setSessionContinueMillis(Integer.valueOf(initParam.get(SetSessionContinueMillis)));

            if(!isEmpty(initParam.get(SetSecret)))
                MobclickAgent.setSecret(Utils.getApplicationContext(),initParam.get(SetSecret));


            UMConfigure.setLogEnabled(true);
        }
    }

    @Override
    public void onAccountStatistics(String acount) {
        MobclickAgent.onProfileSignIn(acount);
    }

    @Override
    public void onAccountStatistics(String provider, String acount) {
        MobclickAgent.onProfileSignIn(provider,acount);
    }

    @Override
    public void onCloseAccountStatistics() {
        MobclickAgent.onProfileSignOff();
    }

    @Override
    public void onFragmentPageStatistics(String pagename) {
        MobclickAgent.onPageStart(pagename);
    }


    @Override
    public void onFragmentStatisticsEnd(String pagename) {
        MobclickAgent.onPageEnd(pagename);
    }

    @Override
    public void onActivityPageStatistics(FragmentActivity fragmentActivity) {
        MobclickAgent.onResume(fragmentActivity);
    }

    @Override
    public void onActivityStatisticsEnd(FragmentActivity fragmentActivity) {
        MobclickAgent.onPause(fragmentActivity);
    }

    /**
     * 统计电商应用中”购买”事件发生的次数，以及购买的商品类型及数量示例:
     * HashMap<String,String> map = new HashMap<String,String>();
     * map.put("type","book");
     * map.put("quantity","3");
     * MobclickAgent.onEvent(mContext, "purchase", map);
     * @param eventName
     * @param value
     */
    @Override
    public void onEventStatistics(String eventName, Map<String, String> value) {
        MobclickAgent.onEvent(Utils.getApplicationContext(),eventName,value);
    }

    @Override
    public void onEventStatistics(String eventName, Map<String, String> value, int du) {
        MobclickAgent.onEventValue(Utils.getApplicationContext(),eventName,value,du);
    }

    @Override
    public void onStartAppStatistics(Context context) {
        MobclickAgent.onResume(context);
    }

    @Override
    public void onExitAppStatistics(Context context) {
        MobclickAgent.onPause(context);
    }


    private boolean isEmpty(String value)
    {
        return TextUtils.isEmpty(value);
    }
}
