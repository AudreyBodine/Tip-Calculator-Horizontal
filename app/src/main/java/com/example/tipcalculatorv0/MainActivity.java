package com.example.tipcalculatorv0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private TipCalculator tipCalc;
    private NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billEditText;
    private EditText tipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalc = new TipCalculator(.17f, 100f);
        setContentView(R.layout.activity_main);

        billEditText = findViewById(R.id.editText_Bill_Amount);
        tipEditText = findViewById(R.id.editText_Enter_Tip);

        TextChangeHandler tch = new TextChangeHandler();
        billEditText.addTextChangedListener(tch);
        tipEditText.addTextChangedListener(tch);

    }

    public void calculate () {

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();

        TextView tipTextView = findViewById(R.id.textView_Tip_Amount);
        TextView totalTextView = findViewById(R.id.textView_Amount_Total);

        try {

            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            tipCalc.setBill(billAmount);
            tipCalc.setTip(.01f * tipPercent);

            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();

            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));

        } catch (NumberFormatException nfe) {

        }
    }

    private class TextChangeHandler implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
                calculate();
        }
    }
}