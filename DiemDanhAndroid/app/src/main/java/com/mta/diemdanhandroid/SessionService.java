package com.mta.diemdanhandroid;

import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.mta.diemdanhandroid.activity.LoginActivity;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class SessionService extends Service {
    private long mSessionTime = 0;
    private static long TIME_SESSION = 600000;

    public SessionService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mSessionTime = System.currentTimeMillis() + TIME_SESSION;
        Log.d("HuyNQn", "onCreate: "+mSessionTime);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mCountDownTimer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    CountDownTimer mCountDownTimer = new CountDownTimer(1000,TIME_SESSION) {
        @Override
        public void onTick(long millisUntilFinished) {
            long currentTime = System.currentTimeMillis();
            if(currentTime >= mSessionTime){
                onFinish();
            }
        }

        @Override
        public void onFinish() {
            onDestroy();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(SessionService.this, "Phiên làm việc của bạn đã hết hạn", Toast.LENGTH_SHORT).show();
        Log.d("HuyNQn", "onDestroy: "+System.currentTimeMillis());
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}