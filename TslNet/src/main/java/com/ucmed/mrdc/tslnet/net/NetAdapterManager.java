package com.ucmed.mrdc.tslnet.net;

import com.ucmed.mrdc.tslnet.TSLNetAdapterIml;

public class NetAdapterManager {
    private NetAdapterManager() {

    }

    public static NetAdapterManager getInstance() {
        return ourInstance;
    }

    private static NetAdapterManager ourInstance = new NetAdapterManager();

    TSLNetAdapterInterface tslNetAdapterInterface;

    public TSLNetAdapterInterface getTslNetAdapterInterface() {
        if(tslNetAdapterInterface==null)
            tslNetAdapterInterface = new TSLNetAdapterIml();
        return tslNetAdapterInterface;
    }

    public void setTslNetAdapterInterface(TSLNetAdapterInterface tslNetAdapterInterface) {
        this.tslNetAdapterInterface = tslNetAdapterInterface;
    }

    public TSLNetStatusListener getTslNetStatusListener() {
        return tslNetStatusListener;
    }

    public void setTslNetStatusListener(TSLNetStatusListener tslNetStatusListener) {
        this.tslNetStatusListener = tslNetStatusListener;
    }

    TSLNetStatusListener tslNetStatusListener;
}
