package com.hxh.component.basicore.ui.recycleview.mrecycleview.callback;

import com.hxh.component.basicore.ui.recycleview.mrecycleview.NetResultBean;

import rx.Observable;

/**
 * Created by hxh on 2017/10/19.
 */

public interface MRecycleViewResponseInterceptorAsync<D> {
    Observable<NetResultBean<D>> setData(NetResultBean<D> datas);

}
