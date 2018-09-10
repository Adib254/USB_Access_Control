package com.example.rifat.readfromapi;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rifat.readfromapi.service.notificationService;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click=(Button)findViewById(R.id.click);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service=new Intent(MainActivity.this,notificationService.class);
                startService(service);

                Intent myIntent = new Intent(MainActivity.this, DataActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
