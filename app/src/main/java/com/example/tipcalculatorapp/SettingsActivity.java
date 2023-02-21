package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {
    TextView setTipText;
    EditText defaultPercentage;
    CheckBox splitButton;
    TextView numOfPartyText;
    EditText defaultParty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        splitButton = findViewById(R.id.splitButton);
        setTipText = findViewById(R.id.setTipText);
        defaultPercentage = findViewById(R.id.defaultPercentage);
        numOfPartyText = findViewById(R.id.numOfPartyText);
        defaultPercentage = findViewById(R.id.defaultPercentage);
        defaultParty = findViewById(R.id.defaultParty);


    }

    @Override
    public void onPause() {
        super.onPause();
        updateSharedPreferences();
    }

    public void updateSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int percentInt = Integer.parseInt(defaultPercentage.getText().toString());
        editor.putInt("defaultPercentage", percentInt);
        int numOfPpl = Integer.parseInt(defaultParty.getText().toString());
        editor.putInt("defaultParty", numOfPpl);
        boolean splitBill = splitButton.isChecked();
        editor.putBoolean("splitButton",splitBill);
        editor.commit();
    }
    private void updatetip() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        defaultPercentage.setText(sp.getInt("defaultPercentage", 15)+"");
    }

    private void updateParty() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        defaultParty.setText(sp.getInt("defaultParty", 4)+"");
    }

    private void updateSplit() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        boolean splitBill = sp.getBoolean("splitButton",true);
        if(splitBill) {
            splitButton.setChecked(true);
        } else splitButton.setChecked(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        updatetip();
        updateParty();
        updateSplit();
    }
}