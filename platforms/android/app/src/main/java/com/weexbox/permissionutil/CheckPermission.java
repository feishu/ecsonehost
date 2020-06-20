package com.weexbox.permissionutil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.bugfender.sdk.MyBugfender;
import com.lelibrary.androidlelibrary.sqlite.SqLiteDeviceCommand;
import com.weexbox.permissionutil.model.PermissionModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CheckPermission {
    private static final String TAG = CheckPermission.class.getName();
    private Activity activity=null;
    private Fragment fragment=null;
    private CPCallback cpCallback=null;

    public CheckPermission(Activity activity, Fragment fragment, CPCallback cpCallback) {
        this.activity = activity;
        if(activity==null){
            if(fragment!=null){
                this.activity = fragment.getActivity();
            }
        }
        this.fragment = fragment;
        this.cpCallback = cpCallback;
    }

    public boolean IsPermissionsGranted() {
        if(activity==null){
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissionsNeeded = new ArrayList<>();
            final List<String> permissionsList = new ArrayList<>();
            try{
                if(cpCallback!=null){
                    if(cpCallback.getPermissionModelList()!=null){
                        Iterator keyIterator = cpCallback.getPermissionModelList().keySet().iterator();
                        while (keyIterator.hasNext()){
                            Object keyName = keyIterator.next();
                            PermissionModel permissionModel=cpCallback.getPermissionModelList().get(keyName);
                            if(permissionModel!=null){
                                if (!addPermission(permissionsList, String.valueOf(keyName))){
                                    permissionsNeeded.add(permissionModel.getPermissionDetails());
                                }
                            }
                        }
                    }
                }
            }catch (Exception e){
                MyBugfender.Log.e(TAG,e);
            }

            if (permissionsList.size() > 0) {
                if (permissionsNeeded.size() > 0) {
                    // Need Rationale
                    boolean commaShow=false;
                    String message = "You need to grant access to ";
                    for (int i = 0; i < permissionsNeeded.size(); i++){
                        if(!TextUtils.isEmpty(permissionsNeeded.get(i))){
                            if(commaShow){
                                message = message + ", " + permissionsNeeded.get(i);
                            }else{
                                commaShow=true;
                                message = message + permissionsNeeded.get(i);
                            }
                        }
                    }
                    showMessageDialog(message,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (!permissionsList.isEmpty()) {
                                        if (fragment != null) {
                                            fragment.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), Permission.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                                        } else {
                                            ActivityCompat.requestPermissions(activity, permissionsList.toArray(new String[permissionsList.size()]), Permission.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                                        }
                                    }
                                }
                            },
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(cpCallback!=null){
                                        cpCallback.onStopApp();
                                    }
                                }
                            });
                    return false;
                }
                if (!permissionsList.isEmpty()) {
                    if(fragment!=null){
                        fragment.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), Permission.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                    }else{
                        ActivityCompat.requestPermissions(activity,permissionsList.toArray(new String[permissionsList.size()]), Permission.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                    }
                }
                return false;
            }
            if(this.cpCallback!=null){
                this.cpCallback.isGranted();
            }
            return true;
        }else{
            if(this.cpCallback!=null){
                this.cpCallback.isGranted();
            }
            return true;
        }
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        try{
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
                // Check for Rationale Option
                if (!ActivityCompat.shouldShowRequestPermissionRationale(activity,permission)){
                    return false;
                }
            }
        }catch (Exception e){
            MyBugfender.Log.e(TAG, e);
        }
        return true;
    }

    private int checkSelfPermission(String permission) {
        return ContextCompat.checkSelfPermission(activity, permission);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        //Log.d(TAG,"CheckPermission onActivityResult requestCode => "+requestCode);
        if (requestCode == Permission.REQUEST_CODE_ASK_MULTIPLE_PERMISSION_ENABLE) {
            IsPermissionsGranted();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        try{
          //  Log.d(TAG,"CheckPermission onRequestPermissionsResult requestCode => "+requestCode+ " permissions => "+permissions.toString()+ " grantResults => "+grantResults.toString());
            switch (requestCode) {
                case Permission.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS:
                     try{
                        if(cpCallback!=null){
                            if(cpCallback.getPermissionModelList()!=null){
                                // Fill with results
                                boolean all_permission_granted=true;
                                for (int i = 0; i < permissions.length; i++) {
                                    String keyName=permissions[i];
                                    PermissionModel permissionModel=cpCallback.getPermissionModelList().get(keyName);
                                    permissionModel.setPermissionStatus(grantResults[i]);
                                    if(permissionModel.getPermissionStatus() != PackageManager.PERMISSION_GRANTED){
                                        all_permission_granted = false;
                                    }
                                    cpCallback.getPermissionModelList().put(keyName,permissionModel);
                                }

                                if (all_permission_granted) {
                                    // All Permissions Granted
                                    if(this.cpCallback!=null){
                                        this.cpCallback.isGranted();
                                    }
                                } else {
                                    // Permission Denied
                                    //Toast.makeText(activity, "Some Permission is Denied", Toast.LENGTH_SHORT).show();
                                    MyBugfender.Log.i(TAG, "Some Permission is Denied");
                                    openPermissionsSettings(activity.getApplicationContext().getPackageName());
                                }
                            }
                        }
                    }catch (Exception e){
                        MyBugfender.Log.e(TAG,e);
                    }
                break;
            }
        }catch (Exception e){
            MyBugfender.Log.e(TAG, e);
        }
    }

    private void showMessageDialog(@NonNull String message, @NonNull DialogInterface.OnClickListener okListener, @NonNull DialogInterface.OnClickListener cancelListener) {
        try{
            AlertDialog.Builder alertBuilder =  new AlertDialog.Builder(activity)
                    .setCancelable(false)
                    .setTitle("")
                    .setMessage(message)
                    .setPositiveButton(activity.getString(android.R.string.ok), okListener)
                    .setNegativeButton(activity.getString(android.R.string.cancel), cancelListener);

            AlertDialog alertDialog = alertBuilder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.setCancelable(false);
            alertDialog.show();
        }catch (Exception e){
            MyBugfender.Log.e(TAG, e);
        }
    }

    private void openPermissionsSettings(@NonNull String packageName) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //activity.startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + packageName)));
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.setData(Uri.parse("package:" + packageName));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                if(fragment!=null){
                    fragment.startActivityForResult(intent, Permission.REQUEST_CODE_ASK_MULTIPLE_PERMISSION_ENABLE);
                }
                else{
                    activity.startActivityForResult(intent, Permission.REQUEST_CODE_ASK_MULTIPLE_PERMISSION_ENABLE);
                }
            }
        }catch (Exception e){
            MyBugfender.Log.e(TAG, e);
        }
    }



