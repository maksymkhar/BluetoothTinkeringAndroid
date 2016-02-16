package com.dam2.max.bluetoothtinkeringandroid;

import android.bluetooth.BluetoothAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        isBluetoothAvailable();


    }

    /**
     * Check if bluetooth is available
     * @return Bluetooth available
     */
    private boolean isBluetoothAvailable()
    {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter != null) { return true; }
        else {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
