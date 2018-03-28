package cn.bobojing.bluetoothutils;

import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Set;

import cn.bobojing.bluetoothutils.btUtils.BlueToothUtils;

public class BlueActivity extends AppCompatActivity {
    BlueToothUtils btu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue);

        btu=MyApplication.getmBtu();
        openBlueTooth();
    }
    /**
     * 初始化蓝牙
     * notice：所有蓝牙打印信息均在 Log.i("BTU","msg")
     */
    private void openBlueTooth() {
        //打开蓝牙
        btu.enableBluetooth(new BlueToothUtils.enableBluetoothListener() {
            @Override//打开蓝牙后才能进行后续操作
            public void enableSuccess() {
                getDevices();
            }
        });
//        btu.closeBlueTooth();   关闭蓝牙
    }
    /**
     * 获取设备
     */
    private void getDevices() {
        //获取已配对设备
        Set<BluetoothDevice> sb= btu.queryingPairedDevices();
        for(BluetoothDevice b:sb){
            Log.i("BTU","PairedDevice:"+b.getName()+":"+b.getAddress());
        }
        //获取扫描到的设备
        btu.discoveringDevices(12000, new BlueToothUtils.discoveringDevicesListener() {
            @Override
            public void getDiscouveredDevice(BluetoothDevice bluetoothDevice) {
                Log.i("BTU","DiscoveringDevices:"+bluetoothDevice.getName()+":"+bluetoothDevice.getAddress());
                if(bluetoothDevice.getName().equals("BT05")){
                    //连接指定的蓝牙设备和读写口
                    btu.bleConnect(bluetoothDevice,"0000ffe1-0000-1000-8000-00805f9b34fb");
                }
            }
        });

        //使当前手机可以被其他设备扫描到,可见时间120秒
        btu.enablingDiscoverability(120);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        btu.release();//关闭蓝牙和注销广播
    }
}
