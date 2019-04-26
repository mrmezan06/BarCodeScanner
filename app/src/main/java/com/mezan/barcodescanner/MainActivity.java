package com.mezan.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import androidx.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    CodeScanner CScanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CodeScannerView csv=(CodeScannerView)findViewById(R.id.barScanner);
        CScanner = new CodeScanner(this,csv);
        CScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String res = result.getText().toString();

                        Toast.makeText(MainActivity.this,result.getText(),Toast.LENGTH_LONG).show();
                        Intent it = new Intent(MainActivity.this, com.mezan.barcodescanner.Result.class);
                        it.putExtra("res",res);
                        startActivity(it);
                    }
                });
            }
        });
        csv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CScanner.startPreview();
    }

    @Override
    protected void onPause() {
        CScanner.releaseResources();
        super.onPause();
    }
}
