package com.hxh.component.basicore.ui.recycleview.mrecycleview;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by hxh on 2017/7/14.
 */
public interface NetRepository<T extends NetResultBean>{
    Observable<T> getData(HashMap<String, Object> params);
}
