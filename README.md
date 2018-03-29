# BlueToothUtils

# Usage

## dependency

>just copy the package [btUtils](https://github.com/fanrunqi/BlueToothUtils/tree/master/app/src/main/java/cn/bobojing/bluetoothutils/btUtils) in your project.
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

## In your Bluetooth Reading or writing Activtiy

>Get BlueToothUtils instance
```
 //获取蓝牙实例
        if (null == mBtu) {
            mBtu = MyApplication.getmBtu();
        }
```

>add Bluetooth read and write success callback
```
 //蓝牙读写成功后回调
        mBtu.addInteractiveDataListener(new BlueToothUtils.interactiveDataListener() {
            @Override
            public void writeSuccess(String data) {

            }
            @Override
            public void readSuccess(byte[] data) {

            }

            @Override
            public void replyData(byte[] data) {
                //注意这在子线程
            }
        });
```

>Determine if Bluetooth is connected
```
mBtu.blueIsConnected()//返回boolean
```

>Write data (unlimited length)
```
 mBtu.blueStringWrite(String info,String encode);//传入对字符数组的编码格式
```

>Write data (less than 20 bytes)
```
mBtu.bleBytesWrite(byte[] data)
```

>read data
```
mBtu.bleBytesRead()
```
>Turn off Bluetooth
```
mBtu.closeBlueTooth()
```

>Turn off Bluetooth and log off the broadcast
```
mBtu.release()
```
**You can download this project to see the example, There are problems you can directly modify the source code, don't forget to submit，Thank you**.

# License
> Copyright 2018 fanrunqi

> Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  >  http://www.apache.org/licenses/LICENSE-2.0

> Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

