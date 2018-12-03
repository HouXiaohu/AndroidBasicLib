package com.hxh.component.basicore.component.net;



import com.hxh.component.basicore.CoreLib;
import com.hxh.component.basicore.ui.stateview.IRequestState;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求拦截器，作用：
 * 当你想在请求前或者请求后做一些事情的时候，那么你就注册
 */
public class RequestInterceptor implements Interceptor {

    private RequestCallBackHandler handler;
    public RequestInterceptor(RequestCallBackHandler handler)
    {
        this.handler = handler;
    }

    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();
        if(handler != null)
        {
            request = handler.onBeforeRequest(request,chain);
        }
        CoreLib.getInstance().getNetProvider();
        Response response = chain.proceed(request);
        String respinseStr= response.body().string();
        if(handler != null)
        {
            response = handler.onAfterRequest(response,respinseStr,chain);
        }
        CoreLib.getInstance().getNetProvider().getRequestState().sendState(IRequestState.onHaveData,respinseStr);
        return response;
    }

}
