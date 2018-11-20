package com.hxh.component.basicore.component.mvp.delegate;


import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.component.mvp.delegate.interfaces.ILogRelated;
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
        logProvder.d(msg);
    }

    @Override
    public void d(Throwable th) {
        logProvder.d(th);
    }

    @Override
    public void d(Exception e) {
        logProvder.d(e);
    }

    @Override
    public void e(String msg) {
        logProvder.e(msg);
    }

    @Override
    public void e(Throwable th) {
        logProvder.e(th);
    }

    @Override
    public void e(Exception e) {
        logProvder.e(e);
    }

    @Override
    public void i(String msg) {
        logProvder.i(msg);
    }

    @Override
    public void i(Throwable th) {
        logProvder.i(th);
    }

    @Override
    public void i(Exception e) {
        logProvder.i(e);
    }
}
