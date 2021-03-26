package com.example.widgets_are_us.productsservice.utils;

import javax.persistence.Embeddable;
import java.text.DecimalFormat;

@Embeddable
public class Dollars {

    private static final long MULTIPLIER = 100_000_000L;
    private static final DecimalFormat df = new DecimalFormat("#.00");

    private Long amount;

    public Dollars() {
        this.amount = 0L;
    }

    public Dollars(int amt) {
        this.amount = amt * MULTIPLIER;
    }

    public Dollars(long amt) {
        this.amount = amt * MULTIPLIER;
    }

    public Dollars(float amt) {
        this.amount = (long) (amt * MULTIPLIER);
    }

    public Dollars(double amt) {
        this.amount = (long) (amt * MULTIPLIER);
    }

    public double toDecimal() {
        return (double) amount / MULTIPLIER;
    }

    public String toString() {
        return df.format((double) Math.round(this.toDecimal() * 100) / 100);
    }

    public Dollars add(Dollars value) {
        this.amount += value.amount;

        return this;
    }

    public Dollars multiply(int value) {
        this.amount *= value;

        return this;
    }

    public Dollars multiply(float value) {
        this.amount = (long) (this.amount * value);

        return this;
    }

    public Dollars multiply(double value) {
        this.amount = (long) (this.amount * value);

        return this;
    }

    public Dollars divide(int value) {
        this.amount = this.amount / value;

        return this;
    }

    public Dollars divide(float value) {
        this.amount = (long) (this.amount / value);

        return this;
    }

    public Dollars divide(double value) {
        this.amount = (long) (this.amount / value);

        return this;
    }

    public Dollars subtract(Dollars value) {
        this.amount -= value.amount;

        return this;
    }

    public Dollars calculateSalesTaxByPercentage(float percentage) {
        return new Dollars(this.amount * percentage / 100);
    }

    public Dollars calculateSalesTaxByPercentage(double percentage) {
        return new Dollars(this.amount * percentage / 100);
    }

    public Dollars calculateSalesTax(float tax) {
        return new Dollars(this.amount * tax);
    }

    public Dollars calculateSalesTax(double tax) {
        return new Dollars(this.amount * tax);
    }

}
