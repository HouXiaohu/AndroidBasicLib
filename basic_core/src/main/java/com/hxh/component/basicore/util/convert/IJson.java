package com.hxh.component.basicore.util.convert;

/**
 * Created by hxh on 2017/12/21.
 */

public interface IJson {
    public String toJson(Object obj);
    public <T>T toObj(String json,Class<T> classzz);
}
