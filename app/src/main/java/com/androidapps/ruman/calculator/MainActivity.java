package com.androidapps.ruman.calculator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 * Created by A B M Ruman on 19/10/2015 for Project: Calculator.
 */

public class MainActivity extends Activity {
    public final String HISTORY_SHARED_PREF = "HistoryPref";
    EditText textViewMain, textViewResult;
    Calculator calculator;
    History history;
    private SharedPreferences historySharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        longClickDel();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        calculator = new Calculator();
        history = new History(this);
        textViewMain = (EditText) findViewById(R.id.textViewMain);
        textViewResult = (EditText) findViewById(R.id.textViewResult);
        if(textViewResult.getText().toString() == "")
            textViewResult.setText("0");
        if(textViewMain.getText().toString() == "")
            textViewMain.setText("0");

        historySharedPref = getSharedPreferences(HISTORY_SHARED_PREF, MODE_PRIVATE);
    }

    public void History(View view) {
        startActivity(new Intent("com.androidapps.ruman.calculator.HISTORY_ACTIVITY"));

    }

    public void longClickDel() {
        Button btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                textViewMain.setText("0");
                textViewResult.setText("0");
                return true;
            }
        });
    }
    public void Click(View view) {
        Button btn = (Button) view;

        String strMain = textViewMain.getText().toString();
        String strResult = textViewResult.getText().toString();
        String txtBtn = btn.getText().toString();
        final HorizontalScrollView scrollView = (HorizontalScrollView) findViewById(R.id.scrollMain);

        scrollView.postDelayed(new Runnable() {
            public void run() {
                scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
            }
        }, 100L);

        SharedPreferences.Editor editor = historySharedPref.edit();
        String strHistory = historySharedPref.getString("History", "");
        switch (txtBtn) {
            case "=":
                textViewMain.setText(fixExpression(textViewMain.getText().toString()));
                if (!textViewResult.toString().equals("") || !textViewResult.toString().equals("Error!")) {
                    try {
                        if (calculateResult()) {
                            history.writeHistory(textViewMain.getText() + " = " + textViewResult.getText() + "\n");
                            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                            textViewMain.setText(textViewResult.getText().toString());
                            textViewResult.setText("0");
                        } else {
                            textViewResult.setText("Error!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                break;
            case "DEL":
                if (strMain.length() > 1) {
                    if(textViewResult.getText().toString() != "Error!")
                        textViewMain.setText(strMain.toCharArray(), 0, strMain.length() - 1);
                    else {
                        textViewMain.setText("0");
                        textViewResult.setText("0");
                    }
                } else {
                    textViewMain.setText("0");
                }
                calculateResult();
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
            case "M+":

                if (strHistory.equals("")) {
                    Toast.makeText(MainActivity.this, "Memory is empty.", Toast.LENGTH_SHORT).show();
                } else {
                    if (textViewMain.getText().equals("0"))
                        textViewMain.setText(strHistory);
                    else
                        textViewMain.append(strHistory);
                    Toast.makeText(MainActivity.this, "Memory has " + strHistory, Toast.LENGTH_SHORT).show();
                }
                calculateResult();
                break;
            case "MR":
                String result = textViewResult.getText().toString();
                if (!result.equals("") && !result.equals("Error!")) {
                    editor.putString("History", result);
                    editor.commit();
                    Toast.makeText(MainActivity.this, "Memory is set to: " + strResult, Toast.LENGTH_SHORT).show();
                }

                break;
            case "MC":
                editor.putString("History", "");
                editor.commit();
                Toast.makeText(MainActivity.this, "Memory is cleared.", Toast.LENGTH_SHORT).show();
                break;
//            case "π":
//                txtBtn = getString(R.string.btn_pi);
            case "+":
            case "-":
            case "x":
            case "÷":
                //String text = textViewMain.getText().toString();
                if (strMain.endsWith("÷")
                        || strMain.endsWith("+")
                        || strMain.endsWith("-")
                        || strMain.endsWith("x")
                        || strMain.endsWith("π")
                        || strMain.equals("0")
                        || strMain.equals(".")
                        || strMain.equals("(")) {

                    break;
                }
            default:
                if (strMain.equals("0")) {
                    textViewMain.setText(txtBtn);
                } else if (txtBtn.matches("[0-9]") && textViewResult.getText().equals("")) {
                    textViewMain.setText(txtBtn);
                } else {

                    textViewMain.append(txtBtn);
                }
                calculateResult();
                break;
        }

    }

    public boolean calculateResult() {
        String strMain = fixExpression(textViewMain.getText().toString());
        Double result;
        DecimalFormat decimalFormat = new DecimalFormat("#.######");

        try {
            result = calculator.calculate(strMain);
            textViewResult.setText(decimalFormat.format(result)); //String.format("%.6f%n",result)
            return true;
        } catch (Exception e) {
            //textViewResult.setText("Error!");
            e.printStackTrace();
        }
        return false;
    }

    public boolean invalidExpression(String expression){
        return expression.endsWith("+") || expression.endsWith("-") || expression.endsWith("*") || expression.endsWith("/") || expression.endsWith("(");

    }
    public String fixExpression(String expression){
        if(invalidExpression(expression)){
            return expression.substring(0, expression.length() - 1);
        }
        return expression;
    }
}
