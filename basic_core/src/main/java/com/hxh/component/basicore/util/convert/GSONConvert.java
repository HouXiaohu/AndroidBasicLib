package com.hxh.component.basicore.util.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by hxh on 2017/12/21.
 */

public class GSONConvert implements IJson{

    private static volatile GSONConvert singleton = null;

    private GSONConvert() {
    }

    public static GSONConvert getInstance() {
        if (singleton == null) {
            synchronized (GSONConvert.class) {
                if (singleton == null) {
                    singleton = new GSONConvert();
                }
            }
        }
        return singleton;
    }

    Gson gson;

    @Override
    public String toJson(Object obj) {
        checkIsNull();
        return gson.toJson(obj);
    }

    @Override
    public <T> T toObj(String json, Class<T> classzz) {
        checkIsNull();
        return gson.fromJson(json,classzz);
    }

    @Override
    public <T> List<T> toArray(String json, Class<T> classzz) {
        checkIsNull();
        Type type = new TypeToken<List<T>>(){}.getType();
        return gson.fromJson(json,type);
    }

    private void checkIsNull()
    {
        if(null== gson )gson = new Gson();
    }
}
