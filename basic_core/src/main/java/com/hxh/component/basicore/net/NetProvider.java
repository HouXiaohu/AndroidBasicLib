package com.hxh.component.basicore.net;



import android.support.v4.util.ArrayMap;

import com.hxh.component.basicore.net.baseurl.DynamicMutilHttpBaseUrl;
import com.hxh.component.basicore.net.baseurl.FixedMutilHttpBaseUrl;
import com.hxh.component.basicore.rx.resetfulhttpstyle.DefaultApiError;
import com.hxh.component.basicore.util.Utils;

import java.io.File;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import retrofit2.Converter;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hxh on 2017/5/7.
 */
public class NetProvider {

    private long configReadTimeOut;
    private long configConnectTimeOut;
    private boolean isEnableCookie;


    private Authenticator configRESTFULTokenInterceptor; //认证失败
    private Interceptor configTokenInterceptor; //请求拦截，用于Token插入
    private Interceptor configLogTnterceptor; //日志拦截器
    private RequestCallBackHandler configRequestCallBack;
    private Converter.Factory configConverterFactory;
    private String cachePath;//缓存地址
    private long cacheSize ;//最大缓存大小
    private IApiError mApiErrorClasszz;
    private ArrayMap<Object,String> mFixedBaseUrls;//多baseUrl动态配置
    private  String mBaseUrl; //配置baseUrl
    private boolean isDynamicHttpUrl;
    private DynamicMutilHttpBaseUrl mDynamicMutilHttpBaseUrl;
    private String[] BASEURL_CONFIG = {"NORMAL", "FIXED", "DYNAMIC"};
    private long configWriteTimeOut;
    private boolean isEnableErrorRetry=true;
    private int errorRetryCount;
    public NetProvider(
            boolean isEnableCookie, long configReadTimeOut, long configConnectTimeOut,
            Authenticator configRESTFULTokenInterceptor, Interceptor configTokenInterceptor,
            RequestCallBackHandler configRequestCallBack, Converter.Factory configConverterFactory,
            String cachePath,long maxSize,Interceptor configLogTnterceptor
            , IApiError apiErrorClasszz,long configWriteTimeOut,boolean isEnableConnectTimeOut,
            int errorRetryCount)
    {
        this.errorRetryCount = errorRetryCount;
        this.configReadTimeOut = configReadTimeOut;
        this.configConnectTimeOut = configConnectTimeOut;
        this.isEnableCookie = isEnableCookie;

        this.configWriteTimeOut = configWriteTimeOut;
        this.isEnableErrorRetry =isEnableConnectTimeOut;

        this.configRESTFULTokenInterceptor = configRESTFULTokenInterceptor;
        this.configTokenInterceptor = configTokenInterceptor;
        this.configRequestCallBack = configRequestCallBack;
        this.configConverterFactory = configConverterFactory;
        this.cachePath = cachePath;

        this.configLogTnterceptor = configLogTnterceptor;
        this.mApiErrorClasszz = apiErrorClasszz;
    }

    public static NetProvider defaultNetProvider()
    {
        return new Builder().build();
    }

    public static class Builder
    {

        private long configReadTimeOut =30; //默认30 秒
        private long configConnectTimeOut =30;//默认30 秒
        private long configWriteTimeOut = 30;//默认30 秒
        private boolean isEnableErrorRetry  = true;
        private int errorRetryCount=-1;
        private boolean isEnableCookie = true;
        private String cachePath;//缓存地址
        private long cacheSize; //最大缓存大小
        private Authenticator configRESTFULTokenInterceptor; //用户验证失败时候的拦截器
        private Interceptor configTokenInterceptor;  //Token拦截去
        private RequestCallBackHandler configRequestCallBack;
        private Interceptor configLogTnterceptor;
        private IApiError mApiErrorClasszz;
        private Converter.Factory configConverterFactory = GsonConverterFactory.create();
        private String baseUrl="";
        private FixedMutilHttpBaseUrl mFixedMutilHttpCallBack;
        private ArrayMap<Object,String> mFixedBaseUrls;

        private boolean isDynamisHttpUrl;

        public Builder configReadTimeOut(long configReadTimeOut) {
            this.configReadTimeOut = configReadTimeOut;
            return this;
        }

        public Builder configConnectTimeOut(long configConnectTimeOut) {
            this.configConnectTimeOut = configConnectTimeOut;
            return this;
        }

