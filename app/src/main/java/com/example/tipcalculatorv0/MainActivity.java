package com.example.tipcalculatorv0;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance( );
    private EditText billEditText;
    private EditText tipEditText;
    private EditText guestEditText;

    private static final String MA = "MainActivity";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        tipCalc = new TipCalculator( 0.17f, 100.0f , 0.17f, 100.0f, 0f);
        setContentView( R.layout.activity_main);

        billEditText = ( EditText ) findViewById( R.id.editText_Bill_Amount);
        tipEditText = ( EditText ) findViewById( R.id.editText_Enter_Tip );
        guestEditText = (EditText) findViewById( R.id.editText_Guests);

        TextChangeHandler tch = new TextChangeHandler( );
        billEditText.addTextChangedListener( tch );
        tipEditText.addTextChangedListener(tch);
        guestEditText.addTextChangedListener(tch);
    }

    private void modifyLayout(Configuration newConfig) {
        Log.w(MA, "Inside modifyLayout");

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            setContentView(R.layout.activity_main);
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.w(MA, "Inside onConfigurationChanged Method");
        super.onConfigurationChanged(newConfig);
        modifyLayout(newConfig);
    }

    public void calculate( ) {
        String billString = billEditText.getText( ).toString( );
        String tipString = tipEditText.getText( ).toString( );
        String guestString = guestEditText.getText( ).toString( );

        TextView tipTextView =
                ( TextView ) findViewById( R.id.textView_Tip_Amount );
        TextView totalTextView =
                (TextView) findViewById( R.id.textView_Amount_Total );
        TextView perTipTextView =
                (TextView) findViewById( R.id.textView_Tip_Per_Amount );
        TextView perTotalTextView =
                (TextView) findViewById( R.id.textView_Amount_Per_Total );
        try {
            // convert billString and tipString to floats
            float billAmount = Float.parseFloat( billString );
            int tipPercent = Integer.parseInt( tipString );
            float guestAmount = Float.parseFloat( guestString );
            // update the Model
            tipCalc.setBill( billAmount );
            tipCalc.setTip( .01f * tipPercent );
            //tipCalc.setPerBill(billAmount / guestAmount);
           // tipCalc.setPerTip((.01f * tipPercent) / guestAmount);
            tipCalc.setGuestTotal(guestAmount);
            // ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount();
            float total = tipCalc.totalAmount();
            float perTip = tipCalc.perTipAmount();
            float perTotal = tipCalc.perTotalAmount();
            // update the View with formatted tip and total amounts
            tipTextView.setText( money.format( tip ) );
            totalTextView.setText( money.format( total ) );
            perTipTextView.setText( money.format( perTip ) );
            perTotalTextView.setText( money.format( perTotal ) );
        } catch( NumberFormatException nfe ) {
            // pop up an alert view here
        }
    }

    private class TextChangeHandler implements TextWatcher {
        public void afterTextChanged( Editable e ) {
            calculate( );
        }

        public void beforeTextChanged( CharSequence s, int start,
                                       int count, int after ) {
        }

        public void onTextChanged( CharSequence s, int start,
                                   int before, int after ) {
        }
    }
}