package com.hxh.component.basicore.ui.stateview;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.hxh.component.basicore.ui.recycleview.mrecycleview.NetResultBean;

import java.util.List;

/**
 * Created by houxi on 2018/11/16.
 */

public abstract class IRequestState<T> {

    public static final int onError= 0x1;
    public static final int onNoNet= 0x2;
    public static final int onEmptyData= 0x3;
    public static final int onHaveData= 0x4;


    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
           int what = msg.what;
            switch (what) {
                case onError:
                    onError((Throwable) msg.obj);
                    break;
                case onNoNet:
                    onNoNet();
                    break;
                case onEmptyData:
                    onEmptyData();
                    break;
                case onHaveData:
                    onHaveData((T) msg.obj);
                    break;
            }
        }
    };


    private void onNoNet() {
        _onNoNet();
    }

    private void onError(Throwable throwable) {
        _onError(throwable);
    }

    private void onEmptyData() {
        _onEmptyData();
    }

    private void onHaveData(T t) {
        if (t instanceof List) {
            if (((List) t).size() == 0) {
                _onEmptyData();
                return;
            }
        } else if (t instanceof NetResultBean) {
            if (((NetResultBean) t).getItems().size() == 0) {
                _onEmptyData();
                return;
            }
        }
        _onHaveData(t);
    }

    /**
     * 没有网络
     */
    protected abstract void _onNoNet();

    /**
     * 出现错误
     *
     * @param throwable
     */
    protected abstract void _onError(Throwable throwable);


    protected abstract void _onEmptyData();

    /**
     * 不一定会有数据，有可能是大小为0
     *
     * @param t
     */
    protected abstract void _onHaveData(T t);

    public void sendState(int type){
        sendState(type,null);
    }
    public void sendState(int type, Object arg) {
        Message msg = null;
        if(null != arg){
             msg = Message.obtain(handler,type,arg);
        }else{
             msg = Message.obtain(handler,type);
        }

        handler.sendMessage(msg);
    }
}
