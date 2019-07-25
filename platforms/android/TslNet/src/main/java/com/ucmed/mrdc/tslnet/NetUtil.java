package com.ucmed.mrdc.tslnet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;


/**
 * Created by WX-GXM-1326 on 2017/6/19.
 */

public class NetUtil {
    /**
     * 未知状态
     */
    public static final String NETWORK_UNKOWN = "unknown";
    /**
     * 没有连接网络
     */
    public static final String NETWORK_NONE = "none";
    /**
     * 移动网络 4g/3g/2g
     */
    public static final String NETWORK_MOBILE_4G = "4g";
    public static final String NETWORK_MOBILE_3G = "3g";
    public static final String NETWORK_MOBILE_2G = "2g";
    /**
     * 无线网络
     */
    public static final String NETWORK_WIFI = "wifi";

    public static String getNetworkType(Context context) {

        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            NetworkCapabilities networkCapabilities = mConnectivityManager.getNetworkCapabilities(mConnectivityManager.getActiveNetwork());
            if(networkCapabilities !=null) {
                Log.d("Avalible", "NetworkCapalbilities:" + networkCapabilities.toString());
                if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)) {

                } else {
                    return NETWORK_NONE;
                }
            }else{
                return NETWORK_NONE;
            }
        }

        NetworkInfo activeNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                int networkType = activeNetworkInfo.getSubtype();
                String _strSubTypeName = activeNetworkInfo.getSubtypeName();
                Log.e("logger", "Network getSubtypeName : " + _strSubTypeName);

                Log.d("logger", "Network getSubtype : " + Integer.valueOf(networkType).toString());
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        Log.e("logger", "Network Type : " + NETWORK_MOBILE_2G);
                        return NETWORK_MOBILE_2G;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        return NETWORK_MOBILE_3G;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        return NETWORK_MOBILE_4G;
                    default:
                        return NETWORK_UNKOWN;
                }
            }
        } else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }
}
