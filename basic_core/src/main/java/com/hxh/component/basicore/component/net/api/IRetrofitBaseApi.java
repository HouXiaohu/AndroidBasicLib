package com.hxh.component.basicore.component.net.api;

import com.hxh.component.basicore.component.net.NetProvider;

import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by hxh on 2018/5/29.
 */

public interface IRetrofitBaseApi {

    <S> S createServices(NetProvider provider, Class<S> clazz);

    <S> S createServices(String baseurl, Class<S> clazz);

    <S> S createServices(Class<S> clazz);

    void unRegister();

    void register(String baseurl, NetProvider provider);

    void register(NetProvider provider);


    Retrofit getRetrofit(String baseUrl);
    Retrofit getRetrofit(String baseUrl, NetProvider provider);
    Retrofit createRetrofit(String baseUrl, String convertFactoryname, NetProvider provider);
    OkHttpClient getOkHttpClient(String baseUrl, NetProvider provider);
    String getConvertFactoryName(Converter.Factory factory);


    IRetrofitBaseApi setConvertFactory(int factorytype);

    NetProvider getCurrentNetProvider();

    String getmHttpCacheDir();


}
