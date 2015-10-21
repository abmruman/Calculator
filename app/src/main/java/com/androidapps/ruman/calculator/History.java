package com.androidapps.ruman.calculator;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by A B M Ruman on 10/21/15 for Project: Calculator.
 */
public class History {
    private final String historyFileName = "history";
    Context context;

    public History(Context context) {
        this.context = context;
    }

    public boolean writeHistory(String message) {
        try {
            message += readHistory();
            FileOutputStream fileOutputStream = context.openFileOutput(historyFileName, Context.MODE_PRIVATE);
            fileOutputStream.write(message.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String readHistory() {
        return readHistory(false);
    }

    public String readHistory(boolean withBulletPoint) {
        String message = "";
        String bull = (withBulletPoint) ? "\u2022 " : "";
        try {
            FileInputStream fileInputStream = context.openFileInput(historyFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((message = bufferedReader.readLine()) != null) {
                stringBuilder.append(bull).append(message).append("\n");
            }
            message = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
