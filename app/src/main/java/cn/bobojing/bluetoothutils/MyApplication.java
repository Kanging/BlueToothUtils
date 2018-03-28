package cn.bobojing.bluetoothutils;

import android.app.Application;
import cn.bobojing.bluetoothutils.btUtils.BlueToothUtils;


public class MyApplication extends Application {
    public static BlueToothUtils mBtu;
    @Override
    public void onCreate() {
        super.onCreate();
        mBtu = new BlueToothUtils(this);
    }
    public static BlueToothUtils getmBtu() {
        return mBtu;
    }
}
