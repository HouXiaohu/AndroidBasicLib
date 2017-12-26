package com.hxh.component.basicore.util.cache;

import android.os.Parcelable;

import java.util.List;

/**
 * Created by hxh on 2017/12/21.
 */

public interface ICache {
    void save(String k, String v);

    void save(String k, String... v);

    void save(String k, int v);

    void save(String k, double v);

    void save(String k, float v);

    <T extends Parcelable> void save(String k, T v);

    <T extends Parcelable> void save(String k, T... v);

    <T extends Parcelable> void save(String k, List<T> v);
}
