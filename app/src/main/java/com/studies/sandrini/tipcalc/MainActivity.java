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
    private int currentCustomPercent, shareNumber;
    private EditText tip10, tip15, tip20, total10, total15, total20, customTip, billTotal, customTotal,
            personTotal10, personTotal15, personTotal20, customPersonTotal;
    private TextView customTipResult, shareResult;
    private SeekBar customTipBar, shareBar;

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
        customTotal = (EditText) findViewById(R.id.custom_total);
        personTotal10 = (EditText) findViewById(R.id.total_person_ten);
        personTotal15 = (EditText) findViewById(R.id.total_person_fifteen);
        personTotal20 = (EditText) findViewById(R.id.total_person_twenty);
        shareBar = (SeekBar) findViewById(R.id.share_bar);
        shareResult = (TextView) findViewById(R.id.share_result);
        customPersonTotal = (EditText) findViewById(R.id.shared_custom_total);

        currentCustomPercent = customTipBar.getProgress();

        billTotal.addTextChangedListener(billTextWatcher);

        customTipBar.setOnSeekBarChangeListener(customTipBarListener);


        shareBar.setOnSeekBarChangeListener(shareBarListener);
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


    private SeekBar.OnSeekBarChangeListener shareBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    shareNumber = progress;
                    if(progress < 1){
                        shareBar.setProgress(1);
                    }
                    updateStandard();
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
        double tenPercentShared = tenPercentTotal / shareNumber;
        double fifteenPercentTip = currentBillTotal * .15;
        double fifteenPercentTotal = currentBillTotal + fifteenPercentTip;
        double fifteenPercentShared = fifteenPercentTotal / shareNumber;
        double twentyPercentTip = currentBillTotal * .20;
        double twentyPercentTotal = currentBillTotal + twentyPercentTip;
        double twentyPercentShared = twentyPercentTotal / shareNumber;

        tip10.setText(String.format("%.02f", tenPercentTip));
        total10.setText(String.format("%.02f", tenPercentTotal));
        personTotal10.setText(String.format("%.02f", tenPercentShared));
        tip15.setText(String.format("%.02f", fifteenPercentTip));
        total15.setText(String.format("%.02f", fifteenPercentTotal));
        personTotal15.setText(String.format("%.02f", fifteenPercentShared));
        tip20.setText(String.format("%.02f", twentyPercentTip));
        total20.setText(String.format("%.02f", twentyPercentTotal));
        personTotal20.setText(String.format("%.02f", twentyPercentShared));
    }

    private void updateCustom() {
        customTipResult.setText(currentCustomPercent + "%");
        shareResult.setText(String.valueOf(shareNumber));

        double customTipAmount = currentBillTotal * currentCustomPercent * .01;
        double customTotalAmount = currentBillTotal + customTipAmount;
        double sharedCustomTotal = customTotalAmount / shareNumber;

        customTip.setText(String.format("%.02f", customTipAmount));
        customTotal.setText(String.format("%.02f", customTotalAmount));
        customPersonTotal.setText(String.format("%.02f", sharedCustomTotal));
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
