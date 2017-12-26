package com.hxh.component.basicore.util.convert;

import com.google.gson.Gson;

/**
 * Created by hxh on 2017/12/21.
 */

public class GSONConvert implements IJson{
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

    private void checkIsNull()
    {
        if(null== gson )gson = new Gson();
    }
}
