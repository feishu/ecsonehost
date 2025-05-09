package com.ucmed.pushcenterlib.OPPOPush;

import com.ucmed.pushcenterlib.PushResultCallBack;

public class OPPOPushCallbackManager {

    private static OPPOPushCallbackManager ourInstance = new OPPOPushCallbackManager();

    private OPPOPushCallbackManager() {

    }

    public static OPPOPushCallbackManager getInstance() {
        return ourInstance;
    }

    private PushResultCallBack initCallback;
    private PushResultCallBack registerCallback;
    private PushResultCallBack unRegisterCallback;
    private PushResultCallBack getAliasesCallback;
    private PushResultCallBack setAliasesCallback;
    private PushResultCallBack unsetAliasesCallback;
    private PushResultCallBack setUserAccountsCallback;
    private PushResultCallBack unsetUserAccountsCallback;
    private PushResultCallBack getUserAccountsCallback;
    private PushResultCallBack setTagsCallback;
    private PushResultCallBack unsetTagsCallback;
    private PushResultCallBack getTagsCallback;
    private PushResultCallBack getPushStatusCallback;
    private PushResultCallBack setPushTimeCallback;
    private PushResultCallBack getNotificationStatusCallback;
    private PushResultCallBack getRegisterCallback;

    public PushResultCallBack getGetRegisterCallback() {
        return getRegisterCallback;
    }

    public void setGetRegisterCallback(PushResultCallBack getRegisterCallback) {
        this.getRegisterCallback = getRegisterCallback;
    }

    public PushResultCallBack getInitCallback() {
        return initCallback;
    }

    public void setInitCallback(PushResultCallBack initCallback) {
        this.initCallback = initCallback;
    }

    public PushResultCallBack getRegisterCallback() {
        return registerCallback;
    }

    public void setRegisterCallback(PushResultCallBack registerCallback) {
        this.registerCallback = registerCallback;
    }

    public PushResultCallBack getUnRegisterCallback() {
        return unRegisterCallback;
    }

    public void setUnRegisterCallback(PushResultCallBack unRegisterCallback) {
        this.unRegisterCallback = unRegisterCallback;
    }

    public PushResultCallBack getGetAliasesCallback() {
        return getAliasesCallback;
    }

    public void setGetAliasesCallback(PushResultCallBack getAliasesCallback) {
        this.getAliasesCallback = getAliasesCallback;
    }

    public PushResultCallBack getSetAliasesCallback() {
        return setAliasesCallback;
    }

    public void setSetAliasesCallback(PushResultCallBack setAliasesCallback) {
        this.setAliasesCallback = setAliasesCallback;
    }

    public PushResultCallBack getUnsetAliasesCallback() {
        return unsetAliasesCallback;
    }

    public void setUnsetAliasesCallback(PushResultCallBack unsetAliasesCallback) {
        this.unsetAliasesCallback = unsetAliasesCallback;
    }

    public PushResultCallBack getSetUserAccountsCallback() {
        return setUserAccountsCallback;
    }

    public void setSetUserAccountsCallback(PushResultCallBack setUserAccountsCallback) {
        this.setUserAccountsCallback = setUserAccountsCallback;
    }

    public PushResultCallBack getUnsetUserAccountsCallback() {
        return unsetUserAccountsCallback;
    }

    public void setUnsetUserAccountsCallback(PushResultCallBack unsetUserAccountsCallback) {
        this.unsetUserAccountsCallback = unsetUserAccountsCallback;
    }

    public PushResultCallBack getGetUserAccountsCallback() {
        return getUserAccountsCallback;
    }

    public void setGetUserAccountsCallback(PushResultCallBack getUserAccountsCallback) {
        this.getUserAccountsCallback = getUserAccountsCallback;
    }

    public PushResultCallBack getSetTagsCallback() {
        return setTagsCallback;
    }

    public void setSetTagsCallback(PushResultCallBack setTagsCallback) {
        this.setTagsCallback = setTagsCallback;
    }

    public PushResultCallBack getUnsetTagsCallback() {
        return unsetTagsCallback;
    }

    public void setUnsetTagsCallback(PushResultCallBack unsetTagsCallback) {
        this.unsetTagsCallback = unsetTagsCallback;
    }

    public PushResultCallBack getGetTagsCallback() {
        return getTagsCallback;
    }

    public void setGetTagsCallback(PushResultCallBack getTagsCallback) {
        this.getTagsCallback = getTagsCallback;
    }

    public PushResultCallBack getGetPushStatusCallback() {
        return getPushStatusCallback;
    }

    public void setGetPushStatusCallback(PushResultCallBack getPushStatusCallback) {
        this.getPushStatusCallback = getPushStatusCallback;
    }

    public PushResultCallBack getSetPushTimeCallback() {
        return setPushTimeCallback;
    }

    public void setSetPushTimeCallback(PushResultCallBack setPushTimeCallback) {
        this.setPushTimeCallback = setPushTimeCallback;
    }

    public PushResultCallBack getGetNotificationStatusCallback() {
        return getNotificationStatusCallback;
    }

    public void setGetNotificationStatusCallback(PushResultCallBack getNotificationStatusCallback) {
        this.getNotificationStatusCallback = getNotificationStatusCallback;
    }
}
