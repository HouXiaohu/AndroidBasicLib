package com.hxh.component.basicore.util.cache;

import android.os.Parcelable;

import com.hxh.component.basicore.util.Utils;

import java.util.List;

/**
 * Created by hxh on 2017/12/21.
 */

public class FileCache implements ICache{

    public FileCache()
    {
        fileCache =  Utils.FileCache.get();
    }

    Utils.FileCache fileCache;

    @Override
    public void save(String k, String v) {

    }

    @Override
    public void save(String k, String... v) {

    }

    @Override
    public void save(String k, int v) {

    }

    @Override
    public void save(String k, double v) {

    }

    @Override
    public void save(String k, float v) {

    }

    @Override
    public <T extends Parcelable> void save(String k, T v) {

    }

    @Override
    public <T extends Parcelable> void save(String k, T... v) {

    }

    @Override
    public <T extends Parcelable> void save(String k, List<T> v) {

    }
}
