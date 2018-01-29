package com.hxh.component.basicore.util.convert;

/**
 * Created by hxh on 2017/12/21.
 */

public class JsonFactory
{
    public static final String GSON = "gson";
    public static final String FASTJSON = "json";

    public static IJson getConvert(String type)
    {
        switch (type)
        {
            case GSON:
                return new GSONConvert();

            case FASTJSON:
                return new JSONConvert();

        }
        return new JSONConvert();
    }



}
