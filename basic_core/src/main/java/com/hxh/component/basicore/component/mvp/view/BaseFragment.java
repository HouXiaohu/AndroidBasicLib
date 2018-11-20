package com.hxh.component.basicore.component.mvp.view;

import com.hxh.component.basicore.component.mvp.EmptyFragment;
import com.hxh.component.basicore.component.mvp.persenter.IPresenter;

/**
 * 对外提供各种操作方法，内部统一交由对应的Delegate实现
 */
public abstract class BaseFragment<P extends IPresenter>
        extends EmptyFragment<P>
        implements IView<P> {


}
