package com.ucmed.pushcenterlib.XgPush;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.huawei.hms.support.api.push.PushReceiver;
import com.ucmed.pushcenterlib.PushCenterManager;

import java.io.FileWriter;
import java.io.IOException;

public class TSLHWReceiver extends PushReceiver {
    @Override
    public void onEvent(Context context, Event arg1, Bundle arg2) {
        super.onEvent(context, arg1, arg2);
        if (PushCenterManager.getInstance().isDebug)
            showToast("onEvent" + arg1 + " Bundle " + arg2, context);
    }

    @Override
    public boolean onPushMsg(Context context, byte[] arg1, Bundle arg2) {
        if (PushCenterManager.getInstance().isDebug)
            showToast("onPushMsg" + new String(arg1) + " Bundle " + arg2, context);
        return super.onPushMsg(context, arg1, arg2);
    }

    @Override
    public void onPushMsg(Context context, byte[] arg1, String arg2) {
        if (PushCenterManager.getInstance().isDebug)
            showToast("onPushMsg" + new String(arg1) + " arg2 " + arg2, context);
        super.onPushMsg(context, arg1, arg2);
    }

    @Override
    public void onPushState(Context context, boolean arg1) {
        if (PushCenterManager.getInstance().isDebug)
            showToast("onPushState" + arg1, context);
        super.onPushState(context, arg1);
    }

    @Override
    public void onToken(Context context, String arg1, Bundle arg2) {
        super.onToken(context, arg1, arg2);
        if (PushCenterManager.getInstance().isDebug)
            showToast(" onToken" + arg1 + "bundke " + arg2, context);
    }

    @Override
    public void onToken(Context context, String arg1) {
        super.onToken(context, arg1);
        if (PushCenterManager.getInstance().isDebug)
            showToast(" onToken" + arg1, context);
    }

    public void showToast(final String toast, final Context context) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

    private void writeToFile(String conrent) {
        String SDPATH = Environment.getExternalStorageDirectory() + "/huawei.txt";
        try {
            FileWriter fileWriter = new FileWriter(SDPATH, true);

            fileWriter.write(conrent + "\r\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
