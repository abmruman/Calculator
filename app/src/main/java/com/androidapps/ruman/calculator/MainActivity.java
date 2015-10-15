package com.androidapps.ruman.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textViewMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewMain = (TextView) findViewById(R.id.textViewMain);
    }

    public void History(View v) {
        startActivity(new Intent("com.androidapps.ruman.calculator.HISTORY_ACTIVITY"));
    }


    public void Click  (View view){
        Button btn = (Button) view;

        String strMain = textViewMain.getText().toString();
        String txtBtn = btn.getText().toString();
        switch (txtBtn){
            case "=":
                textViewMain.setText("0");
                break;
            case "DEL":
                if(strMain.length()>0)
                    textViewMain.setText(strMain.toCharArray(), 0, strMain.length()-1);
                else
                    textViewMain.setText("0");
                break;
            case "\u03C0":
                txtBtn = getString(R.string.btn_pi);
            default:
                if(strMain.equals("0"))
                    textViewMain.setText(txtBtn);
                else
                    textViewMain.append(txtBtn);
                break;
        }
    }
}