        public Builder configWriteTimeOut(long configWriteTimeOut) {
            this.configWriteTimeOut = configWriteTimeOut;
            return this;
        }

        public Builder configEnableConnectTimeOutRetry(boolean isEnable) {
            this.isEnableErrorRetry = isEnable;
            return this;
        }
        /**
         * 当遇到请求出错时候（不包括连接错误），重试次数
         *
         * @time 2018/1/19 15:41
         *
         * @author
         */
        public Builder configEnableApiErrorIsRetryCount(int errorcount) {
            this.errorRetryCount = errorcount;
            return this;
        }

        public Builder configRequestCallBack(RequestCallBackHandler configRequestCallBack) {
            this.configRequestCallBack = configRequestCallBack;
            return this;
        }

        public Builder configRESTFULTokenInterceptor(Authenticator configRESTFULTokenInterceptor) {
            this.configRESTFULTokenInterceptor = configRESTFULTokenInterceptor;
            return this;
        }

        public Builder configTokenInterceptor(Interceptor configTokenInterceptor) {
            this.configTokenInterceptor = configTokenInterceptor;

            return this;
        }

        public Builder isEnableCookie(boolean isEnableCookie) {
            this.isEnableCookie = isEnableCookie;
            return this;
        }

        public Builder configConverterFactory(Converter.Factory configConverterFactory) {
            this.configConverterFactory = configConverterFactory;
            return this;
        }

        public Builder enableCache(String cachePath, long maxSize) {
            this.cachePath = cachePath;
            this.cacheSize = maxSize;
            return this;
        }

        public Builder enableCache(long maxSize) {
            this.cacheSize = maxSize;
            return this;
        }

        public Builder configLogInteceptor(Interceptor configLogTnterceptor) {
            this.configLogTnterceptor = configLogTnterceptor;
            return this;
        }

        public Builder configNetErrorEntity(IApiError neterrorclaszz )
        {
            this.mApiErrorClasszz = neterrorclaszz;
            return this;
        }

        /**
         * 设置BaseUrl,当你只有一个BaseUrl时候
         * @param baseUrl
         * @return
         */
        public Builder configHttpBaseUrl(String baseUrl)
        {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * 当你的Url有很多个，但是不可知
         * @return
         */
        public Builder configHttpBaseUrl(String baseUrl,boolean isDynamic)
        {
            this.baseUrl = baseUrl;
            this.isDynamisHttpUrl = isDynamic;
            this.mFixedMutilHttpCallBack = null;
            return this;
        }

        /**
         * 当你的Url有很多个，是固定的，已知的
         * @param mutils
         * @return
         */
        public Builder configHttpBaseUrl(FixedMutilHttpBaseUrl mutils)
        {
            this.isDynamisHttpUrl = false;
            this.mFixedMutilHttpCallBack = mutils;
            if(null == mFixedBaseUrls)
            {
                mFixedBaseUrls = new ArrayMap<>();
            }
            this.mFixedMutilHttpCallBack.configBaeUrl(mFixedBaseUrls);
            return this;
        }



        public NetProvider build()
        {
            NetProvider provider =  new NetProvider(isEnableCookie,configReadTimeOut,configConnectTimeOut,
                    configRESTFULTokenInterceptor,configTokenInterceptor,configRequestCallBack,configConverterFactory,cachePath,cacheSize,configLogTnterceptor
                    ,mApiErrorClasszz
            ,configWriteTimeOut,isEnableErrorRetry,errorRetryCount);

            provider.setBaseUrl(baseUrl);
            if (isDynamisHttpUrl) {
                provider.setDynamicHttpUrl(true);
                provider.setFixedBaseUrls(null);
            } else if (null != mFixedMutilHttpCallBack)
            {
                provider.setDynamicHttpUrl(false);
                provider.setFixedBaseUrls(mFixedBaseUrls);
            }

            return provider;
        }
    }


    //region getter setter


    public int getErrorRetryCount() {
        return errorRetryCount;
    }

    public void setErrorRetryCount(int errorRetryCount) {
        this.errorRetryCount = errorRetryCount;
    }

    public long getConfigReadTimeOut() {
        return configReadTimeOut;
    }

    public void setConfigReadTimeOut(long configReadTimeOut) {
        this.configReadTimeOut = configReadTimeOut;
    }

    public long getConfigConnectTimeOut() {
        return configConnectTimeOut;
    }

