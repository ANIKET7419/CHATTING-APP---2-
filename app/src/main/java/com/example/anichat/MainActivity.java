package com.example.anichat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button enter=null;
EditText ip=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enter=findViewById(R.id.enter);
        ip=findViewById(R.id.ipaddress);
        enter.setOnClickListener(this);
    }
    @Override
    public void onClick(View view)
    {
        if("".equals(ip.getText().toString()))
        {
            ip.setHint("Please Enter Ip Address");
        }
        else
        {
            String ipaddress =ip.getText().toString();
            Intent i1=new Intent(getApplicationContext(),AniChat2.class);
            i1.putExtra("key",ipaddress);
            ip.setText("");
            startActivity(i1);
        }
    }
}
