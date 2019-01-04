package projects.com.amirahmadadibi.ipcimplementation;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import projects.com.amirahmadadibi.ipcimplementation.model.Car;
import projects.com.amirahmadadibi.ipcimplementation.services.MyBoundService;

public class MassengerActivity extends AppCompatActivity {
    private TextView mTxtResult;
    private EditText mEdtNumber2;
    private EditText mEdtNumber1;
    private Messenger mMessenger;
    private boolean isBinded = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            isBinded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            if (mMessenger != null) {
                mMessenger = null;
            }
            isBinded = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_massenger);
        mTxtResult = findViewById(R.id.txt_result);
        mEdtNumber1 = findViewById(R.id.edt_num1);
        mEdtNumber2 = findViewById(R.id.edt_num_2);

    }

    public void performAddOperation(View view) {
        int num1 = Integer.valueOf(mEdtNumber1.getText().toString());
        int num2 = Integer.valueOf(mEdtNumber2.getText().toString());
//        mTxtResult.setText(myBoundService.addNumbers(num1,num2) + "");
        Message messageToService = Message.obtain(null, 43);
        Bundle bundle = new Bundle();
        bundle.putInt("numOne", num1);
        bundle.putInt("numTwo", num2);
        messageToService.setData(bundle);
        try {
            mMessenger.send(messageToService);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onBindService(View view) {
        Intent intent = new Intent(this, MyBoundService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void onUnBindService(View view) {
        //it's not best practise
        unbindService(serviceConnection);
    }

    public void sendObjectToBoundService(View view) {
        Car car = new Car("red",1200);
        Message message = Message.obtain(null,44);
        message.setData();
        mMessenger.send(car);

    }
}
