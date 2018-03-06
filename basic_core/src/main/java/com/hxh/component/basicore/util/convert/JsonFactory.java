package com.hxh.component.basicore.util.convert;

/**
 * Created by hxh on 2017/12/21.
 */

public class JsonFactory {
    public static final String GSON = "gson";
    public static final String FASTJSON = "json";

    public static IJson getConvert(String type) {
        switch (type) {
            case GSON:
                return GSONConvert.getInstance();

            case FASTJSON:
                return JSONConvert.getInstance();

        }
        return JSONConvert.getInstance();
    }

    public static IJson getFastJsonConvert() {
        return JSONConvert.getInstance();
    }

    public static IJson getGSONConvert() {
        return GSONConvert.getInstance();
    }


}
