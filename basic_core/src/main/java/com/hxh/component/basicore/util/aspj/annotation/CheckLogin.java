package com.hxh.component.basicore.util.aspj.annotation;

import com.hxh.component.basicore.util.aspj.annotationenum.ShowType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 标题: CheckLogin.java
 * 作者: hxh
 * 日期: 2018/3/6 11:51
 * 描述:  检查是否登录
 * 请注意，如果你使用了此注解，我默认会从sp 中得到用户信息，sp 的key 为：
 * {@link com.hxh.component.basicore.Config} SP_USERINFO_TAG 字段
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface CheckLogin {
   ShowType type() default ShowType.TOAST;
}
