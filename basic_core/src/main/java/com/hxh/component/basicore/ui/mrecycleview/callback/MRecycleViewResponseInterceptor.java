package com.hxh.component.basicore.ui.mrecycleview.callback;

import com.hxh.component.basicore.ui.mrecycleview.AbsNetResultBean;

/**
 * Created by hxh on 2017/10/19.
 */

public interface MRecycleViewResponseInterceptor<D> {
    /**
     * 请注意这个方法，如果你的结果是一个异步的，那么请你务必返回null，否则会导致加载两次Adapter
     * 
     * @time 2017/11/21 13:57
     * 
     * @author 
     */
    AbsNetResultBean<D> setData(AbsNetResultBean<D> datas);

}
