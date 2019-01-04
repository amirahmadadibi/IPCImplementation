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

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void useBSNormaly(View view) {
    }


    @Override
    protected void onStop() {
        super.onStop();
    }
}
