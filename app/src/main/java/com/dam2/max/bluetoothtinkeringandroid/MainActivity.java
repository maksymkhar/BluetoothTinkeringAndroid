package com.dam2.max.bluetoothtinkeringandroid;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final String TAG = "BTA MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize BluetoothAdapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (!isBluetoothAvailable()) { return; }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Call enable bluetooth system Intent
        checkBluetoothEnabled();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case REQUEST_ENABLE_BT:
                enableBluetoothResult(resultCode);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menu_discover:
                callDiscoverableIntent();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Check if bluetooth is available
     * @return Bluetooth available
     */
    private boolean isBluetoothAvailable()
    {
        if (mBluetoothAdapter != null) { return true; }
        else {
            Toast.makeText(this, getString(R.string.bluetooth_not_available), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * Call enable bluetooth system Intent
     */
    private void checkBluetoothEnabled()
    {
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    /**
     * Enable bluetooth on activity result actions
     * @param resultCode code of petition result
     */
    private void enableBluetoothResult(int resultCode)
    {
        // When the request to enable Bluetooth returns
        if (resultCode == Activity.RESULT_OK) {
            // Bluetooth is now enabled, so set up a chat session
            //setupChat();
            // TODO
            Toast.makeText(this, getString(R.string.bluetooth_enabled), Toast.LENGTH_SHORT).show();
        } else {
            // User did not enable Bluetooth or an error occurred
            Log.d(TAG, "BT not enabled");
            Toast.makeText(this, getString(R.string.bluetooth_not_enabled), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * Starts discoverable bluetooth Intent
     */
    private void callDiscoverableIntent()
    {
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);
    }
}
