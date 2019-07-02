package com.weex.weexextra;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/**
 * Created by WX-GXM-1326 on 2018/7/26.
 */

public class DeviceUtil {

    static Map res;

    public static Map getDevice(Context context) {
        if (res != null) return res;
        res = new HashMap();
        res.put("platform", "Android");
        res.put("brand", Build.BRAND);
        res.put("model", Build.MODEL);
        res.put("pixelRatio", ScreenUtil.getInstance((Activity) context).pixelRatio);
        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        DisplayMetrics book = new DisplayMetrics();
        display.getMetrics(book);
        res.put("windowWidth", ScreenUtil.getInstance((Activity) context).DisplayWidth);
        res.put("windowHeight", ScreenUtil.getInstance((Activity) context).DisplayHeight);
        res.put("screenWidth", display.getWidth());
        res.put("screenHeight", display.getHeight());
        res.put("statusBarHeight", ScreenUtil.getInstance((Activity) context).NavigationBarHeight);

        res.put("xdpi", context.getResources().getDisplayMetrics().xdpi);
        res.put("ydpi", context.getResources().getDisplayMetrics().ydpi);
        res.put("widthPixels", context.getResources().getDisplayMetrics().widthPixels);
        res.put("heightPixels", context.getResources().getDisplayMetrics().heightPixels);

        res.put("language", Locale.getDefault().getLanguage());
        String versionName = null;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        res.putAll(getPhoneIMEI(context));
        res.put("ip", getIPAddress(context));
        res.put("mac", getMacAddress());
        res.put("version", versionName);
        res.put("system", Build.VERSION.RELEASE);
        res.put("fontSizeSetting", book.scaledDensity); //字体缩放比例
        res.put("SDKVersion", Build.VERSION.SDK_INT);
        if (res.containsKey("imei")) {
            String androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
            UUID deviceUuid = new UUID(androidId.hashCode(), (Long.valueOf(res.get("imei").toString()).longValue() << 32) | String.valueOf(res.get("sn")).hashCode());
            res.put("uuid", deviceUuid.toString());
        } else {
            if (SharedPreferencesUtil.contain("uuid")) {
                res.put("uuid", String.valueOf(SharedPreferencesUtil.get("uuid")));
            } else {
                String uuid = getMyUUID();
                res.put("uuid", uuid);
                SharedPreferencesUtil.put("uuid", uuid);
            }
        }
        return res;
    }

    private static String getMyUUID() {
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }

    public static String getUUID(Context context,String imei) {

        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    serial = Build.getSerial();
                }else {
                    serial = imei;
                }
            } else {
                serial = Build.SERIAL;
            }
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = imei; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * 获得IP地址，分为两种情况，一是wifi下，二是移动网络下，得到的ip地址是不一样的
     */
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
//                if (!wifiManager.isWifiEnabled()){
//                    wifiManager.setWifiEnabled(true);
//                    wifiManager.setWifiEnabled(false);
//                }
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                //调用方法将int转换为地址字符串
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    public static String getMacAddress() {
 /*获取mac地址有一点需要注意的就是android 6.0版本后，以下注释方法不再适用，不管任何手机都会返回"02:00:00:00:00:00"这个默认的mac地址，这是googel官方为了加强权限管理而禁用了getSYstemService(Context.WIFI_SERVICE)方法来获得mac地址。*/
        //        String macAddress= "";
//        WifiManager wifiManager = (WifiManager) MyApp.getContext().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        macAddress = wifiInfo.getMacAddress();
//        return macAddress;

        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }

    /**
     * 获取手机的IMEI号码
     */
    public static Map getPhoneIMEI(final Context context) {
        final TelephonyManager mTm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Map map = new HashMap();
                map.put("imei", mTm.getDeviceId());
                map.put("imsi", mTm.getSubscriberId());
                map.put("number", mTm.getLine1Number());
                map.put("sn", mTm.getSimSerialNumber());
                return map;
            } else {
                return new HashMap();
            }
        } else {
            Map map = new HashMap();
            map.put("imei", mTm.getDeviceId());
            map.put("imsi", mTm.getSubscriberId());
            map.put("number", mTm.getLine1Number());
            map.put("sn", mTm.getSimSerialNumber());
            return map;
        }
    }
}
