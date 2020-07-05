package com.omar.countingdevicebybluetooth;

import androidx.appcompat.app.AppCompatActivity;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.TextView;

import java.util.Set;




public class MainActivity extends AppCompatActivity {
    public  BluetoothAdapter bluetoothAdapter;
    private final static int REQUEST_ENABLE_BT = 1;
    public static TextView textView ,warningText;
    public static  int i = 0;
       public static String s = "";
       public static String s2="";
    Handler handler;
    Runnable run;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        warningText= (TextView) findViewById(R.id.warning);
        if(!isBluetoothEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent,REQUEST_ENABLE_BT);
        }


        handler=new Handler();
  run =new Runnable() {
     @Override
     public void run() {
         loop();
     }
 };
 handler.postDelayed(run,1000);


    }

   public void loop(){
        run();
        handler.postDelayed(run,1000);
    }

public void run(){



     bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();

    if (pairedDevices.size() > 0) {
        //There are paired devices. Get the name and address of each paired device.
        for (BluetoothDevice device : pairedDevices) {
            String deviceName = device.getName();
            String deviceHardwareAddress = device.getAddress(); // MAC address

            MainActivity.s += deviceName + " and the mac addrss is  " + deviceHardwareAddress;


            i++;

        }
    }

     if(i>10){
         textView.setTextColor(Color.parseColor("#FE0313"));
         warningText.setVisibility(View.VISIBLE);
     }
     else {
         textView.setTextColor(Color.parseColor("#FDFEFE"));
         warningText.setVisibility(View.GONE);
     }
    s2+= i;
    textView.setText(s2);
    s2="";
    i=0;
}

    public boolean isBluetoothEnabled()
    {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return mBluetoothAdapter.isEnabled();

    }
}







