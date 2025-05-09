package com.weex.weexextra;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;

import com.taobao.weex.utils.WXLogUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WX-GXM-1326 on 2018/7/24.
 */

public class ClipboardUtil {

    public static final String RESULT = "result";
    public static final String DATA = "data";
    public static final String RESULT_OK = "success";
    public static final String RESULT_FAILED = "failed";
    private final String CLIP_KEY = "WEEX_CLIP_KEY_MAIN";

    public static void setString(Context context, String label, String text) {
        if (null == text) {
            return;
        }

        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        clipboard.setPrimaryClip(clip);
    }


    public static Map getString(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        Map<String, Object> map = new HashMap<>(2);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            ClipData.Item item = clip.getItemAt(0);
            CharSequence text = coerceToText(context, item);

            map.put(RESULT, text != null ? RESULT_OK : RESULT_FAILED);
            map.put(DATA, text != null ? text : "");
        } else {
            map.put(RESULT, RESULT_FAILED);
            map.put(DATA, "");
        }
        return map;
    }


    private static CharSequence coerceToText(Context context, ClipData.Item item) {
        // Condition 1. just a simple text
        CharSequence text = item.getText();
        if (text != null) {
            return text;
        }

        // Condition 2. a URI value
        Uri uri = item.getUri();
        if (uri != null) {
            InputStreamReader reader = null;
            FileInputStream stream = null;
            try {
                AssetFileDescriptor assetFileDescriptor = context.getContentResolver().openTypedAssetFileDescriptor(uri, "text/*", null);
                stream = assetFileDescriptor.createInputStream();
                reader = new InputStreamReader(stream, "UTF-8");

                StringBuilder builder = new StringBuilder(128);
                char[] buffer = new char[8192];
                int len;
                while ((len = reader.read(buffer)) > 0) {
                    builder.append(buffer, 0, len);
                }
                return builder.toString();

            } catch (FileNotFoundException e) {
                //  ignore.
            } catch (IOException e) {
                WXLogUtils.w("ClippedData Failure loading text.", e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        // ignore
                    }
                }
            }

            return uri.toString();
        }

        // Condition 3.  an intent.
        Intent intent = item.getIntent();
        if (intent != null) {
            return intent.toUri(Intent.URI_INTENT_SCHEME);
        }

        // else case
        return null;
    }
}
