package com.mingle.ZiYou.broadandservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;

import com.mingle.myapplication.R;

import java.io.File;

public class SoundService extends Service {
    private MediaPlayer mp;

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.sound);
//        mp = new MediaPlayer();
//        String song = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test.mp3";
//        try {
//            mp.setDataSource(song);
//            mp.prepare();
//            mp.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.release();
        stopSelf();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean playing = intent.getBooleanExtra("playing", false);
        if (playing) {
            mp.start();
        } else {
            mp.pause();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
