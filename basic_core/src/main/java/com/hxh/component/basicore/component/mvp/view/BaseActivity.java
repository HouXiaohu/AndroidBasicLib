package com.hxh.component.basicore.component.mvp.view;

import com.hxh.component.basicore.Base.view.EmptyActivity;
import com.hxh.component.basicore.component.mvp.persenter.IPresenter;

public abstract class BaseActivity<P extends IPresenter>
        extends EmptyActivity<P>
        implements IView<P> {



}
