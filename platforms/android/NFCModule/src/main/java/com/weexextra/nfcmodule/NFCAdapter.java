package com.weexextra.nfcmodule;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.Ndef;
import android.widget.Toast;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NFCAdapter {
    NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;

    public void setTag(Tag mTag) {
        this.mTag = mTag;
    }

    private Tag mTag;

    public NFCAdapter.getTag getGetTag() {
        return getTag;
    }

    public void setGetTag(NFCAdapter.getTag getTag) {
        this.getTag = getTag;
    }

    //    private Context mContext;
    private getTag getTag;

    interface getTag {
        void onGetTag(List<String> list);
    }

    interface doCallback {
        void onSuccess(String s);

        void onfail(String s);
    }

    private static NFCAdapter ourInstance = new NFCAdapter();

    private NFCAdapter() {

    }

    public static NFCAdapter getInstance() {
        return ourInstance;
    }

    public void init(Context context,doCallback initCallback) {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(context);//设备的NfcAdapter对象
        if (mNfcAdapter == null) {//判断设备是否支持NFC功能
//            Toast.makeText(context, "设备不支持NFC功能!", Toast.LENGTH_SHORT);
            callback(initCallback,false,"设备不支持NFC功能!");
            return ;
        }
        if (!mNfcAdapter.isEnabled()) {//判断设备NFC功能是否打开
//            Toast.makeText(context, "请到系统设置中打开NFC功能!", Toast.LENGTH_SHORT);
            callback(initCallback,false,"请到系统设置中打开NFC功能!");
            return ;
        }
        Intent intent = new Intent(context.getPackageName() + "npc");
        mPendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);//创建PendingIntent对象,当检测到一个Tag标签就会执行此Intent
        callback(initCallback,true,"");
    }

    public void resume(Context context) {
        if (mNfcAdapter != null) {
            mNfcAdapter.enableForegroundDispatch((Activity) context, mPendingIntent, null, null);//打开前台发布系统，使页面优于其它nfc处理.当检测到一个Tag标签就会执行mPendingItent
        }
    }

    public void pause(Context context) {
        if (mNfcAdapter != null) {
            mNfcAdapter.disableForegroundDispatch((Activity) context);//关闭前台发布系统
        }
    }

    public boolean isHaveMifareClissic() {
        return haveMifareClissic;
    }

    public void setHaveMifareClissic(boolean haveMifareClissic) {
        this.haveMifareClissic = haveMifareClissic;
    }

    boolean haveMifareClissic = false;
//    Ndef ndef = Ndef.get(mTag);//获取ndef对象

