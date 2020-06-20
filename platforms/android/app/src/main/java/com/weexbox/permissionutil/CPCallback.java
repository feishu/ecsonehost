package com.weexbox.permissionutil;
import com.weexbox.permissionutil.model.PermissionModel;

import java.util.Map;

/**
 * Created by baps on 25-05-2017.
 */

public interface CPCallback {
    Map<String, PermissionModel> getPermissionModelList();
    void isGranted();
    void onStopApp();
}
