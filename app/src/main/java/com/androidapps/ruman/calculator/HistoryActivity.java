package com.androidapps.ruman.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by A B M Ruman on 19/10/2015 for Project: Calculator.
 */
public class HistoryActivity extends Activity {
    private final String histroyFile = "history";
    public TextView textViewHistory;
    History history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        textViewHistory = (TextView) findViewById(R.id.textViewHistory);
    }

    @Override
    protected void onStart() {
        super.onStart();
        history = new History(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        textViewHistory.setText(history.readHistory(true));
    }

    public void clickBack(View view) {
        finish();
    }

}
