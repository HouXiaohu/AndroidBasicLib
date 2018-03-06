package com.hxh.component.basicore.util.aspj;

import com.hxh.component.basicore.Config;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.aspj.annotation.CheckLogin;
import com.hxh.component.basicore.util.aspj.util.AspjManager;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by hxh on 2017/6/29.
 */
@Aspect
public  class CheckLoginAspj {
    public String TAG = Config.SP_DEFAULT_USERINFO_TAG;
    public final String  method_piex = "execution(@com.hxh.component.basicore.util.aspj.annotation.CheckLogin * *(..)) && @annotation(ann)";

    @Pointcut(method_piex)
    public void method_pointpiex(CheckLogin ann){}


    @Around("execution(!synthetic * *(..)) && method_pointpiex(ann)")
    public Object chckLogin(ProceedingJoinPoint point,CheckLogin ann) {

        String isNoUser = Utils.SP.getString(TAG);
        Object resule = null;
        if(null == isNoUser || "".equals(isNoUser))
        {
            AspjManager.CheckLoginManager manager = AspjManager.CheckLoginManager.getInstance();
            manager.showLoginview(ann.type());
        }else
        {
            try {
                resule = point.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return resule;

    }
}
