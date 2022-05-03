package com.example.MyFlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

class Customer implements Serializable {

    private static final long serialVersionUID = -6307176000003L;
    private final String ID;
    private String name;
    private String gender;
    private String mobile;
    private String address;
    private ArrayList<Order> orders;
    private ArrayList<Order> removeOrder;

    public Customer() {
        ID = null;
    }

    public Customer(String ID, String name, String gender, String mobile, String address) {
        this.ID = ID;
        this.name = name;
        this.gender = gender;
        this.mobile = mobile;
        this.address = address;
        orders = new ArrayList<>();
        removeOrder = new ArrayList<>();
    }
