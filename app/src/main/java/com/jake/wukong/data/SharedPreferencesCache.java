package com.jake.wukong.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * description：cache data by SharedPreferences
 *
 * @author jake
 * @since 2016/9/26 21:43
 */


public final class SharedPreferencesCache {
    /**
     * cache data key
     */
    interface Key {
        String APP = "app";
    }

    private static class InstanceBuild {
        static SharedPreferencesCache instance = new SharedPreferencesCache();
    }

    private SharedPreferences mSharedPreferences;
    private static final String DEFAULT_CACHE_NAME = "cache_data";
    private Context mContext;
    private String mCacheName = DEFAULT_CACHE_NAME;

    public static SharedPreferencesCache getInstance() {
        return InstanceBuild.instance;
    }

    private SharedPreferencesCache() {
    }

    /**
     * initialization mContext and mSharedPreferences,it must invoke in Application's onCreate method
     *
     * @param context
     */
    public void install(Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(DEFAULT_CACHE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * write cache
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        put(DEFAULT_CACHE_NAME, key, value);
    }

    public void put(String cacheName, String key, String value) {
        checkInstall();
        checkSharedPreferences(cacheName);
        mSharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * delete cache by key
     *
     * @param key
     */
    public void remove(String key) {
        remove(DEFAULT_CACHE_NAME, key);
    }

    public void remove(String cacheName, String key) {
        checkInstall();
        checkSharedPreferences(cacheName);
        mSharedPreferences.edit().remove(key).commit();
    }


    /**
     * get cache
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return get(DEFAULT_CACHE_NAME, key);
    }

    public String get(String cacheName, String key) {
        checkSharedPreferences(cacheName);
        return mSharedPreferences.getString(key, "");
    }

    /**
     * check  mContext is or not null,mContext can't be null
     */
    private void checkInstall() {
        if (mContext == null) {
            throw new RuntimeException("SharedPreferencesCache is not installed");
        }
    }

    /**
     * check  SharedPreferences is or not null,if it is null,  create it
     *
     * @param cacheName
     * @return
     */
    private void checkSharedPreferences(String cacheName) {
        checkInstall();
        if (!TextUtils.equals(mCacheName, cacheName)) {
            mCacheName = cacheName;
            mSharedPreferences = mContext.getSharedPreferences(mCacheName, Context.MODE_PRIVATE);
        }
    }

}
