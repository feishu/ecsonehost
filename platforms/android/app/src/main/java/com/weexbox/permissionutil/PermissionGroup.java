package com.weexbox.permissionutil;

import android.Manifest;
import android.os.Build;
//import android.support.annotation.RequiresApi;
import androidx.annotation.RequiresApi;
//https://inthecheesefactory.com/blog/things-you-need-to-know-about-android-m-permission-developer-edition/en
public class PermissionGroup {

    public static final String CALENDAR = Manifest.permission_group.CALENDAR;
    public static final String CAMERA = Manifest.permission_group.CAMERA;
    public static final String CONTACTS = Manifest.permission_group.CONTACTS;
    public static final String LOCATION = Manifest.permission_group.LOCATION;
    public static final String MICROPHONE = Manifest.permission_group.MICROPHONE;
    public static final String PHONE = Manifest.permission_group.PHONE;
    public static final String SENSORS = Manifest.permission_group.SENSORS;
    public static final String SMS = Manifest.permission_group.SMS;
    public static final String STORAGE = Manifest.permission_group.STORAGE;

/*    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    public static final String BODY_SENSORS = Manifest.permission.BODY_SENSORS;*/
}
