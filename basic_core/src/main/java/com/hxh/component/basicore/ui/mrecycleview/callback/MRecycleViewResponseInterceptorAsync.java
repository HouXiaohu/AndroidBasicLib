package com.hxh.component.basicore.ui.mrecycleview.callback;

import com.hxh.component.basicore.ui.mrecycleview.NetResultBean;

import rx.Observable;

/**
 * Created by hxh on 2017/10/19.
 */

public interface MRecycleViewResponseInterceptorAsync<D> {
    Observable<NetResultBean<D>> setData(NetResultBean<D> datas);

}
