package com.hxh.component.basicore.Base.delegate;

import com.hxh.component.basicore.Base.delegate.interfaces.ILogRelated;
import com.hxh.component.basicore.Config;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.util.log.DefalutLog;
import com.hxh.component.basicore.util.log.ILog;

/**
 * Created by hxh on 2017/8/1.
 */
public class LogDelegate implements ILogRelated {
    private ILog logProvder;

    public LogDelegate() {
        logProvder = CoreLib.getInstance().getAppComponent().globalLogProvider();
    }

    @Override
    public void d(String msg) {
        genetorLogProvider();
        logProvder.d(msg);
    }

    @Override
    public void d(Throwable th) {
        genetorLogProvider();
        logProvder.d(th);
    }

    @Override
    public void d(Exception e) {
        genetorLogProvider();
        logProvder.d(e);
    }

    @Override
    public void e(String msg) {
        genetorLogProvider();
        logProvder.e(msg);
    }

    @Override
    public void e(Throwable th) {
        genetorLogProvider();
        logProvder.e(th);
    }

    @Override
    public void e(Exception e) {
        genetorLogProvider();
        logProvder.e(e);
    }

    @Override
    public void i(String msg) {
        genetorLogProvider();
        logProvder.i(msg);
    }

    @Override
    public void i(Throwable th) {
        genetorLogProvider();
        logProvder.i(th);
    }

    @Override
    public void i(Exception e) {
        genetorLogProvider();
        logProvder.i(e);
    }

    /**
     * 一般情况下，是不需要做什么检测的，但是由于用户在设置网络拦截器时候，这个类里面在构造函数中初始化了
     * LogProvider，但是此时CoreLib还没有及时将LogProvider初始化，所以会导致，这个对象内部的日志对象为null,
     */
    private void genetorLogProvider() {
        if (null == logProvder)
            logProvder = CoreLib.getInstance().getAppComponent().globalLogProvider();
        if (null == logProvder) {
            logProvder = DefalutLog.getInstance(true, false, Config.DEFAULT_LOG_TAG, null);
            CoreLib.getInstance().getAppComponent().globalLogProvider(logProvder);
        }

    }
}
