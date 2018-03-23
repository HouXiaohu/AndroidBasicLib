package com.hxh.component.basicore.util.aspj;


import com.hxh.component.basicore.ui.mrecycleview.NetResultBean;
import com.hxh.component.basicore.ui.mrecycleview.datasource.DataSource_DB;
import com.hxh.component.basicore.ui.mrecycleview.datasource.DataSource_SP;
import com.hxh.component.basicore.ui.mrecycleview.datasource.IDataSource;
import com.hxh.component.basicore.util.aspj.annotation.DataSave;
import com.hxh.component.basicore.util.aspj.annotationenum.DataSourceType;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

import rx.Subscription;

/**
 * 暂时采用Sp做存储，因为发现目前的ORM框架，对多模块支持都不太友善，在这个项目中，存在各式各样的问题
 */
@Aspect
public class DataSaveAspj {
    //应用了DataSave注解，并且有个参数为ann
    public final String method_piex1 = "execution(@com.hxh.component.basicore.util.aspj.annotation.DataSave * *(..)) && @annotation(ann)";
    //上面的表达式并不是错误的，但是在你仔细检查表达式没发现问题的话，那么你可以这样描述你的表达式
    // public final String method_piex = "@within(com.hxh.component.basicore.util.aspj.annotation.DataSave)";

    @Pointcut(method_piex1)
    public void method_datasavepiex(DataSave ann) {
    }


    @Around("execution(!synthetic * *(..)) && method_datasavepiex(ann)")
    public void dbsave(ProceedingJoinPoint point, final DataSave ann) {

        Subscription subscription = null;
        try {
            point.proceed();
            Object args = point.getArgs();
            List beans = null;
            if (args instanceof List) {
                beans = (List) args;
            } else if (args instanceof NetResultBean) {
                beans = ((NetResultBean) args).getItems();
            }
            if(null != beans)
            {
                IDataSource datasource = null;
                if (ann.value() == DataSourceType.SP) {
                    datasource = new DataSource_SP<>();
                    datasource.saveDatas(beans);

                } else if (ann.value() == DataSourceType.DB)
                {
                    datasource = new DataSource_DB<>();
                    datasource.saveDatas(beans);
                } else {

                }
            }

        } catch (Exception e) {
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {

        }


    }
}
