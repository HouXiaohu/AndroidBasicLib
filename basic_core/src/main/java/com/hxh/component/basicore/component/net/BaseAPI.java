package com.hxh.component.basicore.component.net;

import com.hxh.component.basicore.component.net.api.IRetrofitBaseApi;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by hxh on 2017/5/8.
 */
public class BaseAPI {

    private IRetrofitBaseApi mBaseApi;

    //region 单例
    private static class SingletonHolder {
        private static final BaseAPI INSTANCE = new BaseAPI();
    }

    private BaseAPI() {
    }

    public static final BaseAPI getInstance() {
        return SingletonHolder.INSTANCE;
    }
    //endregion

    /**
     * 设置一个转换器
     *
     * @param factorytype 给一个转换器类别，可通过COnvertFactory获得
     * @return
     */
    public BaseAPI setConvertFactory(int factorytype) {
        this.mBaseApi.setConvertFactory(factorytype);
        return this;
    }


    //在你的application中注册
    public void register(NetProvider provider) {
        this.mBaseApi.register(provider);
    }

    //在你的application中注册
    public void register(String baseurl, NetProvider provider) {
        this.mBaseApi.register(baseurl, provider);
    }

    public void unRegister() {
        this.mBaseApi.unRegister();
    }

    /**
     * 创建一个Service
     *
     * @param clazz Service的类
     * @param <S>
     * @return
     */
    public <S> S createServices(Class<S> clazz) {

        return this.mBaseApi.createServices(clazz);
    }

    /**
     * 创建一个Service，根据BaseUrl
     *
     * @param baseurl
     * @param clazz
     * @param <S>
     * @return
     */
    public <S> S createServices(String baseurl, Class<S> clazz) {
        return this.mBaseApi.createServices(baseurl, clazz);
    }


    /**
     * 创建一个Service,根据NetProvider去创建
     *
     * @param clazz
     * @param <S>
     * @return
     */
    public <S> S createServices(NetProvider provider, Class<S> clazz) {

        return this.mBaseApi.createServices(provider, clazz);
    }

    /**
     * 得到一个Retrofit
     *
     * @param baseUrl
     * @return
     */
    private Retrofit getRetrofit(String baseUrl) {
        return this.mBaseApi.getRetrofit(baseUrl, null);
    }

    /**
     * 得到一个Retrofit
     *
     * @param baseUrl
     * @param provider
     * @return
     */
    private Retrofit getRetrofit(String baseUrl, NetProvider provider) {


        return this.mBaseApi.getRetrofit(baseUrl, provider);
    }

    private Retrofit createRetrofit(String baseUrl, String convertFactoryname, NetProvider provider) {

        return this.mBaseApi.createRetrofit(baseUrl, convertFactoryname, provider);
    }

    private String getConvertFactoryName(Converter.Factory factory) {
        return this.mBaseApi.getConvertFactoryName(factory);
    }

    /**
     * 得到一个OKhttpClient
     *
     * @param baseUrl  服务器地址
     * @param provider 网络提供者
     * @return 一个OkhttpClient对象
     */
    private OkHttpClient getOkHttpClient(String baseUrl, NetProvider provider) {

        return this.mBaseApi.getOkHttpClient(baseUrl, provider);
    }

    /**
     * 得到当前的NetProvider
     *
     * @return
     */
    public NetProvider getCurrentNetProvider() {
        return this.mBaseApi.getCurrentNetProvider();
    }

    public String getmHttpCacheDir() {
        return this.mBaseApi.getmHttpCacheDir();
    }


    public void registerBaseApi(IRetrofitBaseApi api) {
        this.mBaseApi = api;
    }

}
