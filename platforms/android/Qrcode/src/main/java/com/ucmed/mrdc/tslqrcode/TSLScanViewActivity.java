package com.ucmed.mrdc.tslqrcode;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.mylhyl.zxing.scanner.decode.QRDecode;
import com.ucmed.mrdc.teslacore.module.TSLModuleAdapterManager;
import com.ucmed.teslah5nativebrigelib.TSLCallAdapterInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by mdp on 2018/3/16.
 * Version:1.0.0
 */

public class TSLScanViewActivity extends Activity implements View.OnClickListener {
    ScannerView scannerView;
    boolean isFlashOn = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tslcore_activity_scan);
        initUI();
    }

    private void initUI() {
        findViewById(R.id.back).setOnClickListener(this);
        findViewById(R.id.flash).setOnClickListener(this);
        findViewById(R.id.image).setOnClickListener(this);
        scannerView = (ScannerView) findViewById(R.id.scanner_view);
        scannerView.setOnScannerCompletionListener(new OnScannerCompletionListener() {
            @Override
            public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
                result(rawResult.getText());
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.back) {
            this.finish();
        } else if (id == R.id.flash) {
            if (isFlashOn)
                scannerView.toggleLight(false);
            else
                scannerView.toggleLight(true);//开
            isFlashOn = !isFlashOn;
        } else if (id == R.id.image) {
            pickImage();
        } else {
            this.finish();
        }
    }

    private void pickImage() {
        TSLModuleAdapterManager.getInstance().getTslImageAdapter().chooseImage(this, 1, null, null, Arrays.asList("album"),1, new TSLCallAdapterInterface() {
            @Override
            public void success(String message) {

            }

            @Override
            public void success(Map<String, Object> map) {
                if (map.size() > 0 && map.get("tempFilePaths") != null) {
                    List<String> paths = (List<String>) map.get("tempFilePaths");
                    if (paths.size() > 0)
                        parseImageForCode(paths.get(0));
                }
            }

            @Override
            public void error(String message) {

            }

            @Override
            public void error(Map<String, Object> map) {

            }
        });
    }

    private void parseImageForCode(String path) {
        QRDecode.decodeQR(path, new OnScannerCompletionListener() {
            @Override
            public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
                if (rawResult == null) {
                    Toast.makeText(TSLScanViewActivity.this, R.string.tsl_toast_decodescan_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
                result(rawResult.getText());
            }
        });
    }

    private void result(String text) {
        EventBus.getDefault().post(new ScanQrCodeSuccessEvent(text,"qrCode", "UTF-8"));
        TSLScanViewActivity.this.finish();
    }

    @Override
    protected void onResume() {
        scannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        scannerView.onPause();
        super.onPause();
    }


}
