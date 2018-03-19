package com.hxh.component.basicore.component.mvp.newmvp.model;


import com.hxh.component.basicore.component.mvp.newmvp.presenter.IPresenter;

/**
 * 标题: IModel.java
 * 作者: hxh
 * 日期: 2018/3/10 17:19
 * 描述:
 *
 *       理想情况下，Model层，应该为单独的一个module，这样做：
 *       1. 使得分工明确，因为我们大多时候，时间都用于了UI或者UI逻辑上，而不是在和业务
 *       2. Model层变为全局性，一个业务组件，可以依赖于多个Model，避免多次书写Model代码
 */
public interface IModel {
    // 注册一个Presenter,用于向这个Presenter进行回调
    // 暂时不考虑使用EventBus，等到有这方面需求时候，在考虑。
    // 现阶段，手动写这几行代码，并且也能助人理解，何必使用EventBus?
    void registerP(IPresenter p);


    /**
     * 传入一个请求的tag，用于标明，你想调用哪个方法,这个Tag，最好的方式是声明在对应Model中，并且方法名最好见名知意
     * 方法必然会有参数，那么这个参数如何定义？
     * @param tag
     * @param <B>  返回值不知道该限定于谁，也不想让用户去继承/实现 一个基类，感觉没什么必要，
     *           因为上层调用方法，上层根本不知道，也不会传类型给下层，所以，下层的返回值应该是
     *           最大自由度，那么就是Object
     * @return
     */
    <B ,R extends Object>B getData(String tag, R request);


    /**
     * 释放Model所持有的资源，在现有的框架中，Model常常捆绑着一个Activity或者Fragment,那么Model就要和它们进行
     * 生命周期的同步
     */
    void release();
}
