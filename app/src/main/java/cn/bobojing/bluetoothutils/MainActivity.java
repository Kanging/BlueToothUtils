package cn.bobojing.bluetoothutils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import cn.bobojing.bluetoothutils.btUtils.BlueToothUtils;


public class MainActivity extends AppCompatActivity {
    BlueToothUtils mBtu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //获取限权
        AndPermission.with(this)
                .permission(Permission.Group.LOCATION).start();
        //打开链接蓝牙界面
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,BlueActivity.class));
            }
        });
        //获取蓝牙实例
        if (null == mBtu) {
            mBtu = MyApplication.getmBtu();
        }
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
                String replyData="";
                try{
                    replyData =new String(data,"GBK");
                }catch (Exception e){
                    e.printStackTrace();
                }
                Log.i("BTU", "replyData:" +replyData);

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果蓝牙以及连接则发送数据
                if(mBtu.blueIsConnected()){
                    mBtu.blueStringWrite("I0491","GBK");
                }else {
                    Toast.makeText(MainActivity.this,"please connect bluetooth before",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
