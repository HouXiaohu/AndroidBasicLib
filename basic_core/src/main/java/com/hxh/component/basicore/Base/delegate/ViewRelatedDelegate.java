package com.hxh.component.basicore.Base.delegate;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hxh.component.basicore.Base.app.provider.UIProvider;
import com.hxh.component.basicore.Base.delegate.interfaces.IViewRelated;
import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.ui.ToastUIType;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.aspj.annotation.Safe;

/**
 * Created by hxh on 2017/7/28.
 */
public class ViewRelatedDelegate implements IViewRelated {
    @Safe
    @Override
    public void visibe(View view) {
        view.setVisibility(View.VISIBLE);
    }

    @Safe
    @Override
    public void gone(View view) {
        if (!isEmpty(view)) view.setVisibility(View.GONE);

    }

    @Safe
    @Override
    public void inVisibe(View view) {
        if (!isEmpty(view)) view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void visibe(View... views) {
        if (!isEmpty(views)) {
            for (View view : views) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void gone(View... views) {
        if (!isEmpty(views)) {
            for (View view : views) {
                view.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void inVisibe(View... views) {
        if (!isEmpty(views)) {
            for (View view : views) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void toastShort(String msg) {
        toast(msg, -1, Toast.LENGTH_SHORT);

    }

    @Override
    public void toastShort(String msg, int gravity) {
        //Utils.Toast.toast(msg,gravity);
        toast(msg, gravity, Toast.LENGTH_SHORT);
    }

    @Override
    public void toastLong(String msg) {
        toast(msg, -1, Toast.LENGTH_LONG);
    }

    @Safe
    private void toast(String msg, int gravity, int duration) {
        UIProvider uiProvider = CoreLib.getInstance().getUIProvider();
        switch (uiProvider.getToastUIType()) {
            case ToastUIType.INSIDE:
            case ToastUIType.CUSTOM:
                Utils.Toast.toast(uiProvider.getToastView(), uiProvider.getToastViewTextViewId(), msg, duration, gravity);
                break;
            default:
                Utils.Toast.toast(msg, gravity, duration);
                break;
        }
    }


    @Override
    public String getRES_String(int resId) {
        return Utils.Resource.getString(resId);
    }

    @Override
    public int getRES_Color(int resId) {
        return Utils.Resource.getColor(resId);
    }


    @Override
    public Drawable getRES_drawable(int resId) {
        return Utils.Resource.getDrawable(resId);
    }

    @Override
    public boolean getRES_boolean(int resId) {
        return Utils.Resource.getBoolean(resId);
    }

    @Override
    public float getRES_dimen(int resId) {
        return Utils.Resource.getDimen(resId);
    }

    @Safe
    @Override
    public String getText(EditText et) {
        if (isEmpty(et.getText().toString())) {
            return "";
        }
        return et.getText().toString();
    }

    @Safe
    @Override
    public String getText(TextView tv) {
        if (isEmpty(tv.getText().toString())) {
            return "";
        }
        return tv.getText().toString();
    }

    @Safe
    @Override
    public String getText(EditText et, String defaulttext) {
        return isEmpty(et.getText().toString()) ? defaulttext : et.getText().toString();
    }

    @Safe
    @Override
    public String getText(TextView tv, String defaulttext) {
        return isEmpty(tv.getText().toString()) ? defaulttext : tv.getText().toString();
    }

    @Safe
    @Override
    public String getText(TextView tv, int defaulttextResId) {
        return isEmpty(tv.getText().toString()) ? getRES_String(defaulttextResId) : tv.getText().toString();
    }

    @Safe
    @Override
    public String getText(EditText et, int defaulttextResId) {
        return isEmpty(et.getText().toString()) ? getRES_String(defaulttextResId) : et.getText().toString();
    }

    @Safe
    @Override
    public String getHint(EditText et) {
        return et.getHint().toString();
    }

    @Safe
    @Override
    public String getHint(EditText et, String defaulttext) {
        return isEmpty(et.getHint().toString()) ? defaulttext : et.getHint().toString();
    }

    @Safe
    @Override
    public String getHint(EditText et, int defaulttextResId) {
        return isEmpty(et.getHint().toString()) ? getRES_String(defaulttextResId) : et.getHint().toString();
    }

    @Override
    public <V extends TextView> void setText(V tv, String msg) {
        tv.setText(msg);
    }

    @Override
    public <V extends TextView> void setText(V tv, int msgResId) {
        tv.setText(msgResId);
    }

    @Safe
    @Override
    public void loadimg(ImageView iv, String url) {

        Utils.ImageLoadUtils.loadimg(iv, url);
    }

    @Safe
    @Override
    public void loadimg(ImageView iv, int resid) {
        Utils.ImageLoadUtils.loadimg(iv, resid);

    }

    @Override
    public void loadimg(ImageView iv, String url, int errorId) {
        Utils.ImageLoadUtils.loadimg(iv, url, errorId);
    }


    private boolean isEmpty(String s)
    {
        return Utils.Text.isEmpty(s);
    }

    private boolean isEmpty(Object s)
    {
        return Utils.Text.isEmpty(s);
    }
}
