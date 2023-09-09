package com.example.bluetoothwithcar;


import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter myBlue;
    private Set<BluetoothDevice> pairedDevice;
    Button bluetoothbuton,cihazlar;
    ListView pairedList;
    public static  String adres="device_Adress";
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBlue=BluetoothAdapter.getDefaultAdapter();
        bluetoothbuton= findViewById(R.id.bluetooth);
        cihazlar= findViewById(R.id.cihazlar);
        pairedList = findViewById(R.id.view);
    }
    public void Blutoothbutonum(View view) {
        bluetoothbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myBlue == null) {
                    Toast.makeText(getApplicationContext(), "cihaz yok", Toast.LENGTH_SHORT).show();
                }
                if (!myBlue.isEnabled()) {
                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivity(enableIntent);
                }
                if (myBlue.isEnabled()) {
                    myBlue.disable();
                }
            }
        });

    }


    public void cihazlar (View view)
    {
        pairedDevice = myBlue.getBondedDevices();

        ArrayList list = new ArrayList();

        if(pairedDevice.size()>0){

            for (BluetoothDevice bt : pairedDevice){

                list.add(bt.getName()+"\n" +bt.getAddress());

            }
        }
        else{
            Toast.makeText(getApplicationContext(),"eşleşmis cihaz yok ", Toast.LENGTH_SHORT).show();
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        pairedList.setAdapter(adapter);
        pairedList.setOnItemClickListener(seleckDevice);
    }
    public AdapterView.OnItemClickListener seleckDevice =new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String info = ((TextView) view ).getText().toString();
            String adress = info.substring(info.length()-17);

            Intent intent = new Intent(MainActivity.this,MainActivity2.class);
            intent.putExtra(adres,adress);
            startActivity(intent);
        }
    };
}
