package com.ucmed.mrdc.tslnet.net;

/**
 * Created by WX-GXM-1326 on 2018/4/25.
 */

public interface TSLNetStatusListener {
    void onStatusChange(boolean isConnected, String networkType);
}
