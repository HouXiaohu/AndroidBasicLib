package com.hxh.component.basicore.Base.delegate;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hxh.component.basicore.Base.delegate.interfaces.ICheckNullRelated;
import com.hxh.component.basicore.util.Utils;

import java.util.List;

/**
 * Created by hxh on 2018/5/8.
 */

public class CheckNullDelegate implements ICheckNullRelated {

    @Override
    public boolean isEmpty(List list) {
        return Utils.Text.isEmpty(list);
    }

    @Override
    public boolean isEmpty(String msg) {
        return Utils.Text.isEmpty(msg);
    }

    @Override
    public boolean isEmpty(CharSequence str) {
        return Utils.Text.isEmpty(str);
    }

    @Override
    public boolean isEmpty(String... args) {
        return Utils.Text.isEmpty(args);
    }

    @Override
    public boolean isEmpty(EditText text) {
        return Utils.Text.isEmpty(text);
    }

    @Override
    public boolean isEmpty(TextView tv, String msg) {

        return Utils.Text.isEmpty(tv, msg);
    }

    @Override
    public boolean isEmpty(EditText text, String tipmsg) {
        return Utils.Text.isEmpty(text, tipmsg);
    }

    @Override
    public boolean isEmpty(TextView tv) {
        return Utils.Text.isEmpty(tv);
    }

    @Override
    public boolean isEmpty(Object obj) {
        return null == obj;
    }

    private boolean isEmpty(View view) {
        if (null != view) return false;
        else return true;
    }

    private boolean isEmpty(View... view) {
        if (null != view) return false;
        else return true;
    }

}
