package com.ucmed.mrdc.tslnet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ucmed.mrdc.tslnet.net.NetAdapterManager;
import com.ucmed.mrdc.tslnet.net.TSLNetStatusListener;

import static com.ucmed.mrdc.tslnet.NetUtil.NETWORK_MOBILE_2G;
import static com.ucmed.mrdc.tslnet.NetUtil.NETWORK_MOBILE_3G;
import static com.ucmed.mrdc.tslnet.NetUtil.NETWORK_MOBILE_4G;
import static com.ucmed.mrdc.tslnet.NetUtil.NETWORK_WIFI;


/**
 * Created by WX-GXM-1326 on 2017/6/20.
 */

public class NetworkReceiver extends BroadcastReceiver {
    private static String _NETWORK = NetUtil.NETWORK_UNKOWN;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG", "intent============>>>>" + intent.toString());
        if (!_NETWORK.equals(NetUtil.getNetworkType(context))) {
            _NETWORK = NetUtil.getNetworkType(context);
            TSLNetStatusListener listener = NetAdapterManager.getInstance().getTslNetStatusListener();

            if (listener != null) {
                listener.onStatusChange(_NETWORK.equals(NETWORK_MOBILE_2G) ||
                        _NETWORK.equals(NETWORK_MOBILE_3G) ||
                        _NETWORK.equals(NETWORK_MOBILE_4G) ||
                        _NETWORK.equals(NETWORK_WIFI), _NETWORK);
            }

        }
    }
}
