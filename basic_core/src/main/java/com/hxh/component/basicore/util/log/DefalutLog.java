package com.hxh.component.basicore.util.log;

import com.hxh.component.basicore.Config;
import com.hxh.component.basicore.util.Utils;

/**
 * Created by hxh on 2018/5/10.
 */

public class DefalutLog implements ILog {



    //region 单例实现
    private static volatile DefalutLog singleton = null;

    private DefalutLog(boolean enableLog, boolean enableLogWrite, String logTag, String savePath) {
        this.logEnable = enableLog;
        this.enableLogWrite = enableLogWrite;
        this.logTag = logTag;
        this.writePath = savePath;
    }

    public static DefalutLog getInstance() {
        if (singleton == null) {
            synchronized (DefalutLog.class) {
                if (singleton == null) {
                    singleton = new DefalutLog(true,false, Config.DEFAULT_LOG_TAG,null);
                }
            }
        }
        return singleton;
    }

    public static DefalutLog getInstance(boolean enableLog, boolean enableLogWrite, String logTag, String savePath) {
        if (singleton == null) {
            synchronized (DefalutLog.class) {
                if (singleton == null) {
                    singleton = new DefalutLog(enableLog,enableLogWrite,logTag,savePath);
                }
            }
        }
        return singleton;
    }

    //endregion

    private boolean logEnable = false;
    private boolean enableLogWrite;
    private String logTag;
    private String writePath;
    private boolean pathIsValid;

    @Override
    public void init() {
        //判断是否存在
        if (enableLogWrite) {
            if (!Utils.Text.isEmpty(writePath)) {
                if (!Utils.FileUtil.exist(writePath)) {
                    if (Utils.FileUtil.isDirOrFile(writePath)) pathIsValid = true;
                    else throw new IllegalArgumentException("there savePath not is a File !");
                } else throw new IllegalArgumentException("there savePath not exists !");
            } else throw new IllegalArgumentException("this param savePath is null");
        }
    }

    @Override
    public void d(String msg) {
        log(msg, "d");
    }

    @Override
    public void d(Exception exception) {
        log(exception.getMessage(), "d");
    }

    @Override
    public void d(Throwable throwable) {
        log(throwable.getMessage(), "d");
    }

    @Override
    public void e(String msg) {
        log(msg, "e");
    }

    @Override
    public void e(Exception exception) {
        log(exception.getMessage(), "e");
    }

    @Override
    public void e(Throwable throwable) {
        log(throwable.getMessage(), "e");
    }

    @Override
    public void w(String msg) {
        log(msg, "w");
    }

    @Override
    public void w(Exception exception) {
        log(exception.getMessage(), "w");
    }

    @Override
    public void w(Throwable throwable) {
        log(throwable.getMessage(), "w");
    }

    @Override
    public void i(String msg) {
        log(msg, "i");
    }

    @Override
    public void i(Exception exception) {
        log(exception.getMessage(), "i");
    }

    @Override
    public void i(Throwable throwable) {
        log(throwable.getMessage(), "i");
    }


    /**
     * 采用安卓原生Log
     *
     * @param message
     * @param type
     */
    private void log(String message, String type) {
        if (logEnable) {
            switch (type) {
                case "e":
                    android.util.Log.e(logTag, message);
                    break;
                case "d":
                    android.util.Log.d(logTag, message);
                    break;
                case "i":
                    android.util.Log.i(logTag, message);
                    break;
                case "w":
                    android.util.Log.w(logTag, message);
                    break;
            }
        }

        //当开启Log输出，并且地址有效，那么向这个文件里面输出Log
        if (pathIsValid) {
            logWriteToFile();
        }
    }

    @Override
    public boolean isEnableLog() {
        return logEnable;
    }

    @Override
    public boolean isEnableWriteLog() {
        return enableLogWrite;
    }

    @Override
    public String getLogTag() {
        return logTag;
    }

    @Override
    public String getLogWritePath() {
        return writePath;
    }

    private void logWriteToFile() {

    }
}
