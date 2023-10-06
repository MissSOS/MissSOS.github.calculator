package com.example.myapplication;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class calculator extends AppCompatActivity implements View.OnClickListener {

    private int i = 0;
    private TextView tv_result;
    private TextView tv_follow;
    private String firstNum = "";
    private String operator = "";
    private String secondNum = "";
    private String result = "";
    private String showText = "";
    private ClipData mClipData;   //剪切板Data对象
    private ClipboardManager mClipboardManager;//剪切板管理工具类
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        //从布局文件中获取名叫tv_result的文本视图
        tv_result = findViewById(R.id.tv_result);
        tv_follow = findViewById(R.id.tv_follow);
        mClipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        //下面给每个按钮都注册了点击监听器
        findViewById(R.id.btn_x).setOnClickListener(this);
        findViewById(R.id.copyBtn).setOnClickListener(this);
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
        if (v.getId() == R.id.copyBtn) {
            String mData = tv_follow.getText().toString();
            //创建一个新的文本clip对象
            mClipData = ClipData.newPlainText("Simple text", mData);
            //把clip对象放在剪贴板中
            mClipboardManager.setPrimaryClip(mClipData);
            Toast.makeText(getApplicationContext(), "复制成功！",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (v.getId() == R.id.btn_x) {
            if (!operator.equals("")) {
                if (!secondNum.equals("")) {
                    secondNum = secondNum.substring(0, secondNum.length() - 1);
                    refresh(firstNum + operator + secondNum);
                    if (!secondNum.equals("")) {
                        tv_follow.setText(secondNum);
                    } else {
                        tv_follow.setText(operator);
                    }

                } else {
                    operator = "";
                    refresh(firstNum);
                    tv_follow.setText(firstNum);
                }
            } else {
                firstNum = firstNum.substring(0, firstNum.length() - 1);
                if (!firstNum.equals("")) {
                    refresh(firstNum);
                    tv_follow.setText(firstNum);
                } else {
                    clear();
                }

            }
            return;
        }
        String inputText;
        if (v.getId() == R.id.btn_sqrt) {
            inputText = "√";
            tv_follow.setText(inputText);
        } else {
            //除了开根号之外其他按钮
            inputText = ((TextView) v).getText().toString();
            // tv_follow.setText(inputText);
        }
        switch (v.getId()) {
            case R.id.btn_clear:
                clear();
                num=0;
                break;
            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                operator = inputText;//运算符
                tv_follow.setText(operator);
                refresh(showText + inputText);
                num=0;
                break;
            case R.id.btn_equal:
                DecimalFormat df = new DecimalFormat("#.00000000");
                double calculate_result = Double.parseDouble(df.format(calculateFour()));
                refreshOperator(String.valueOf(calculate_result));
                refresh(showText + "=" + result);
                tv_follow.setText(String.valueOf(calculate_result));
                num=0;
                break;
            case R.id.btn_cancel:
                tv_follow.setText(inputText);
                if (operator.equals("")) {
                    if (Double.parseDouble(firstNum) > 0) {
                        refresh("-" + firstNum);
                    } else {
                        refresh(firstNum.substring(1, firstNum.length()));
                    }
                    firstNum = String.valueOf(-1 * Double.parseDouble(firstNum));
                } else {
                    if (Double.parseDouble(secondNum) > 0) {
                        showText = showText.substring(0, showText.length() - secondNum.length());
                        refresh(showText + "-" + secondNum);
                    } else {
                        showText = showText.substring(0, showText.length() - secondNum.length());
                        refresh(showText + secondNum.substring(1, secondNum.length() - 1));
                    }
                    secondNum = String.valueOf(-1 * Double.parseDouble(secondNum));
                }

                break;
            case R.id.btn_sqrt:
                double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
                refreshOperator(String.valueOf(sqrt_result));
                refresh(showText + "√=" + result);
                tv_follow.setText(result);
                num=0;
                break;
            case R.id.btn_reciprocal:
                double reci_result = 1.0 / (Double.parseDouble(firstNum));
                refreshOperator(String.valueOf(reci_result));
                refresh(showText + "/=" + result);
                tv_follow.setText(result);
                num=0;
                break;
//                点击了其他按钮，包括数字和小数点
            case R.id.btn_dot:
                num++;
            default:
                if(num>1&&inputText.equals(".")){
                    break;
                }
                if (result.length() > 0 && operator.equals("")) {
                    clear();
                }
                if (operator.equals("")) {
                    firstNum = firstNum + inputText;
                    tv_follow.setText(firstNum);
                } else {
                    secondNum = secondNum + inputText;
                    tv_follow.setText(secondNum);
                }


                if (showText.equals("0") && !inputText.equals(".")) {
            refresh(inputText);

               } else {
            refresh(showText + inputText);
        }
    }}



    private double calculateFour() {
        switch (operator) {
            case "＋":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "－":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "÷":
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);

        }
        return 0;
    }

    private void clear() {
        tv_follow.setText("0");
        refreshOperator("");
        refresh("0");

    }

    //刷新运算结果
    private void refreshOperator(String new_result) {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }


    private void refresh(String text) {
        showText = text;
        tv_result.setText(showText);
    }
}