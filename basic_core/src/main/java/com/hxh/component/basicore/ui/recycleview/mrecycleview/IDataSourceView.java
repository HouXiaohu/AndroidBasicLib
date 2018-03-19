package com.hxh.component.basicore.ui.recycleview.mrecycleview;

import com.hxh.component.basicore.Base.adapter.BaseRecyAdapter;

import java.util.List;

/**
 * Created by hxh on 2017/10/19.
 */

public interface IDataSourceView<D> {
    void setNetData(List<D> result);

    void setLocalData(List<D> result);

    void setEmpty(EmptyViewConfig config);

    void clearData();

    void noevery(); //不做任何操作

    boolean isRefresh();

    boolean isLoadMore();

    boolean isEnableLoadAndRefresh();

    void enableRefreshAndLoadMore();

    BaseRecyAdapter getAdapter();
}
