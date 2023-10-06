package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.myapplication.util.ViewUtil;

public class edit_text extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        EditText et_phone = findViewById(R.id.et_phone);
        EditText et_password = findViewById(R.id.et_password);
        et_phone.addTextChangedListener(new HideTextWatcher(et_phone, 11));
        et_password.addTextChangedListener(new HideTextWatcher(et_password, 6));
    }
//定义一个编辑框监听器，在输入文本达到指定长度时自动隐藏输入法
    private class HideTextWatcher implements TextWatcher {
        //声明一个编辑框对象
        private EditText mview;
        //声明一个最大长度变量
        private int mMaxLength;
        public HideTextWatcher(EditText v, int i) {
            this.mview=v;
            this.mMaxLength=i;

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
         //获得已输入的文本字符串
            String str = s.toString();
         //输入文本达到11位（如手机号码），或者密码达到6位时，关闭输入法
            if(str.length()==mMaxLength){
                //隐藏输入法键盘
                ViewUtil.hideOneInoutMethod(edit_text.this,mview);
        }
    }
}}