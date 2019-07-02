package com.ucmed.mrdc.tslqrcode;

public class ScanQrCodeSuccessEvent  {

    public String resultStr;
    public String scanType;
    public String charSet;

    public ScanQrCodeSuccessEvent(String resultStr, String scanType, String charSet) {
        this.resultStr = resultStr;
        this.scanType = scanType;
        this.charSet = charSet;
    }
}
