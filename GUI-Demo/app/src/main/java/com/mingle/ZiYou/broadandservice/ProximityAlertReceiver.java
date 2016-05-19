package com.mingle.ZiYou.broadandservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;
//作者：马翔宇
//内容：gps临近警告的广播接收
public class ProximityAlertReceiver extends BroadcastReceiver {
    public ProximityAlertReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // 获取是否进入指定区域
        boolean isEnter = intent.getBooleanExtra(
                LocationManager.KEY_PROXIMITY_ENTERING, false);
        if (isEnter) {
            // 给出提示信息
            Toast.makeText(context, "您已经进入成都市成华区", Toast.LENGTH_LONG).show();
            Log.i("!!!gps!!!!","进入");
        } else {
            // 给出提示信息
            Toast.makeText(context, "您已经离开成都市成华区", Toast.LENGTH_LONG).show();
            Log.i("!!!gps!!!!", "未进入");
        }
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
