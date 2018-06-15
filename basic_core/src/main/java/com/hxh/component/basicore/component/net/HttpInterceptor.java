package com.hxh.component.basicore.component.net;

import com.hxh.component.basicore.Base.delegate.LogDelegate;
import com.hxh.component.basicore.util.Utils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hxh on 2017/7/8.
 */
public class HttpInterceptor {

    private LogDelegate mLog;
    public HttpInterceptor()    
    {
        mLog = new LogDelegate();
    }

    public  Interceptor buildTokenInterceptor(final String tokenFieldName, final TokenCallBack callback) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Utils.Text.isEmpty(callback.getToken())) {
                    mLog.d("植入token--->" + callback.getToken());
                    Request authorised = request.newBuilder()
                            .removeHeader(tokenFieldName)
                            .header(tokenFieldName, callback.getToken())
                            .build();
                    return chain.proceed(authorised);
                }
                return chain.proceed(request);
            }
        };
    }

    public  Interceptor buildDefaultLogInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                long startTime = System.currentTimeMillis();
                okhttp3.Response response = chain.proceed(chain.request());
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                okhttp3.MediaType mediaType = response.body().contentType();
                String content = response.body().string();
                mLog.d("/n");
                mLog.d("----------Start----------------");
                mLog.d("| " + request.toString());
                String method = request.method();

                if ("POST".equals(method)) {
                    StringBuilder sb = new StringBuilder();
                    if (request.body() instanceof FormBody) {
                        FormBody body = (FormBody) request.body();
                        for (int i = 0; i < body.size(); i++) {
                            sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        mLog.d("| RequestParams:{" + sb.toString() + "}");
                    }
                }
                mLog.d("| Response:" + content);
                mLog.d("----------End:" + duration + "毫秒----------");
                return response.newBuilder()
                        .body(okhttp3.ResponseBody.create(mediaType, content))
                        .build();
            }
        };
    }

    public  Interceptor buildLogInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                long startTime = System.currentTimeMillis();
                okhttp3.Response response = chain.proceed(chain.request());
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                okhttp3.MediaType mediaType = response.body().contentType();
                String content = response.body().string();

                mLog.d("/n");
                mLog.d("----------Start----------------");
                mLog.d("| " + request.toString());
                String method = request.method();

                if ("POST".equals(method)) {
                    StringBuilder sb = new StringBuilder();

                    if (request.body() instanceof FormBody) {
                        FormBody body = (FormBody) request.body();
                        for (int i = 0; i < body.size(); i++) {
                            sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                        }
                        sb.delete(sb.length() - 1, sb.length());
                        mLog.d("| RequestParams:{" + sb.toString() + "}");
                    }
                }

                mLog.d("| Response:" + content);
                mLog.d("----------End:" + duration + "毫秒----------");
                return response.newBuilder()
                        .body(okhttp3.ResponseBody.create(mediaType, content))
                        .build();
            }
        };
    }

     int retrynum = 0;

    public  Interceptor buildErrorRetryInterceptor(final int maxNum) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);
                while (!response.isSuccessful() && retrynum < maxNum) {
                    ++retrynum;
                    response = chain.proceed(request);
                }
                return response;
            }
        };

    }


    public interface TokenCallBack {
        String getToken();
    }


}
