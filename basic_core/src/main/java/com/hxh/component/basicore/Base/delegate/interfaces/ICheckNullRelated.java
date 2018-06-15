package com.hxh.component.basicore.Base.delegate.interfaces;

import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hxh on 2018/5/8.
 */

public interface ICheckNullRelated {
    //region 非空
    boolean isEmpty(List list);
    boolean isEmpty(String msg);
    boolean isEmpty(CharSequence str);
    boolean isEmpty(String... args) ;
    boolean isEmpty(EditText text);
    boolean isEmpty(EditText text,String tipmsg);
    boolean isEmpty(TextView tv);
    boolean isEmpty(TextView tv,String msg);
    boolean isEmpty(Object obj);
    //endregion
}
