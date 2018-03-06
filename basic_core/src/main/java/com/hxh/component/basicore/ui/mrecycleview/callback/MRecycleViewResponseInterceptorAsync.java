package com.hxh.component.basicore.ui.mrecycleview.callback;

import com.hxh.component.basicore.ui.mrecycleview.AbsNetResultBean;

import rx.Observable;

/**
 * Created by hxh on 2017/10/19.
 */

public interface MRecycleViewResponseInterceptorAsync<D> {
    Observable<AbsNetResultBean<D>> setData(AbsNetResultBean<D> datas);

}
