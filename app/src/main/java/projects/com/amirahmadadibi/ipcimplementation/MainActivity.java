package projects.com.amirahmadadibi.ipcimplementation;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import projects.com.amirahmadadibi.ipcimplementation.services.MyBoundService;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "service";
    private MyBoundService myBoundService;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBoundService.ServiceBinder serviceBinder = (MyBoundService.ServiceBinder) service;
            myBoundService = serviceBinder.getService();
            Log.i(TAG, "onServiceConnected: initialization complete");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (myBoundService != null) {
                //cleaning up
                myBoundService = null;
            }
            Log.i(TAG, "onServiceDisconnected: reset service filed");
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        //binding process
        Intent boundServiceIntent = new Intent(this, MyBoundService.class);
        //Context.BIND_AUTO_CREATE -> automatic creation of service after binding
        bindService(boundServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void useBSNormaly(View view) {
        Log.i(TAG, "useBSNormaly: " + myBoundService.doSomeWork());
    }


    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        Log.i(TAG, "onStop: unbind BoundService");
    }
}
