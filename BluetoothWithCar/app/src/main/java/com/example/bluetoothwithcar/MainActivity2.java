package com.example.bluetoothwithcar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bluetoothwithcar.R;

import java.io.IOException;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {

    String address= null;
    private ProgressDialog progress ;
    BluetoothAdapter myBluetooth =null;

    BluetoothSocket btSocket=null;
    BluetoothDevice remoteDevice ;
    BluetoothServerSocket mmServer ;
    Button ileri,geri,dur,sag,sol;

    private boolean isBtConnected=false;
    static final UUID myUUID =UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        new BTbaglan().execute();
        Intent newIntent=getIntent();
        address = newIntent.getStringExtra(com.example.bluetoothwithcar.MainActivity.adres);
        ileri=findViewById(R.id.ileri);
        geri=findViewById(R.id.geri);
        dur=findViewById(R.id.Dur);
        sag=findViewById(R.id.sag);
        sol=findViewById(R.id.sol);
    }

    public void Dur(View view) {
        dur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket != null) {
                    try {
                        btSocket.getOutputStream().write("3".toString().getBytes());
                    } catch (IOException e) {

                    }
                }

            }
        });


    }





    public void ileri(View view)
    {
        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btSocket!=null){
                    try {
                        btSocket.getOutputStream().write("1".toString().getBytes());
                    }
                    catch (IOException e)
                    {

                    }
                }

            }
        });


    }
    public void geri(View view)
    {
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btSocket!=null){
                    try {
                        btSocket.getOutputStream().write("2".toString().getBytes());
                    }
                    catch (IOException e)
                    {

                    }
                }


            }
        });

    }
    public void sol(View view)
    {
        sol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btSocket!=null){
                    try {
                        btSocket.getOutputStream().write("5".toString().getBytes());
                    }
                    catch (IOException e)
                    {

                    }
                }


            }
        });

    }
    public void sag(View view)
    {
        sag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btSocket!=null){
                    try {
                        btSocket.getOutputStream().write("4".toString().getBytes());
                    }
                    catch (IOException e)
                    {

                    }
                }


            }
        });
        new BTbaglan().execute();
    }


    private void Disconnect(){
        if(btSocket!=null){
            try {
                btSocket.close();
            } catch (IOException e){
                // msg("Error");
            }
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Disconnect();
    }

    private class BTbaglan extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(MainActivity2.this, "Baglanıyor...", "Lütfen Bekleyin");
        }


        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice cihaz = myBluetooth.getRemoteDevice(address);
                    btSocket = cihaz.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!ConnectSuccess) {
                // msg("Baglantı Hatası, Lütfen Tekrar Deneyin");
                Toast.makeText(getApplicationContext(),"Bağlantı Hatası Tekrar Deneyin",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                //   msg("Baglantı Basarılı");
                Toast.makeText(getApplicationContext(),"Bağlantı Başarılı",Toast.LENGTH_SHORT).show();

                isBtConnected = true;
            }
            progress.dismiss();
        }

    }
}
