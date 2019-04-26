package com.mezan.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView RES;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RES = (TextView)findViewById(R.id.resTXT);
        Intent intent = getIntent();
        String result = intent.getStringExtra("res");
        RES.setText(result);

    }
}
