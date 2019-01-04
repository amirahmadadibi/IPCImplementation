package projects.com.amirahmadadibi.ipcimplementation.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MyBoundService extends Service {
    public static final String TAG = "service";
    //I'm simply create and return binder for  connection
    //creating messenger
    Messenger messenger = new Messenger(new IncomingHandler());

    public MyBoundService() {
        Log.i(TAG, "MyBoundService: Service Class Loaded");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: BoundService");
        //bridge a communication gap between components
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: BoundService");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: BoundService");
    }
    //just for test accessibility of BoundService
    public int addNumbers(int value1, int value2) {
        return value1 + value2;
    }

    public class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //FROM DOC * User-defined message code so that the recipient can identify
            switch (msg.what) {
                case 43:
                    Bundle bundle = msg.getData();
                    int num1 = bundle.getInt("numOne");
                    int num2 = bundle.getInt("numTwo");
                    int result = addNumbers(num1, num2);
                    Toast.makeText(MyBoundService.this, "result" + result, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