    public void setConfigConnectTimeOut(long configConnectTimeOut) {
        this.configConnectTimeOut = configConnectTimeOut;
    }

    public boolean isEnableCookie() {
        return isEnableCookie;
    }

    public void setEnableCookie(boolean enableCookie) {
        isEnableCookie = enableCookie;
    }

    public Authenticator getConfigRESTFULTokenInterceptor() {
        return configRESTFULTokenInterceptor;
    }

    public void setConfigRESTFULTokenInterceptor(Authenticator configRESTFULTokenInterceptor) {
        this.configRESTFULTokenInterceptor = configRESTFULTokenInterceptor;
    }

    public Interceptor getConfigTokenInterceptor() {
        return configTokenInterceptor;
    }

    public void setConfigTokenInterceptor(Interceptor configTokenInterceptor) {
        this.configTokenInterceptor = configTokenInterceptor;
    }



    public RequestCallBackHandler getConfigRequestCallBack() {
        return configRequestCallBack;
    }

    public void setConfigRequestCallBack(RequestCallBackHandler configRequestCallBack) {
        this.configRequestCallBack = configRequestCallBack;
    }

    public long getConfigWriteTimeOut() {
        return configWriteTimeOut;
    }

    public void setConfigWriteTimeOut(long configWriteTimeOut) {
        this.configWriteTimeOut = configWriteTimeOut;
    }

    public boolean isEnableErrorRetry() {
        return isEnableErrorRetry;
    }

    public void setEnableErrorRetry(boolean enableErrorRetry) {
        isEnableErrorRetry = enableErrorRetry;
    }

    public Converter.Factory getConfigConverterFactory() {
        return configConverterFactory;
    }

    public void setConfigConverterFactory(Converter.Factory configConverterFactory) {
        this.configConverterFactory = configConverterFactory;
    }


    public long getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(long cacheSize) {
        this.cacheSize = cacheSize;
    }

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    public Interceptor getConfigLogTnterceptor() {
        return configLogTnterceptor;
    }

    public IApiError getApiErrorClasszz()
    {
        if(null != mApiErrorClasszz)
        {
            return mApiErrorClasszz;
        }
        return DefaultApiError.getInstance();
    }


    //region 多Url配置
    public ArrayMap<Object, String> getFixedBaseUrls() {
        return mFixedBaseUrls;
    }

    public void setFixedBaseUrls(ArrayMap<Object, String> mFixedBaseUrls) {
        this.mFixedBaseUrls = mFixedBaseUrls;
    }


    public boolean isDynamicHttpUrl() {
        return isDynamicHttpUrl;
    }

    public void setDynamicHttpUrl(boolean dynamicHttpUrl) {
        isDynamicHttpUrl = dynamicHttpUrl;
    }

    public String getBaseUrl()
    {
        if (null != mBaseUrl && mBaseUrl.lastIndexOf("/") < 0) {
            mBaseUrl += File.separator;
        }
        return mBaseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    public void configDynamicHttpUrls(ArrayMap<String,String> urls)
    {
        if(null == mDynamicMutilHttpBaseUrl)
        {
            mDynamicMutilHttpBaseUrl = new DynamicMutilHttpBaseUrl();
        }
        mDynamicMutilHttpBaseUrl.setHttpUrls(urls);
        mFixedBaseUrls = null;
    }



    public String getBaseUrl(String tagName) {
        for (String type : BASEURL_CONFIG) {
            String u = getBaseUrlForTag(tagName, type);
            if (!Utils.Text.isEmpty(u)) {
                return u;
            }
        }
        return getBaseUrl();
    }

    private String getBaseUrlForTag(String tagName, String type) {
        if (type.equals("NORMAL")) {
            return getBaseUrl();
        } else if (type.equals("FIXED")) {
            return mFixedBaseUrls.get(tagName);
        } else {
            return mDynamicMutilHttpBaseUrl.getUrl(tagName);
        }
    }


//    public String getBaseUrl(int tagName)
//    {
//        if(null != mFixedBaseUrls)
//        {
//            return mFixedBaseUrls.get(tagName);
//        }else if(null != mDynamicMutilHttpBaseUrl)
//        {
//            return mDynamicMutilHttpBaseUrl.getUrl(tagName);
//        }
//        return getBaseUrl();
//    }


    public DynamicMutilHttpBaseUrl getDynamicHttpUrls()
    {
        return mDynamicMutilHttpBaseUrl;
    }
    //endregion

    //endregion
}
