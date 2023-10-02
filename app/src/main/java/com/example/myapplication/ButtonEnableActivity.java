package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.util.Dateutil;

public class ButtonEnableActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_test;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_enable);
        Button btn_enable = findViewById(R.id.btn_enable);
        Button btn_unable=findViewById(R.id.btn_unable);
        btn_test = findViewById(R.id.btn_test);
        textview = findViewById(R.id.textview);
        btn_enable.setOnClickListener(this);
        btn_unable.setOnClickListener(this);
        btn_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_enable:
                btn_test.setEnabled(true);
                btn_test.setTextColor(Color.BLACK);
                break;
            case R.id.btn_unable:
                btn_test.setEnabled(false);
                btn_test.setTextColor(Color.GRAY);
                break;
            case R.id.btn_test:
                String dec = String.format("%s 您点击了按钮:%s", Dateutil.getNowTime(),btn_test.getText());
                textview.setText(dec);
                break;
        }
    }
}