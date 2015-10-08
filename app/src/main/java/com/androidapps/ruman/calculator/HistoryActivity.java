package com.androidapps.ruman.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ruman on 10/8/15.
 */
public class HistoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    public void clickBack(View v) {
        finish();
    }
}
