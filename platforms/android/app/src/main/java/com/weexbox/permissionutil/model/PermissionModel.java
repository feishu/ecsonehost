package com.weexbox.permissionutil.model;

import android.content.pm.PackageManager;

public class PermissionModel {
    private String permissionDetails="";
    private int permissionStatus= PackageManager.PERMISSION_GRANTED;
    public PermissionModel(String permissionDetails,int permissionStatus){
        this.permissionDetails=permissionDetails;
        this.permissionStatus=permissionStatus;
    }

    public String getPermissionDetails() {
        return permissionDetails;
    }

    public void setPermissionDetails(String permission) {
        this.permissionDetails = permission;
    }

    public int getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(int permissionStatus) {
        this.permissionStatus = permissionStatus;
    }
}
