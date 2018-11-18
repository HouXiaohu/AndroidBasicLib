package com.hxh.component.basicore.ui.stateview;

import com.hxh.component.basicore.ui.recycleview.mrecycleview.NetResultBean;

import java.util.List;

/**
 * Created by houxi on 2018/11/16.
 */

public abstract class IRequestState<T> {

    public void onNoNet() {
        _onNoNet();
    }

    public void onError(Throwable throwable) {
        _onError(throwable);
    }

    public void onEmptyData() {
        _onEmptyData();
    }

    public void onHaveData(T t) {
        if (t instanceof List) {
            if (((List) t).size() == 0) {
                _onEmptyData();
                return;
            }
        } else if (t instanceof NetResultBean) {
            if (((NetResultBean) t).getItemSize() == 0) {
                _onEmptyData();
                return;
            }
        }
        _onHaveData(t);
    }

    /**
     * 没有网络
     */
    public abstract void _onNoNet();

    /**
     * 出现错误
     *
     * @param throwable
     */
    public abstract void _onError(Throwable throwable);


    public abstract void _onEmptyData();

    /**
     * 不一定会有数据，有可能是大小为0
     *
     * @param t
     */
    public abstract void _onHaveData(T t);
}
