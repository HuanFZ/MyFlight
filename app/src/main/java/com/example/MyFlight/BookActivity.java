package com.example.MyFlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookActivity extends AppCompatActivity {
    private Button backBtn;
    public void creInfoSub() {
        //Create information submit

        //User may input nothing or invalid stuff of space
        if (nameField.getText().trim().equals("")){
            throw new IllegalArgumentException("Please enter your name");
        }

        if (mobileField.getText().trim().equals("")){
            throw new IllegalArgumentException("Please enter your mobile number");
        }

        if (addrField.getText().trim().equals("")){
            throw new IllegalArgumentException("Please enter your address");
        }

        ownerJFrame.logUser(new String[]{nameField.getText().trim(), (String) genderCom.getSelectedItem(), mobileField.getText().trim(), addrField.getText().trim()});
        ownerJFrame.checkLogin();//Renew the login status
        nameField.setText("");
        genderCom.setSelectedIndex(0);
        mobileField.setText("");
        addrField.setText("");
    }
}
