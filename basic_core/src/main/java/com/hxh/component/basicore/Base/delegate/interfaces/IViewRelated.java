package com.hxh.component.basicore.Base.delegate.interfaces;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 所有关于界面 的操作，例如： 吐司，获取资源等
 */
public interface IViewRelated
{


    /**
     * 使view可见
     * @param view
     */
    void visibe( View view);

    void visibe(View... views);
    /**
     * 使view不可见并且不占用控件
     * @param view
     */
    void gone(View view);
    void gone(View... views);
    /**
     * 使view不可见
     * @param view
     */
    void inVisibe( View view);
    void inVisibe(View... views);
    /**
     * Toast一条短消息
     * @param msg
     */
    void toastShort(String msg);

    /**
     * Toast一条短消息,并且可以自定义位置
     *
     * @time 2017/12/8 14:45
     *
     * @author
     */
    void toastShort(String msg,int gravity);

    /**
     * Toast一条长消息
     * @param msg
     */
    void toastLong(String msg);

    /**
     * 得到一个String资源
     * @param resId
     * @return
     */
    String getRES_String(int resId);

    /**
     * 得到一条 Color资源
     * @param resId
     * @return
     */
    int getRES_Color(int resId);

    /**
     * 得到一个Drawble资源
     * @param resId
     * @return
     */
    Drawable getRES_drawable(int resId);

    /**
     * 得到一个boolean 资源
     * @param resId
     * @return
     */
    boolean getRES_boolean(int resId);

    /**
     * 得到一个Dimen资源
     * @param resId
     * @return
     */
    float getRES_dimen(int resId);

    // 得到各种文本的字体
    String getText(EditText et);
    String getText(TextView tv);
    String getText(EditText et,String defaulttext);
    String getText(TextView tv,String defaulttext);
    String getText(TextView tv,int defaulttextResId);
    String getText(EditText et,int defaulttextResId);

    String getHint(EditText et);
    String getHint(EditText et,String defaulttext);
    String getHint(EditText et,int defaulttextResId);

    <V extends TextView>void setText(V tv,String msg);
    <V extends TextView>void setText(V tv,int msgResId);


    //region 图片
    void loadimg(ImageView iv,String url);
    void loadimg(ImageView iv,int resid);
    void loadimg(ImageView iv,String url,int errorId);
    //endregion



}
