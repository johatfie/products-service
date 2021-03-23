package com.example.widgets_are_us.productsservice.utils;

import java.text.DecimalFormat;

public class Dollars {

    private static final long MULTIPLIER = 100_000_000L;
    private static final long CONVERSION_MULTIPLIER = 100_000L;
    private static final DecimalFormat df = new DecimalFormat("#.00");

    private Long amount;

    public Dollars() {
        this.amount = 0L;
    }

    public Dollars(int amt) {
        this.amount = amt * MULTIPLIER;
    }

    public Dollars(double amt) {
        this.amount = (long) (amt * MULTIPLIER);
    }

    public void fromDecimal(double amt) {
        this.amount = (long) (amt * MULTIPLIER);
    }
    public double toDecimal() {
        //double intermediateValue = (double) (amount / CONVERSION_MULTIPLIER);
        //double intermediateValue = (double) amount / MULTIPLIER;
        return (double) amount / MULTIPLIER;

        //return df.format((double) Math.round(intermediateValue * 100) / 100);
    }

    public String toMoney() {
        //double intermediateValue = (double) (amount / MULTIPLIER);

        return df.format((double) Math.round(this.toDecimal() * 100) / 100);
    }


}
