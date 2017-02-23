package com.zbar.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.zbar.R;

import java.io.UnsupportedEncodingException;

public class MainActivity extends Activity {
    private ImageButton btn_home_scan;
    private EditText et_mailno_search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_mailno_search = (EditText) this.findViewById(R.id.et_mailno_search);
        btn_home_scan = (ImageButton) this.findViewById(R.id.btn_home_scan);
        btn_home_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_mailno_search.setText("");
                Intent intent = new Intent(MainActivity.this, CaptureActivity2.class);
                startActivityForResult(intent, 0);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            String gbkStr = "";
        try {
            gbkStr = new String(scanResult.getBytes("ISO-8859-1"), "GB2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
            et_mailno_search.setText(gbkStr);
            et_mailno_search.setSelection(et_mailno_search.length());
        }
    }
}