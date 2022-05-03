package com.example.MyFlight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UserInfos {
    class UserInfo {
        public String account;
        public String passwd;
        public String name;
        public String phonenumber;
        public UserInfo(){};
    }

    private static UserInfos userInfos = null;

    public static UserInfos getUserInfo(){
        if(userInfos == null){
            userInfos = new UserInfos();
        }
        return userInfos;
    }
}
