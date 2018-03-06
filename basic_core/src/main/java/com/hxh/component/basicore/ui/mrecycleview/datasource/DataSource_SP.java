package com.hxh.component.basicore.ui.mrecycleview.datasource;

import com.alibaba.fastjson.JSON;
import com.hxh.component.basicore.util.Utils;
import com.hxh.component.basicore.util.convert.JsonFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by hxh on 2017/7/25.
 */
public class DataSource_SP<E> implements IDataSource {

    private List<E> mDatas;

    public DataSource_SP getDatas(Class<E> classzz) {
        if (null == classzz) {
            throw new IllegalStateException("class can't is null");
        }

        String datastr = Utils.SP.getString(classzz.getSimpleName());


        mDatas = Utils.Text.isEmpty(datastr)
                ?
                new ArrayList<E>()
                :
                JSON.parseArray(datastr,classzz);


        return this;
    }

    public List<E> list()
    {
        return mDatas;
    }

    @Override
    public List<E> getDatas(HashMap param) {

        if (null == param) {
            throw new IllegalStateException("class can't is null");
        }
        if(Utils.Text.isEmpty(param.get("classname")))
        {
            return  new ArrayList<E>();
        }

        String datastr = Utils.SP.getString(param.get("classname").toString());
//        return Utils.Text.isEmpty(datastr)
//                ?
//                new ArrayList<E>()
//                :
//                ((List<E>) JsonFactory.getInstance().toArray(datastr, E));
        return null;
    }

    public Observable<List<E>> asObservable()
    {
        return Observable.defer(new Func0<Observable<List<E>>>() {
            @Override
            public Observable<List<E>> call() {
                return Observable.just(mDatas);
            }
        });
    }

    @Override
    public void saveDatas(List datas) {
        if (null != datas) {
            String key1 = datas.get(0).getClass().getSimpleName();
            //后添加
            Utils
                    .SP
                    .updateString(key1, JsonFactory.getFastJsonConvert().toJson(datas));
        }
    }
}
