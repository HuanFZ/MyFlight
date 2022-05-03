package com.example.MyFlight;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class MemberCustomer extends Customer{
    private final double DISCOUNT = 0.05;

    public MemberCustomer() {
    }

    public MemberCustomer(String ID, String name, String gender, String mobile, String address) {
        super(ID, name, gender, mobile, address);
    }

    public double getDISCOUNT() {
        return DISCOUNT;
    }

    @Override
    public String toString() {
        return super.toString() + "  " + getDISCOUNT() + " (Discount)";//Discount info should be displayed
    }
}