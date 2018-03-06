package com.hxh.component.basicore.util.aspj.annotation;

import com.hxh.component.basicore.util.aspj.annotationenum.DataSourceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hxh on 2017/7/17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSave {
    DataSourceType value()default DataSourceType.SP;

}
