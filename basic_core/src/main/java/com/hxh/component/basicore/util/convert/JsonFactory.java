package com.hxh.component.basicore.util.convert;

import java.util.List;

/**
 * Created by hxh on 2017/12/21.
 */

public class JsonFactory implements IJson {
    private JsonFactory(){}
    static JsonFactory factory;
    public static JsonFactory getInstance()
    {
        if(null == factory)factory = new JsonFactory();
        return factory;
    }

    IJson iJson;

    @Override
    public String toJson(Object obj) {
        checkisNUll();
        return iJson.toJson(obj);
    }

    @Override
    public <T> T toObj(String json, Class<T> classzz) {
        checkisNUll();

        return iJson.toObj(json,classzz);
    }

    @Override
    public <T> List<T> toArray(String json, Class<T> classzz)
    {
        checkisNUll();

        return iJson.toArray(json,classzz);
    }


    private void checkisNUll()
    {
        if(null == iJson) iJson =new JSONConvert();
    }
}
