package com.dayal.broadcasts.model;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IncomingNumber {
    private int id;
    private String number;

    public IncomingNumber() {
    }

    public IncomingNumber(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
