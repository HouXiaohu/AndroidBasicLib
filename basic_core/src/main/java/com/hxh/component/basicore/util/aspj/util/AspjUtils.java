package com.hxh.component.basicore.util.aspj.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hxh on 2017/7/6.
 */
public class AspjUtils {

    //region 获取Context方式
    private static Context mContext;
    /**
     * 通过正常方式获取（先调用init方法）
     * @return
     */
    public static Context getApplicationContext() {
        if(null == mContext)//说明没有进行初始化
        {
            mContext = getApplicationContext_reflection();
        }
        if (null == mContext) {
            throw new IllegalStateException("you first call Utils.init()...");
        }
        return mContext;
    }

    /**
     * 通过反射方式获取
     * @return
     */
    private static Context getApplicationContext_reflection()
    {
        if(null == CURRENT && null == mContext)
        {
            try {
                Object activityThread = getActivityThread();
                Object app = activityThread.getClass().getMethod("getApplication").invoke(activityThread);
                CURRENT = (Application) app;

            } catch (Throwable e) {
                throw new IllegalStateException("Can not access Application context by magic code, boom!", e);
            }
        }
        return CURRENT;
    }

    @SuppressLint("StaticFieldLeak")
    private static Application CURRENT;

    private static Object getActivityThread() {
        Object activityThread = null;
        try {
            Method method = Class.forName("android.app.ActivityThread").getMethod("currentActivityThread");
            method.setAccessible(true);
            activityThread = method.invoke(null);
        } catch (final Exception e) {
            Log.w("hxh", e);
        }
        return activityThread;
    }
    //endregion

    public static class App
    {

        private static long lastClickTime;
        private final static int SPACE_TIME = 500;
        public static boolean isDoubleClick() {
            long currentTime = System.currentTimeMillis();
            boolean isClick2;
            if (currentTime - lastClickTime >
                    SPACE_TIME) {
                isClick2 = false;
            } else {
                isClick2 = true;
            }
            lastClickTime = currentTime;
            return isClick2;
        }

        public static boolean isDoubleClick(int spacetime) {
            long currentTime = System.currentTimeMillis();
            boolean isClick2;
            if (currentTime - lastClickTime >
                    spacetime) {
                isClick2 = false;
            } else {
                isClick2 = true;
            }
            lastClickTime = currentTime;
            return isClick2;
        }

    }


    public static class SP {
        public static String PREFERENCE_NAME = "app_context";

        /**
         * put string preferences
         *
         * @param
         * @param key   The name of the preference to modify
         * @param value The new value for the preference
         * @return True if the new values were successfully written to persistent storage.
         */
        public static boolean putString(String key, String value) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(key, value);
            return editor.commit();
        }

        public static SharedPreferences.Editor editor() {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.edit();
        }

        /**
         * get string preferences
         *
         * @param
         * @param key The name of the preference to retrieve
         * @return The preference value if it exists, or null. Throws ClassCastException if there is a preference with this
         * name that is not a string
         * @see #(Context, String, String)
         */
        public static String getString(String key) {
            return getString(key, null);
        }

        /**
         * get string preferences
         *
         * @param
         * @param key          The name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
         * this name that is not a string
         */
        public static String getString(String key, String defaultValue) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getString(key, defaultValue);
        }

        /**
         * put int preferences
         *
         * @param
         * @param key   The name of the preference to modify
         * @param value The new value for the preference
         * @return True if the new values were successfully written to persistent storage.
         */
        public static boolean putInt(String key, int value) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt(key, value);
            return editor.commit();
        }

        /**
         * get int preferences
         *
         * @param
         * @param key The name of the preference to retrieve
         * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
         * name that is not a int
         * @see #(Context, String, int)
         */
        public static int getInt(String key) {
            return getInt(key, -1);
        }

        /**
         * get int preferences
         *
         * @param
         * @param key          The name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
         * this name that is not a int
         */
        public static int getInt(String key, int defaultValue) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getInt(key, defaultValue);
        }

        /**
         * put long preferences
         *
         * @param
         * @param key   The name of the preference to modify
         * @param value The new value for the preference
         * @return True if the new values were successfully written to persistent storage.
         */
        public static boolean putLong(String key, long value) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putLong(key, value);
            return editor.commit();
        }

        /**
         * get long preferences
         *
         * @param
         * @param key The name of the preference to retrieve
         * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
         * name that is not a long
         * @see #(Context, String, long)
         */
        public static long getLong(String key) {
            return getLong(key, -1);
        }

        /**
         * get long preferences
         *
         * @param
         * @param key          The name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
         * this name that is not a long
         */
        public static long getLong(String key, long defaultValue) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getLong(key, defaultValue);
        }

        /**
         * put float preferences
         *
         * @param
         * @param key   The name of the preference to modify
         * @param value The new value for the preference
         * @return True if the new values were successfully written to persistent storage.
         */
        public static boolean putFloat(String key, float value) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putFloat(key, value);
            return editor.commit();
        }

        /**
         * get float preferences
         *
         * @param
         * @param key The name of the preference to retrieve
         * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
         * name that is not a float
         * @see #(Context, String, float)
         */
        public static float getFloat(String key) {
            return getFloat(key, -1);
        }

        /**
         * get float preferences
         *
         * @param
         * @param key          The name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
         * this name that is not a float
         */
        public static float getFloat(String key, float defaultValue) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getFloat(key, defaultValue);
        }

        /**
         * put boolean preferences
         *
         * @param
         * @param key   The name of the preference to modify
         * @param value The new value for the preference
         * @return True if the new values were successfully written to persistent storage.
         */
        public static boolean putBoolean(String key, boolean value) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean(key, value);
            return editor.commit();
        }

        /**
         * get boolean preferences, default is false
         *
         * @param
         * @param key The name of the preference to retrieve
         * @return The preference value if it exists, or false. Throws ClassCastException if there is a preference with this
         * name that is not a boolean
         * @see #(Context, String, boolean)
         */
        public static boolean getBoolean(String key) {
            return getBoolean(key, false);
        }

        /**
         * get boolean preferences
         *
         * @param
         * @param key          The name of the preference to retrieve
         * @param defaultValue Value to return if this preference does not exist
         * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
         * this name that is not a boolean
         */
        public static boolean getBoolean(String key, boolean defaultValue) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            return settings.getBoolean(key, defaultValue);
        }
    }


    public static class ParcelableUtils
    {
        public static byte[] marshall(Parcelable parceable) {
            Parcel parcel = Parcel.obtain();
            parceable.writeToParcel(parcel, 0);
            byte[] bytes = parcel.marshall();
            parcel.recycle();
            return bytes;
        }

        public static Parcel unmarshall(byte[] bytes) {
            Parcel parcel = Parcel.obtain();
            parcel.unmarshall(bytes, 0, bytes.length);
            parcel.setDataPosition(0); // This is extremely important!
            return parcel;
        }

        public static <T> T unmarshall(byte[] bytes, Parcelable.Creator<T> creator) {
            Parcel parcel = unmarshall(bytes);
            T result = creator.createFromParcel(parcel);
            parcel.recycle();
            return result;
        }
    }

}
