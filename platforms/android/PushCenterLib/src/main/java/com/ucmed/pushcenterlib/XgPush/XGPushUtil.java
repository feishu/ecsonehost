package com.ucmed.pushcenterlib.XgPush;

import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGNotifaction;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.XGPushNotifactionCallback;
import com.tencent.android.tpush.common.Constants;
import com.ucmed.pushcenterlib.PushCenterManager;
import com.ucmed.pushcenterlib.PushCenterUtil;
import com.ucmed.pushcenterlib.TSLNotificationUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by liaochengzong on 2018/4/12.
 * 推送适配方案
 * 1.推送通知:展示->点击->app未启动时跳转
 */

public class XGPushUtil {

    private static int res_small_icon;

    /**
     * 信鸽推送初始化
     *
     * @param context
     */
    public static void init(final Context context, @NonNull int small_icon) {
        XGPushConfig.enableDebug(context, PushCenterManager.getInstance().isDebug);
        XGPushManager.setNotifactionCallback(new XGPushNotifactionCallback() {
            @Override
            public void handleNotify(XGNotifaction xgNotifaction) {
                //自定义通知展示, 解决消息点击处理问题
                Log.d(Constants.PushMessageLogTag, "NotifactionCallback");
                try {
                    Field contentField = XGNotifaction.class.
                            getDeclaredField("f");
                    contentField.setAccessible(true);
                    //从xgNotifaction中获取context,防止进程杀掉后context=null
                    Context context1 = (Context) contentField.get(xgNotifaction);
                    showLocalNotification(context1, xgNotifaction.getTitle(), xgNotifaction.getContent(), xgNotifaction.getCustomContent());

                    Map map = new HashMap();
                    map.put("type", "PUSH_RECEIVE_MESSAGE_CALLBACK");
                    map.put("title", xgNotifaction.getTitle());
                    map.put("content", xgNotifaction.getContent());
                    map.put("customContent", xgNotifaction.getCustomContent());
                    map.put("origin", "xinge");

                    PushCenterUtil.sendPushGlobalEvent(context1, map);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
        res_small_icon = small_icon;
    }

    /**
     * 信鸽推送初始化
     *
     * @param context
     * @param registerCallback
     */
    public static void init(final Context context, @NonNull int small_icon, XGIOperateCallback registerCallback) {
//        XGPro.enableXGPro(context, true);
        XGPushConfig.enableDebug(context, true);
        XGPushManager.setNotifactionCallback(new XGPushNotifactionCallback() {
            @Override
            public void handleNotify(XGNotifaction xgNotifaction) {
                //自定义通知展示, 解决消息点击处理问题
                Log.d(Constants.PushMessageLogTag, "NotifactionCallback");
                try {
                    Field contentField = XGNotifaction.class.
                            getDeclaredField("f");
                    contentField.setAccessible(true);
                    //从xgNotifaction中获取context,防止进程杀掉后context=null
                    Context context1 = (Context) contentField.get(xgNotifaction);
                    showLocalNotification(context1, xgNotifaction.getTitle(), xgNotifaction.getContent(), xgNotifaction.getCustomContent());

                    Map map = new HashMap();
                    map.put("type", "PUSH_RECEIVE_MESSAGE_CALLBACK");
                    map.put("title", xgNotifaction.getTitle());
                    map.put("content", xgNotifaction.getContent());
                    map.put("customContent", xgNotifaction.getCustomContent());
                    map.put("origin", "xinge");

                    PushCenterUtil.sendPushGlobalEvent(context1, map);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
        res_small_icon = small_icon;
        registerPush(context, registerCallback);
    }


    /**
     * 推送注册,不绑定账号,在程序启动时调用，只注册信鸽
     *
     * @param context
     * @param callback
     */
    public static void registerPush(Context context, XGIOperateCallback callback) {
//        XGPushConfig.setHuaweiDebug(isDebug);
//        XGPushConfig.enableOtherPush(context.getApplicationContext(), true);
        if (callback == null) {
            XGPushManager.registerPush(context);
        } else {
            XGPushManager.registerPush(context, callback);
        }
    }

    /**
     * 注册华为推送
     *
     * @param context
     * @param callback
     */
    public static void registerHWPush(Context context, XGIOperateCallback callback) {
        XGPushConfig.setHuaweiDebug(PushCenterManager.getInstance().isDebug);
        XGPushConfig.enableOtherPush(context.getApplicationContext(), true);
        if (callback == null) {
            XGPushManager.registerPush(context);
        } else {
            XGPushManager.registerPush(context, callback);
        }
    }

    /**
     * 注册小米推送
     *
     * @param context
     * @param callback
     */
    public static void registerMiPush(Context context, String APP_ID, String APP_KEY, XGIOperateCallback callback) {
        XGPushConfig.setMiPushAppId(context.getApplicationContext(), APP_ID);
        XGPushConfig.setMiPushAppKey(context.getApplicationContext(), APP_KEY);
        XGPushConfig.enableOtherPush(context.getApplicationContext(), true);
        if (callback == null) {
            XGPushManager.registerPush(context);
        } else {
            XGPushManager.registerPush(context, callback);
        }
    }

    /**
     * 注册魅族推送
     *
     * @param context
     * @param callback
     */
    public static void registerMzPush(Context context, String APP_ID, String APP_KEY, XGIOperateCallback callback) {
        XGPushConfig.enableOtherPush(context.getApplicationContext(), true);
        XGPushConfig.setMzPushAppId(context, APP_ID);
        XGPushConfig.setMzPushAppKey(context, APP_KEY);
        if (callback == null) {
            XGPushManager.registerPush(context);
        } else {
            XGPushManager.registerPush(context, callback);
        }
    }

    /**
     * 反注册,调用之后将无法收到推送
     *
     * @param context
     * @param callback
     */
    public static void unregisterPush(Context context, XGIOperateCallback callback) {
        if (callback == null) {
            XGPushManager.unregisterPush(context);
        } else {
            XGPushManager.unregisterPush(context, callback);
        }
    }

    /**
     * 绑定账号,登录成功后调用绑定用户账号
     *
     * @param context
     * @param account
     * @param callback
     */
    public static void bindAccount(Context context, String account, XGIOperateCallback callback) {
        if (TextUtils.isEmpty(account)) {
            throw new IllegalArgumentException("The account parameter can not be null!");
        }

        if (callback == null) {
            XGPushManager.bindAccount(context, account);
        } else {
            XGPushManager.bindAccount(context, account, callback);
        }
    }

    /**
     * 解除绑定账号,
     *
     * @param context
     * @param account
     * @param callback
     */
    public static void deleteAccount(Context context, String account, XGIOperateCallback callback) {
        if (TextUtils.isEmpty(account)) {
            throw new IllegalArgumentException("The account parameter can not be null!");
        }

        if (callback == null) {
            XGPushManager.delAccount(context, account);
        } else {
            XGPushManager.delAccount(context, account, callback);
        }
    }


    /**
     * 获取设备token, 在registerPush成功之后调用才能正确返回
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        return XGPushConfig.getToken(context);
    }

    /**
     * 设置标签
     *
     * @param context
     * @param tag
     */
    public static void setTag(Context context, String tag) {
        if (TextUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("The tag parameter can not be null!");
        }
        XGPushManager.setTag(context, tag);
    }

    /**
     * 删除标签
     *
     * @param context
     * @param tag
     */
    public static void deleteTag(Context context, String tag) {
        if (TextUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("The tag parameter can not be null!");
        }
        XGPushManager.deleteTag(context, tag);
    }


    /**
     * 显示本地通知
     *
     * @param context
     * @param title
     * @param content
     * @param customContent 自定义内容, 可以为空
     * @return
     */
    public static boolean showLocalNotification(Context context, @NonNull String title, @NonNull String content, @Nullable String customContent) {
        return TSLNotificationUtil.showLocalNotification(context, title, content, customContent, res_small_icon);
//        return false;
    }
}
