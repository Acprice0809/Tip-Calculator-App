package com.example.tipcalculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
    EditText tipAmountOutput;
    EditText amountPerPerson;

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
        tipAmountOutput = findViewById(R.id.tipAmountOutput);
        amountPerPerson = findViewById(R.id.amountPerPerson);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double value = Double.parseDouble(purchaseInputBox.getText().toString());
                double s = Double.parseDouble(tipPercentageInput.getText().toString());
                double v = Double.parseDouble(partyNumInput.getText().toString());
                double u = (s/100)*value;
                double w = (value+u)/v;

                if (splitCostButton.isChecked()) {
                    value = (value+u);
                } else if (dontSplitCostButton.isChecked()) {
                    value = value*((s/100)+1);
                }
                totalCostOutput.setText(String.format("%.2f",value));
                tipAmountOutput.setText(String.format("%.2f",u));
                amountPerPerson.setText(String.format("%.2f",w));
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                partyNumInput.setText("");
                if (i==R.id.splitCostButton) {
                    partyNumInput.setHint("Enter num of party");
                } else if (i == R.id.dontSplitCostButton) {
                    partyNumInput.setText("N/A");
                }
            }
        });

        tipPercentageInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    int t = Integer.parseInt(tipPercentageInput.getText().toString());
                    seekBar.setProgress(t);
                }
                return false;
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipPercentageInput.setText(i + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






    }
}