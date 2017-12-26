package com.hxh.component.basicore.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * 标题: TimeTickBroadcastReceiver.java
 * 作者: hxh
 * 日期: 2017/11/13 16:28
 * 描述: 每隔多少分钟，将会回调一次。 常用于 准点报时。
 * 监控的Action为：  Intent.ACTION_TIME_TICK
 */
public class TimeTickBroadcastReceiver extends BroadcastReceiver {

    private int minute = 60;
    private TimeTickCallBack callback;
    public TimeTickBroadcastReceiver(int minute) {
        this.minute = minute;
    }

    public TimeTickBroadcastReceiver(TimeTickCallBack callback) {
        this.callback = callback;
    }

    public TimeTickBroadcastReceiver(int minute,TimeTickCallBack callback) {
        this.minute = minute;
        this.callback = callback;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int milisecond = cal.get(Calendar.MILLISECOND);
        if (min == minute) {
            callback.onTick(hour,min);
        }
    }

    public static interface TimeTickCallBack{
        void onTick(int hour,int min);
    }
}
