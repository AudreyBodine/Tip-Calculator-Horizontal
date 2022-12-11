package com.example.tipcalculatorv0;

public class TipCalculator {
    // Code pasted from Github
    private float tip;
    private float bill;
    private float perTip;
    private float perBill;
    private float guest;

    public TipCalculator(float newTip, float newBill, float perBill, float perTip, float newGuest ) {
        setTip( newTip );
        setBill( newBill );
        setPerBill(perBill);
        setPerTip(perTip);
        setGuestTotal(newGuest);
    }

    public float getTip( ) {
        return tip;
    }

    public float getBill( ) {
        return bill;
    }

    public void setTip( float newTip ) {
        if( newTip > 0 )
            tip = newTip;
    }

    public void setBill( float newBill ) {
        if( newBill > 0 )
            bill = newBill;
    }

    public float tipAmount( ) {
        return bill * tip;
    }

    public float totalAmount( ) {
        return bill + tipAmount( );
    }

    public void setPerBill(float newBill) {
        if( newBill > 0 )
            perBill = newBill;
    }

    public void setPerTip(float newTip) {
        if( newTip > 0 )
            perTip = newTip;
    }

    public void setGuestTotal(float newGuest) {
        if( newGuest > 0 )
            guest = newGuest;
    }

    public float perTipAmount() {
        return tipAmount()/ guest;
    }

    public float perTotalAmount() {
        return totalAmount() / guest;
    }
}

