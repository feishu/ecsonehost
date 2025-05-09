package com.ucmed.pushcenterlib;

import java.util.Map;

public interface PushResultCallBack {
    void success(Map map);
    void failure(Map map);
}
