package com.studies.sandrini.tipcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private double currentBillTotal;
    private int currentCustomPercent;
    private EditText tip10, tip15, tip20, total10, total15, total20, customTip, billTotal, totalCustom;
    private TextView customTipResult;
    private SeekBar customTipBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billTotal = (EditText) findViewById(R.id.bill_total);
        tip10 = (EditText) findViewById(R.id.tip_ten);
        tip15 = (EditText) findViewById(R.id.tip_fifteen);
        tip20 = (EditText) findViewById(R.id.tip_twenty);
        total10 = (EditText) findViewById(R.id.total_ten);
        total15 = (EditText) findViewById(R.id.total_fifteen);
        total20 = (EditText) findViewById(R.id.total_twenty);
        customTipBar = (SeekBar) findViewById(R.id.custom_tip_bar);
        customTipResult = (TextView) findViewById(R.id.custom_tip_result);
        customTip = (EditText) findViewById(R.id.custom__tip_edit);
        totalCustom = (EditText) findViewById(R.id.total_custom);

        currentCustomPercent = customTipBar.getProgress();

        billTotal.addTextChangedListener(billTextWatcher);

        customTipBar.setOnSeekBarChangeListener(customTipBarListener);
    }

    private SeekBar.OnSeekBarChangeListener customTipBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    currentCustomPercent = progress;
                    updateCustom();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private void updateStandard() {
        double tenPercentTip = currentBillTotal * .10;
        double tenPercentTotal = currentBillTotal + tenPercentTip;
        double fifteenPercentTip = currentBillTotal * .15;
        double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;
        double twentyPercentTip = currentBillTotal * .20;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;

        tip10.setText(String.format("%.02f", tenPercentTip));
        total10.setText(String.format("%.02f", tenPercentTotal));
        tip15.setText(String.format("%.02f", fifteenPercentTip));
        total15.setText(String.format("%.02f", fifteenPercentTotal));
        tip20.setText(String.format("%.02f", twentyPercentTip));
        total20.setText(String.format("%.02f", twentyPercentTotal));
    }

    private void updateCustom() {
        customTipResult.setText(currentCustomPercent + "%");

        double customTipAmount = currentBillTotal * currentCustomPercent * .01;
        double customTotalAmount = currentBillTotal + customTipAmount;

        customTip.setText(String.format("%.02f", customTipAmount));
        totalCustom.setText(String.format("%.02f", customTotalAmount));
    }

    private TextWatcher billTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try{
                currentBillTotal = Double.parseDouble(s.toString());
            }catch (NumberFormatException e){
                currentBillTotal = 0.0;
            }
            updateCustom();
            updateStandard();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };



}
