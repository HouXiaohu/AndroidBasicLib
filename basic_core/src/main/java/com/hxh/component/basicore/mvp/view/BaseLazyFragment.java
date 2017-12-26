package com.hxh.component.basicore.mvp.view;

import com.hxh.component.basicore.Base.view.EmptyFragment;
import com.hxh.component.basicore.mvp.persenter.IPresenter;

/**
 * 对外提供各种操作方法，内部统一交由对应的Delegate实现
 */
public abstract class BaseLazyFragment<P extends IPresenter>
        extends EmptyFragment<P>
        implements IView<P>
{


}
