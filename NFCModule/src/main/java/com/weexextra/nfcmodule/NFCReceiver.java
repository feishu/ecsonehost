package com.weexextra.nfcmodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;

import java.util.ArrayList;
import java.util.Arrays;

public class NFCReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (context != null && intent != null && intent.getAction() != null) {
            if (intent.getAction().equals(context.getPackageName()+"npc")){
                Tag mTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);//获取到Tag标签对象
                String[] techList = mTag.getTechList();
                System.out.println("标签支持的tachnology类型：");
                for (String tech : techList) {
                    System.out.println(tech);
                    if(tech.contains("MifareClassic"))NFCAdapter.getInstance().setHaveMifareClissic(true);
                }
                NFCAdapter.getInstance().setTag(mTag);
                NFCAdapter.getInstance().getGetTag().onGetTag(new ArrayList<>(Arrays.asList(techList)));
            }
        }

    }
}
