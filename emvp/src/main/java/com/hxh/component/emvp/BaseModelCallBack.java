package com.hxh.component.emvp;

import com.hxh.component.emvp.presenter.IPresenter;

/**
 * Created by hxh on 2018/5/29.
 */

public class BaseModelCallBack<P extends IPresenter> {
    protected P mPresenter;
}
