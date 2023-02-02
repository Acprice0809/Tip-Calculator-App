package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView purchasePriceText;
    EditText purchaseInputBox;
    TextView tipPercentageText;
    EditText tipPercentageInput;
    SeekBar seekBar;
    TextView seekBarLabel;
    RadioGroup radioGroup;
    RadioButton splitCostButton;
    RadioButton dontSplitCostButton;
    EditText partyNumInput;
    Button convertButton;
    EditText totalCostOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        purchasePriceText = findViewById(R.id.purchasePriceText);
        purchaseInputBox = findViewById(R.id.purchaseInputBox);
        tipPercentageText = findViewById(R.id.tipPercentageText);
        tipPercentageInput = findViewById(R.id.tipPercentageInput);
        seekBar = findViewById(R.id.seekBar);
        seekBarLabel = findViewById(R.id.seekBarLabel);
        radioGroup = findViewById(R.id.radioGroup);
        splitCostButton = findViewById(R.id.splitCostButton);
        dontSplitCostButton = findViewById(R.id.dontSplitCostButton);
        partyNumInput = findViewById(R.id.partyNumInput);
        convertButton = findViewById(R.id.convertButton);
        totalCostOutput = findViewById(R.id.totalCostOutput);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(purchaseInputBox.getText().toString());
                double s = Double.parseDouble(tipPercentageInput.getText().toString());
                double num = Double.parseDouble(partyNumInput.getText().toString());
                if (splitCostButton.isChecked()) {
                    value = (value*(s+1))/num;
                } else if (dontSplitCostButton.isChecked()) {
                    value = value*(s+1);
                }
                totalCostOutput.setText(String.format("%.3f",value));
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                partyNumInput.setText("");
                if (i==R.id.splitCostButton) {
                    partyNumInput.setHint("Enter num of party")
                } else if (i == R.id.dontSplitCostButton) {
                    partyNumInput.setText("N/A");
                }
            }
        });


    }
}