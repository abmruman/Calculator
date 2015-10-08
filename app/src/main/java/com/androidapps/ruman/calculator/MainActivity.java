package com.androidapps.ruman.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickNext(View v) {
        startActivity(new Intent("com.androidapps.ruman.calculator.HISTORY_ACTIVIY"));
    }
}