/*    public void checkOne(@NonNull final String permission, @Nullable final String dialogMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!TextUtils.isEmpty(dialogMessage)) {
                checkPermissionWithUserDialog(permission, dialogMessage);
            } else {
                checkPermission(permission);
            }
        }
    }

    public void checkMultiple(@NonNull final String[] permissions, @Nullable final String dialogMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!TextUtils.isEmpty(dialogMessage)) {
                checkPermissionsWithUserDialog(permissions, dialogMessage);
            } else {
                checkPermissions(permissions);
            }
        }
    }

    public void openPermissionsSettings(@NonNull String packageName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + packageName)));
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission(@NonNull final String permission) {
        int hasSpecificPermission = ContextCompat.checkSelfPermission(context, permission);

        if (hasSpecificPermission != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{permission},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions(@NonNull final String[] permissions) {
        int nonGrantedPermissions = 0;
        for (String permission : permissions) {
            MyBugfender.Log.d(TAG, "checkPermissions: permissions " + permission);
        }
        if (permissions.length > 0) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    nonGrantedPermissions++;
                }
            }

            if (nonGrantedPermissions != 0) {
                final String[] permissionsList = getNonGrantedPermissions(permissions);
                if (permissionsList != null) {
                    activity.requestPermissions(permissionsList, REQUEST_CODE_ASK_PERMISSIONS);
                }
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissionWithUserDialog(@NonNull final String permission,
                                               @NonNull final String dialogMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasSpecificPermission = ContextCompat.checkSelfPermission(context, permission);
            if (hasSpecificPermission != PackageManager.PERMISSION_GRANTED) {
                showMessageDialog(dialogMessage,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                activity.requestPermissions(new String[]{permission},
                                        REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissionsWithUserDialog(@NonNull final String[] permissions,
                                                @NonNull final String dialogMessage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            showMessageDialog(dialogMessage,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            checkPermissions(permissions);
                        }
                    });
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private String[] getNonGrantedPermissions(@NonNull String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissionList = new ArrayList<>(Arrays.asList(permissions));
            for (int i = 0; i < permissionList.size(); i++) {
                if (ContextCompat.checkSelfPermission(activity, permissionList.get(i)) == PackageManager.PERMISSION_GRANTED) {
                    permissionList.remove(i);
                }
            }
            return permissionList.toArray(new String[permissionList.size()]);
        }
        return null;
    }*/



    public void onDestroy() {
        activity=null;
        fragment=null;
        cpCallback=null;
    }
}
