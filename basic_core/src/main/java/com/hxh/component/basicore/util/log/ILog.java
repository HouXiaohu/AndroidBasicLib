package com.hxh.component.basicore.util.log;

/**
 * Created by hxh on 2018/5/10.
 */

public interface ILog {



    void init();
    boolean isEnableLog();
    boolean isEnableWriteLog();
    String getLogTag();
    String getLogWritePath();

    void d(String msg);
    void d(Exception exception);
    void d(Throwable throwable);

    void e(String msg);
    void e(Exception exception);
    void e(Throwable throwable);


    void w(String msg);
    void w(Exception exception);
    void w(Throwable throwable);

    void i(String msg);
    void i(Exception exception);
    void i(Throwable throwable);
}
