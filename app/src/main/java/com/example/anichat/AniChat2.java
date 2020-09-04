package com.example.anichat;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AniChat2 extends AppCompatActivity implements View.OnClickListener{
    TextView t1=null;
    Intent i2=null;
    String ipaddress=null;
    EditText send =null;
    Button sendbutton=null;
    EditText received=null;
    DataOutputStream output=null;
    DataInputStream input=null;
    Handler handle=null;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ani_chat2);
        t1=findViewById(R.id.waiting);
        send=findViewById(R.id.message);
        sendbutton=findViewById(R.id.send);
        received=findViewById(R.id.received);
        i2=getIntent();
        handle=new Handler();
        ipaddress=i2.getStringExtra("key");
        sendbutton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view)
    {
        thread3 thread =new thread3();
        thread.start();
    }
    @Override
    public void onStart()
    {
        super.onStart();
        thread t=new thread();
        t.start();

    }
    class thread3 extends Thread
    {
        public void run()
        {
            try {

                output.writeUTF(send.getText().toString());
                send.setText("");
            }
            catch(Exception e)
            {
                send.setText(e.toString());
            }
        }
    }

    class thread extends Thread
    {
        @Override
        public void run()
        {
            t1.setText("Waiting For Connection");
            try {

               Socket s1= new Socket(ipaddress, 2001);
                t1.setText("Connection has been Established,,,,Waiting..");
                input=new DataInputStream(s1.getInputStream());
                output=new DataOutputStream(s1.getOutputStream());
                Thread.sleep(1000);
                handle.post(new Runnable() {
                    @Override
                    public void run() {
                        t1.setVisibility(View.INVISIBLE);
                        sendbutton.setVisibility(View.VISIBLE);
                        sendbutton.setClickable(true);
                        received.setVisibility(View.VISIBLE);
                        send.setVisibility(View.VISIBLE);
                    }
                });
                thread2 thread =new thread2();
                thread.start();
            }
            catch(Exception e)
            {
                t1.setText(e.toString());
            }



        }
    }
     class thread2 extends Thread
    {
     public void run()
     {
         try {


             while (true) {
                 received.append(" "+input.readUTF());
             }
         }
         catch(Exception e)
         {
             received.append("  There is some error");
         }
     }
    }
}

