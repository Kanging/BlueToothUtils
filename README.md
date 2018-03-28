# BlueToothUtils

# Usage

## dependency

>This tool has only one file [BlueToothUtils.java](https://github.com/fanrunqi/BlueToothUtils/tree/master/app/src/main/java/cn/bobojing/bluetoothutils/btUtils),copy it!

## manifests

>Add the following permissions

```
    <!-- 蓝牙权限 -->
    <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    <uses-permission android:name="android.permission.BLUETOOTH" />
    <!-- Android 5.0以上蓝牙好需要位置权限 -->
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="andriod.permission.ACCESS_FINE_LOCATION" />
```

## Initialize in your application

```
mBtu = new BlueToothUtils(this);
```

## In your Bluetooth settings Activtiy

>Get BlueToothUtils instance
```
btu=MyApplication.getmBtu();
```
>Turn on Bluetooth

```
//打开蓝牙
btu.enableBluetooth(new BlueToothUtils.enableBluetoothListener() {
  @Override
   public void enableSuccess() {
     //打开蓝牙后才能进行后续操作(这需要一段时间)
      }
   });
```
>Get paired devices

```
btu.queryingPairedDevices();
```
>Get the scanned device

```
  //获取扫描到的设备
        btu.discoveringDevices(12000, new BlueToothUtils.discoveringDevicesListener() {
            @Override
            public void getDiscouveredDevice(BluetoothDevice bluetoothDevice) {
                Log.i("BTU","DiscoveringDevices:"+bluetoothDevice.getName()+":"+bluetoothDevice.getAddress());
            }
        });
```
>Make current blue can be scanned by other devices

```
//使当前手机可以被其他设备扫描到,可见时间120秒
 btu.enablingDiscoverability(120);
```
>Connect the specified Bluetooth device and reader port

```
 //连接指定的蓝牙设备和读写口(保存连接状态，在应用其他地方读写)
  btu.bleConnect(bluetoothDevice,"0000ffe1-0000-1000-8000-00805f9b34fb");
```

