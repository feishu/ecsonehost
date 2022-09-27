package com.ucmed.pushcenterlib;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.annotation.RequiresApi;
//import android.support.v4.app.NotificationCompat;
//import android.support.v4.app.NotificationManagerCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXGlobalEventReceiver;
import com.taobao.weex.utils.WXLogUtils;
import com.weex.weexextra.RequestIdGenerator;
import com.weex.weexextra.SharedPreferencesUtil;
import com.weex.weexextra.TSLResourcesUtil;
import com.weexbox.core.router.Router;

import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

public class TSLNotificationUtil {

    /**
     * 发布推送相关的全局事件
     *
     * @param context
     * @param info
     */
    protected static String PUSH_EVENT_NAME = "tsl2-push";

    public static void firePushGlobalEvent(Context context, JSONObject info) {
        Intent intent = new Intent(WXGlobalEventReceiver.EVENT_ACTION);
        intent.putExtra(WXGlobalEventReceiver.EVENT_NAME, context.getPackageName()+PUSH_EVENT_NAME);
        intent.putExtra(WXGlobalEventReceiver.EVENT_PARAMS, info.toJSONString());
        context.sendBroadcast(intent);
    }

    /**
     * 判断是否允许通知
     *
     * @param context
     * @return
     */
    public static boolean isNotificationOpened(Context context) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        return notificationManagerCompat.areNotificationsEnabled();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNotificationChannel(Context context) {
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // 通知渠道的id
        String id = "tsl-push-channel";
        // 用户可以看到的通知渠道的名字.
        CharSequence name = "系统";
        // 用户可以看到的通知渠道的描述
        String description = "系统通知";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(id, name, importance);
        // 配置通知渠道的属性
        mChannel.setDescription(description);
        //最后在notificationmanager中创建该通知渠道
        mNotificationManager.createNotificationChannel(mChannel);
    }

    public static boolean showLocalNotification(Context context, @NonNull String title, @NonNull String content, @Nullable String customContent, int res_small_icon) {
       return showLocalNotification(context,title,content,customContent,res_small_icon,null);
    }

    public static boolean showLocalNotification(Context context, @NonNull String title, @NonNull String content, @Nullable String customContent, int res_small_icon,
                                                Uri sound) {
        if (!isNotificationOpened(context)) {
            WXLogUtils.w("showLocalNotification", "通知栏关闭");
            return false;
        }
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        if (res_small_icon > 0)
            builder.setSmallIcon(res_small_icon);
        else {
            int resId = TSLResourcesUtil.getResourceInMipmap(context, "ic_launcher");
            if (resId > 0)
                builder.setSmallIcon(resId);
            else
                throw new IllegalArgumentException("SmallIcon ==0");
        }
        if(sound!=null)
            builder.setSound(sound);
        else
            builder.setDefaults(Notification.DEFAULT_ALL);

        builder.setTicker(title + ":" + content); //通知首次出现在通知栏，带上升动画效果的
        //设置通知标题
        builder.setContentTitle(title);
        //设置通知内容
        builder.setContentText(content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(context);
        }
        builder.setChannelId("tsl-push-channel");

        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        builder.setWhen(System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Intent intent = new Intent(context, NotificationActivity.class);
            if (customContent != null)
                intent.putExtra("customContent", customContent);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(contentIntent);
        }else {
            Intent intent = new Intent(context.getPackageName() + TSLNotificationClickMsgReceiver.ACTION_CLICK);
            if (customContent != null)
                intent.putExtra("customContent", customContent);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            PendingIntent contentIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(contentIntent);
        }
        builder.setAutoCancel(true);
        Notification notification=builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notifyManager.notify(RequestIdGenerator.nextRequestId(), notification);
        return true;
    }

    public static class TSLNotificationClickMsgReceiver extends BroadcastReceiver {
        public static final String LogTag = "TSLNotificationClickMsgReceiver";
        public static final String ACTION_CLICK = ".tslpush.notification.click";

        @Override
        public void onReceive(Context context, Intent intent) {
            if (context != null && intent != null && intent.getAction() != null) {
                if (intent.getAction().equals(context.getPackageName()+ACTION_CLICK))
                    //
                    onNotifactionClickedResult(context, intent);
            }
        }

        // 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
        public void onNotifactionClickedResult(Context context, Intent intent) {
            WXLogUtils.w(LogTag, "消息点击....");
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("title", intent.getStringExtra("title"));
            jsonObject1.put("content", intent.getStringExtra("content"));
            if (intent.getStringExtra("customContent") != null)
                jsonObject1.put("customContent", intent.getStringExtra("customContent"));
            if (Router.Companion.getRoutes().size() < 1) {
                WXLogUtils.w(LogTag, "消息点击.... 应用未启动");
                //当前页面栈中没有页面时,启动应用
                Intent intents = context.getPackageManager().
                        getLaunchIntentForPackage(context.getPackageName());
                SharedPreferencesUtil.init(context);
                SharedPreferencesUtil.put("PUSH_CONTENT",jsonObject1.toJSONString());
                intents.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                context.startActivity(intents);
            } else {
                WXLogUtils.w(LogTag, "消息点击.... weex页面");
                //weex页面时 发送全局事件
                jsonObject1.put("type", "PUSH_CLICK_CALLBACK");
                firePushGlobalEvent(context, jsonObject1);
                setTopApp(context);
            }
//            else {
//                WXLogUtils.w(LogTag, "消息点击.... native页面");
//                //原生界面时, 发送事件由界面自己处理
////                EventBus.getDefault().post(new TSLNotificationClickEvent(jsonObject1));
//            }
        }

    }

    /**
     * 将本应用置顶到最前端
     * 当本应用位于后台时，则将它切换到最前端
     *
     * @param context
     */
    public static void setTopApp(Context context) {
        if (!isRunningForeground(context)) {
            /**获取ActivityManager*/
            ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

            /**获得当前运行的task(任务)*/
            List<ActivityManager.RunningTaskInfo> taskInfoList = activityManager.getRunningTasks(100);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                /**找到本应用的 task，并将它切换到前台*/
                if (taskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                    activityManager.moveTaskToFront(taskInfo.id, 0);
                    break;
                }
            }
        }
    }

    /**
     * 判断本应用是否已经位于最前端
     *
     * @param context
     * @return 本应用已经位于最前端时，返回 true；否则返回 false
     */
    public static boolean isRunningForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfoList = activityManager.getRunningAppProcesses();
        /**枚举进程*/
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfoList) {
            if (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                if (appProcessInfo.processName.equals(context.getApplicationInfo().processName)) {
                    return true;
                }
            }
        }
        return false;
    }

}
