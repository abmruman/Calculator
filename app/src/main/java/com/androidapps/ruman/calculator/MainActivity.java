package com.androidapps.ruman.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void History(View v) {
        startActivity(new Intent("com.androidapps.ruman.calculator.HISTORY_ACTIVITY"));
    }


    public void Click  (View v){
        Button btn = (Button) v;
        EditText editTextMain = (EditText) findViewById(R.id.editTextMain);
        String strMain = editTextMain.getText().toString();
        String txtBtn = btn.getText().toString();

    }
}
