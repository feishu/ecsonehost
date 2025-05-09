package com.weex.weexextra;

import android.content.Context;
import android.content.SharedPreferences;

import com.taobao.weex.utils.WXLogUtils;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by ucmed on 2016/8/21.
 */
public class SharedPreferencesUtil {
    private static String TAG = "SharedPreferencesUtil";

    private static SharedPreferences preferences;
//    public Context context;

    public static void init(Context context) {
//        this.context = context;
        if (preferences == null)
            preferences = context.getSharedPreferences("share_cache", Context.MODE_PRIVATE);
    }

    public static boolean put(String key, Object value) {
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            if (value instanceof Integer) {
                editor.putInt(key, new Integer((int) value));
            } else if (value instanceof Long) {
                editor.putLong(key, new Long((long) value));
            } else if (value instanceof Double) {
                editor.putLong(key, Double.doubleToRawLongBits(new Double((double) value)));
            } else if (value instanceof Float) {
                editor.putFloat(key, new Float((float) value));
            } else if (value instanceof Boolean) {
                editor.putBoolean(key, new Boolean((boolean) value));
            } else if (value instanceof Set) {
                editor.putStringSet(key, (Set<String>) value);
            } else {
                editor.putString(key, String.valueOf(value));
            }
            return editor.commit();
        }
        WXLogUtils.w(TAG, "preferences is null");
        return false;
    }

    public static Object get(String key) {
        if (preferences != null) {
            return preferences.getAll().get(key);
        } else {
            return null;
        }
    }

    public static boolean getBoolean(String key) {
        if (preferences != null) {
            return preferences.getBoolean(key, false);
        } else {
            return false;
        }
    }


    public static Boolean contain(String key) {
        if (preferences != null) {
            return preferences.contains(key);
        } else {
            return false;
        }
    }

    public static Boolean remove(String key) {
        if (preferences != null) {
            return preferences.edit().remove(key).commit();
        } else {
            return false;
        }
    }

    public static Boolean removeAll() {
        if (preferences != null) {
            return preferences.edit().clear().commit();
        } else {
            return false;
        }
    }

    public static ArrayList<String> getKeys() {
        if (preferences != null) {
            ArrayList<String> keyList = new ArrayList<>();
            Set<String> keys = preferences.getAll().keySet();
            keyList.addAll(keys);
            return keyList;
        } else {
            return new ArrayList<String>();
        }
    }


}