//    <tech-list>
//		<tech>android.nfc.tech.IsoDep</tech>
//		<tech>android.nfc.tech.NfcA</tech>
//		<tech>android.nfc.tech.NfcB</tech>
//		<tech>android.nfc.tech.NfcF</tech>
//		<tech>android.nfc.tech.NfcV</tech>
//		<tech>android.nfc.tech.Ndef</tech>
//		<tech>android.nfc.tech.NdefFormatable</tech>
//		<tech>android.nfc.tech.MifareClassic</tech>
//		<tech>android.nfc.tech.MifareUltralight</tech>
//	</tech-list>

    /**
     * 创建NDEF文本数据
     *
     * @param text
     * @return
     */
    public NdefRecord createTextRecord(String text) {
        byte[] langBytes = Locale.CHINA.getLanguage().getBytes(Charset.forName("US-ASCII"));
        Charset utfEncoding = Charset.forName("UTF-8");
        //将文本转换为UTF-8格式
        byte[] textBytes = text.getBytes(utfEncoding);
        //设置状态字节编码最高位数为0
        int utfBit = 0;
        //定义状态字节
        char status = (char) (utfBit + langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        //设置第一个状态字节，先将状态码转换成字节
        data[0] = (byte) status;
        //设置语言编码，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1到langBytes.length的位置
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        //设置文本字节，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1 + langBytes.length
        //到textBytes.length的位置
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        //通过字节传入NdefRecord对象
        //NdefRecord.RTD_TEXT：传入类型 读写
        NdefRecord ndefRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, new byte[0], data);
        return ndefRecord;
    }

    private void callback(doCallback callback,boolean isSuccess,String text){
        if(callback!=null){
            if(isSuccess)callback.onSuccess(text);
            else callback.onfail(text);
        }
    }

    //往Ndef标签中写数据
    public void writeNdef(Context context, String text, doCallback writeNdefCallback) {
        if (mTag == null) {
            callback(writeNdefCallback,false,"不能识别的标签类型！");
            return;
        }
        Ndef ndef = Ndef.get(mTag);//获取ndef对象
        if (!ndef.isWritable()) {
            callback(writeNdefCallback,false,"该标签不能写入数据！");
            return;
        }
        NdefRecord ndefRecord = createTextRecord(text);//创建一个NdefRecord对象
        NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{ndefRecord});//根据NdefRecord数组，创建一个NdefMessage对象
        int size = ndefMessage.getByteArrayLength();
        if (ndef.getMaxSize() < size) {
            callback(writeNdefCallback,false,"标签容量不足！");
            return;
        }
        try {
            ndef.connect();//连接
            ndef.writeNdefMessage(ndefMessage);//写数据
            callback(writeNdefCallback,true,"writeNdef success");
        } catch (IOException e) {
            e.printStackTrace();
            callback(writeNdefCallback,false,e.getMessage());
        } catch (FormatException e) {
            e.printStackTrace();
            callback(writeNdefCallback,false,e.getMessage());
        } finally {
            try {
                ndef.close();//关闭连接
            } catch (IOException e) {
                e.printStackTrace();
                callback(writeNdefCallback,false,e.getMessage());
            }
        }
    }

    //读取Ndef标签中数据
    public void readNdef(Context context,doCallback readNdefCallback) {
        if (mTag == null) {
            callback(readNdefCallback,false,"不能识别的标签类型！");
            return;
        }
        Ndef ndef = Ndef.get(mTag);//获取ndef对象
        try {
            ndef.connect();//连接
            NdefMessage ndefMessage = ndef.getNdefMessage();//获取NdefMessage对象
            if (ndefMessage != null)
                callback(readNdefCallback,true,parseTextRecord(ndefMessage.getRecords()[0]));
//            Toast.makeText(context, "数据读取成功！", Toast.LENGTH_SHORT);
        } catch (IOException e) {
            e.printStackTrace();
            callback(readNdefCallback,false,e.getMessage());
        } catch (FormatException e) {
            e.printStackTrace();
            callback(readNdefCallback,false,e.getMessage());
        } finally {
            try {
                ndef.close();//关闭链接
            } catch (IOException e) {
                e.printStackTrace();
                callback(readNdefCallback,false,e.getMessage());
            }
        }
    }

    /**
     * 解析NDEF文本数据，从第三个字节开始，后面的文本数据
     *
     * @param ndefRecord
     * @return
     */
    public static String parseTextRecord(NdefRecord ndefRecord) {
        /**
         * 判断数据是否为NDEF格式
         */
        //判断TNF
        if (ndefRecord.getTnf() != NdefRecord.TNF_WELL_KNOWN) {
            return null;
        }
        //判断可变的长度的类型
        if (!Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
            return null;
        }
        try {
            //获得字节数组，然后进行分析
            byte[] payload = ndefRecord.getPayload();
            //下面开始NDEF文本数据第一个字节，状态字节
            //判断文本是基于UTF-8还是UTF-16的，取第一个字节"位与"上16进制的80，16进制的80也就是最高位是1，
            //其他位都是0，所以进行"位与"运算后就会保留最高位
            String textEncoding = ((payload[0] & 0x80) == 0) ? "UTF-8" : "UTF-16";
            //3f最高两位是0，第六位是1，所以进行"位与"运算后获得第六位
            int languageCodeLength = payload[0] & 0x3f;
            //下面开始NDEF文本数据第二个字节，语言编码
            //获得语言编码
            String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
            //下面开始NDEF文本数据后面的字节，解析出文本
            String textRecord = new String(payload, languageCodeLength + 1,
                    payload.length - languageCodeLength - 1, textEncoding);
            return textRecord;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * 写块
     *
     * @param sector 写入的扇区
     * @param block  写入的块区
     */
    public void writeBlock(Context context, int sector, int block, String text,doCallback writeBlockCallback) {
        if (mTag == null) {
//            Toast.makeText(context, "无法识别的标签！", Toast.LENGTH_SHORT);
            callback(writeBlockCallback,false,"无法识别的标签！");
            return;
        }
        if (!haveMifareClissic){
//            Toast.makeText(context,"不支持MifareClassic",Toast.LENGTH_SHORT);
            callback(writeBlockCallback,false,"不支持MifareClassic");
            return;
        }
        MifareClassic mfc = MifareClassic.get(mTag);
        try {
            mfc.connect();//打开连接
            boolean auth;
            auth = mfc.authenticateSectorWithKeyA(sector, MifareClassic.KEY_DEFAULT);//keyA验证扇区
            if (auth) {
                mfc.writeBlock(block, text.getBytes());//写入数据
                callback(writeBlockCallback,true,"writeBlock success");
//                Toast.makeText(context,"写入成功!",Toast.LENGTH_SHORT);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback(writeBlockCallback,false,e.getMessage());
        } finally {
            try {
                mfc.close();//关闭连接
            } catch (IOException e) {
                e.printStackTrace();
                callback(writeBlockCallback,false,e.getMessage());
            }
        }
    }

    /**
     * 读取块
     *
     * @param context
     * @param sector  写入的扇区
     * @param block   写入的块区
     */
    public void readBlock(Context context, int sector, int block,doCallback readBlockCallback) {
        if (mTag == null) {
//            Toast.makeText(context, "无法识别的标签！", Toast.LENGTH_SHORT);
            callback(readBlockCallback,false,"无法识别的标签！");
            return;
        }
        if (!haveMifareClissic){
//            Toast.makeText(context,"不支持MifareClassic",Toast.LENGTH_SHORT);
            callback(readBlockCallback,false,"不支持MifareClassic");
            return ;
        }
        MifareClassic mfc = MifareClassic.get(mTag);
        try {
            mfc.connect();//打开连接
            boolean auth;
            auth = mfc.authenticateSectorWithKeyA(sector, MifareClassic.KEY_DEFAULT);//keyA验证扇区
            if (auth) {
                callback(readBlockCallback,true,bytesToHexString(mfc.readBlock(block)));
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback(readBlockCallback,false,e.getMessage());
        } finally {
            try {
                mfc.close();//关闭连接
            } catch (IOException e) {
                e.printStackTrace();
                callback(readBlockCallback,false,e.getMessage());
            }
        }
    }

    //字符序列转换为16进制字符串
    private String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("0x");
        if (src == null || src.length <= 0) {
            return null;
        }
        char[] buffer = new char[2];
        for (int i = 0; i < src.length; i++) {
            buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
            buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
            System.out.println(buffer);
            stringBuilder.append(buffer);
        }
        return stringBuilder.toString();
    }
}
