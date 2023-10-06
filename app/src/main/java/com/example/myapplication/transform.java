package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

public class transform extends AppCompatActivity {
    private IEditText edit1;
    private IEditText edit2;
    private IEditText edit3;
    private IKeyboardView keyboardView;
    private String m;
    private String n;
    private Integer m1;
    private Integer n1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);

        keyboardView = findViewById(R.id.keyboardview);

        //默认绑定一个
        keyboardView.setEditText(edit1);
        edit1.setmIKeyboardView(keyboardView);
      // edit2.setmIKeyboardView(keyboardView);
        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
             //s.replace(0,s.length(),String.valueOf(Integer.parseInt(s.toString(),2)));
           m=s.toString();
           if(m.equals("")){
               edit2.setText("");
               edit3.setText("");
               return;
           }
           m1=Integer.parseInt(m);
           m=Integer.toBinaryString(m1);
           n=Integer.toHexString(m1);
           edit2.setText(m);
           edit3.setText(n);
            }
        });


    }

}