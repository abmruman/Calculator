package com.androidapps.ruman.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewMain, textViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewMain = (TextView) findViewById(R.id.textViewMain);
        textViewResult = (TextView) findViewById(R.id.textViewResult);


    }

    public void History(View v) {
        startActivity(new Intent("com.androidapps.ruman.calculator.HISTORY_ACTIVITY"));
    }


    public void Click  (View view){
        Button btn = (Button) view;

        String strMain = textViewMain.getText().toString();
        String txtBtn = btn.getText().toString();
        final HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.scrollMain);

        scrollView.postDelayed(new Runnable() {
            public void run() {
                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);

        switch (txtBtn){
            case "=":
                textViewMain.setText("0");
                textViewResult.setText("Ans");
                break;
            case "DEL":

                if (strMain.length() > 1) {
                    textViewMain.setText(strMain.toCharArray(), 0, strMain.length() - 1);
                } else {
                    textViewMain.setText("0");
                }
                break;
            case ".":
                char[] arr = strMain.toCharArray();
                boolean hasDot = false;
                for (int i = strMain.length() - 1; i >= 0; i--) {
                    if (arr[i] == '+' || arr[i] == '-' || arr[i] == 'x' || arr[i] == '÷') {
                        break;
                    } else if (arr[i] == '.') {
                        hasDot = true;
                        break;
                    }
                }
                if (!hasDot)
                    textViewMain.append(".");
                break;
            case "π":
                txtBtn = getString(R.string.btn_pi);
            case "+":
            case "-":
            case "x":
            case "÷":
                String text = textViewMain.getText().toString();
                if (text.endsWith("\u00F7") || text.endsWith("+") || text.endsWith("-") || text.endsWith("x") || text.endsWith("π"))
                    break;

            default:
                if (strMain.equals("0")) {
                    textViewMain.setText(txtBtn);
                } else {

                    textViewMain.append(txtBtn);
                }
                break;
        }
    }
}
