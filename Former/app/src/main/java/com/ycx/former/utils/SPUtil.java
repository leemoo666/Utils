package com.ycx.former.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ycx.former.App;

/**
 * Created by lixiaoming on 16/5/6.
 */
public class SPUtil {

    /**
     * SharedPreferences的实例 *
     */
    private SharedPreferences mSP = null;


    private SPUtil() {

    }

    public static SPUtil getInstance() {
        return Singleton.INSTANCE;
    }


    private static final class Singleton {
        private static final SPUtil INSTANCE = new SPUtil();
    }

    private synchronized SharedPreferences getPrefrences() {
        if (null == mSP) {
            mSP = PreferenceManager.getDefaultSharedPreferences(App.getInstance().getContext());
        }
        return mSP;
    }

    public synchronized void clear() {
        getPrefrences().edit().clear().commit();
    }

    public void putStringForKey(String key, String value) {
        getPrefrences().edit().putString(key, value).commit();
    }

    public int getIntForKey(String key) {
        return getPrefrences().getInt(key, -1);
    }

    public void putIntForKey(String key, int value) {
        getPrefrences().edit().putInt(key, value).commit();
    }

    public String getStringForKey(String key) {
        return getPrefrences().getString(key, "");
    }

    /**
     * 获取SharePreference实例
     * Context context
     */
    public static SharedPreferences getSP(Context context) {
        return context.getSharedPreferences("personInfo", Context.MODE_PRIVATE | Context.MODE_MULTI_PROCESS);
    }
}
