package com.hxh.component.basicore.ui.mrecycleview;

import java.util.List;

/**
 * 标题: ANetResultBean.java
 * 作者: hxh
 * 日期: 2018/2/27 14:53
 * 描述: 如果使用MRecycleView，那么你的返回类型必须是继承了这个类
 * 并且，请求方法需要加上@UseMRecycleView
 *
 */
public  class NetResultBean<T> {
    public int total_count;

    public List<T> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public  Object getSource(){
        return null;
    }
}

