package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class calculator extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private String firstNum = "";
    private String operator = "";
    private String secondNum = "";
    private String result = "";
    private String showText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //从布局文件中获取名叫tv_result的文本视图
        tv_result = findViewById(R.id.tv_result);
        //下面给每个按钮都注册了点击监听器
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_reciprocal).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_sqrt).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String inputText;
        if(v.getId()==R.id.btn_sqrt){
            inputText="√";
        }else{
            //除了开根号之外其他按钮
            inputText=((TextView)v).getText().toString();
        }
        switch(v.getId()) {
            case R.id.btn_clear:
                clear();
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                 operator = inputText;//运算符
                refresh(showText+inputText);
                break;
            case R.id.btn_equal:
                double calculate_result  = calculateFour();
                refreshOperator(String.valueOf(calculate_result));
                refresh(showText+"="+result);
                break;
            case R.id.btn_sqrt:
               double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
               refreshOperator(String.valueOf(sqrt_result));
               refresh(showText+"√="+result);
                break;
            case R.id.btn_reciprocal:
                double reci_result = 1.0/(Double.parseDouble(firstNum));
                refreshOperator(String.valueOf(reci_result));
                refresh(showText+"/="+result);
                break;
//                点击了其他按钮，包括数字和小数点
            default:
                if(result.length()>0&&operator.equals("")){
                    clear();
                }
                if (operator.equals("")) {
                    firstNum = firstNum + inputText;
                } else {
                    secondNum = secondNum + inputText;
                }
                if (showText.equals("0") && !inputText.equals(".")) {
                    refresh(inputText);
                }else{
                    refresh(showText+inputText);
                }
                break;
        }}

    private double calculateFour() {
        switch(operator){
            case "＋":
                return Double.parseDouble(firstNum)+Double.parseDouble(secondNum);
            case "－":
                return Double.parseDouble(firstNum)-Double.parseDouble(secondNum);
            case "÷":
                return Double.parseDouble(firstNum)/Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum)*Double.parseDouble(secondNum);

    }    return 0;
    }

    private void clear() {
        refreshOperator("");
        refresh("");

    }
    //刷新运算结果
    private void refreshOperator(String new_result){
        result=new_result;
        firstNum=result;
        secondNum="";
        operator="";
    }


    private void refresh(String text){
        showText = text;
        tv_result.setText(showText);
    }
}