package com.hxh.component.basicore.util.convert;

import java.util.List;

/**
 * Created by hxh on 2017/12/21.
 */

public interface IJson {
     String toJson(Object obj);
     <T>T toObj(String json,Class<T> classzz);
     <T> List<T> toArray(String json,Class<T> classzz);
}
