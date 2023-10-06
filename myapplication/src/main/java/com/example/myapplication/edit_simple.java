package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class edit_simple extends AppCompatActivity implements View.OnFocusChangeListener {

    private EditText et_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_simple);
        et_phone = findViewById(R.id.et_phone);
        EditText et_password=findViewById(R.id.et_password);
        et_password.setOnFocusChangeListener(this);

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
           String phone=et_phone.getText().toString();
           //手机号码不足十一位
            if(TextUtils.isEmpty(phone)||phone.length()<11)
            et_phone.requestFocus();
            //弹出一个吐司的提示
            Toast.makeText(this,"请输入11位手机号码",Toast.LENGTH_SHORT).show();

        }
    }
}