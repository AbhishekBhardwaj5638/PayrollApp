package com.bhardwaj.abhishek.payrollapp.Model;

public class Earnings {

    private float amount;
    private String date;
    private String payStubId;


    public String getPayStubId() {

        return payStubId;
    }

    public void setPayStubId(String payStubId) {
        this.payStubId = payStubId;
    }


    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
