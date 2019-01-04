package projects.com.amirahmadadibi.ipcimplementation.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBoundService extends Service {
    public static final String TAG = "service";
    //I'm simply create and return binder for  connection
    private final Binder mBinder = new ServiceBinder();

    public MyBoundService() {
        Log.i(TAG, "MyBoundService: Service Class Loaded");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: BoundService");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: BoundService");
        return super.onUnbind(intent);
    }

    public class ServiceBinder extends Binder {
        public MyBoundService getService() {
            return MyBoundService.this;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: BoundService");
    }


    //just for test accessibility of BoundService
    public String doSomeWork() {
        return "i did!";
    }
}
