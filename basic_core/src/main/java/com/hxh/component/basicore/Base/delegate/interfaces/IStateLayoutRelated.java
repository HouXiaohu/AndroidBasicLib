package com.hxh.component.basicore.Base.delegate.interfaces;


/**
 * Created by hxh on 2018/2/28.
 */

public interface IStateLayoutRelated {

    void operaErrorLayout(boolean isHide);
    void operaLoadingLayout(boolean isHide);
    void operaNoNetWorkLayout(boolean isHide);
    void operaStateLayout(String tag,boolean isHide);

}
