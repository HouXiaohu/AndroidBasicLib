package com.hxh.component.basicore.util.aspj;


import com.hxh.component.basicore.Base.app.AppManager;
import com.hxh.component.basicore.util.aspj.annotation.SingleClick;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Created by hxh on 2017/6/29.
 */
@Aspect
public class SingleClickAspj {
   // static int TIME_TAG = R.id.click_time;
    private static final int MIN_CLICK_DELAY_TIME = 600;//间隔时间600ms
    private final String method_piex = "execution(@com.hxh.component.basicore.util.aspj.annotation.SingleClick * *(..)) && @annotation(ann)";



    @Pointcut(method_piex)//根据SingleClick注解找到方法切入点
    public void methodAnnotated(SingleClick ann) {}

    @Around("execution(!synthetic * *(..)) && methodAnnotated(ann)")//在连接点进行方法替换
    public Object aroundJoinPoint(ProceedingJoinPoint joinPoint,SingleClick ann) throws Throwable {
        if(null != ann)
        {
            int waittime = ann.waittime();

            if(!AppManager.isDoubleClick(waittime))
            {
                return joinPoint.proceed();//执行原方法
            }
        }
        return null;
    }
}
