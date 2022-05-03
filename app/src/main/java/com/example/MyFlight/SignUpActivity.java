package com.example.MyFlight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
public MyJFrame(OrderingSystem os) {
        this.os = os;
        isLogin = false;//Default login status
        onOrder = false;

        //JPanel for root
        root = new JPanel(null);
        this.setContentPane(root);
        this.setTitle("Computer shop");

        //Button for login as new
        signUpBut = new JButton("Sign up");
        signUpBut.setBounds(50, 650, 140, 50);
        signUpBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        signUpBut.addActionListener(e -> {

            //Show the dialog to sign up
            supDialog.setVisible(true);
        });
        root.add(signUpBut);

        //Button for login as existed user
        logInBut = new JButton("Log in");
        logInBut.setBounds(50, 720, 140, 50);
        logInBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        logInBut.addActionListener(e -> {

            //Show the dialog to login
            logDialog.setVisible(true);
        });
        root.add(logInBut);

        //Button for log out
        logOutBut = new JButton("Log out");
        logOutBut.setBounds(50, 790, 140, 50);
        logOutBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        logOutBut.addActionListener(e -> {
            try {
                logOut();
            } catch (IllegalArgumentException exception) {
                //The error message will display as Dialog via JOptionPane
                JOptionPane.showMessageDialog(MyJFrame.this, exception.getMessage());
            }

        });
        root.add(logOutBut);

        //Model for computer parts
        compModel = new DefaultListModel<>();
        for (ComputerPart cp : os.getComputerParts()) {
            compModel.addElement(cp);
        }
        compJList = new JList<>();
        compJList.setModel(compModel);
        compJList.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        allPartsPane = new JScrollPane(compJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        allPartsPane.setBounds(300, 60, 800, 120);
        root.add(allPartsPane);

        compLabel = new JLabel("Computer Parts");
        compLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
        compLabel.setBounds(600, 15, 200, 40);
        root.add(compLabel);

        //Add computer parts button
        addPartBut = new JButton("Add to chart");
        addPartBut.setBounds(1160, 90, 140, 60);
        addPartBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        addPartBut.setEnabled(isLogin && onOrder);
        addPartBut.addActionListener(e -> {
            try {
                addPart();
            } catch (IllegalArgumentException exception) {
                //The error message will display as Dialog via JOptionPane
                JOptionPane.showMessageDialog(MyJFrame.this, exception.getMessage());
            }
        });
        root.add(addPartBut);

        //Text area to display current order
        currentOrdArea = new JTextArea();
        currentOrdArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        myCartPane = new JScrollPane(currentOrdArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        myCartPane.setBounds(500, 250, 600, 120);
        root.add(myCartPane);

        //Display Strings of part id, then match the Arraylist of my parts.getID.equals()
        myCartModel = new DefaultListModel<>();
        myCartJList = new JList<>();
        myCartJList.setModel(myCartModel);
        myCartJList.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        currentOrdPane = new JScrollPane(myCartJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        currentOrdPane.setBounds(300, 250, 160, 120);
        root.add(currentOrdPane);

        //Delete parts
        delPartBut = new JButton("Delete part");
        delPartBut.setBounds(1160, 260, 140, 40);
        delPartBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        delPartBut.setEnabled(isLogin && onOrder);
        delPartBut.addActionListener(e -> {
            try {
                delPart();
            } catch (IllegalArgumentException exception) {
                //The error message will display as Dialog via JOptionPane
                JOptionPane.showMessageDialog(MyJFrame.this, exception.getMessage());
            }
        });
        root.add(delPartBut);

        myCartLabel = new JLabel("My Cart");
        myCartLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
        myCartLabel.setBounds(340, 205, 200, 40);
        root.add(myCartLabel);

        currentOrdlabel = new JLabel("Current Order");
        currentOrdlabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
        currentOrdlabel.setBounds(722, 205, 240, 40);
        root.add(currentOrdlabel);

        hisOrdLabel = new JLabel("My Orders");
        hisOrdLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
        hisOrdLabel.setBounds(328, 395, 240, 40);
        root.add(hisOrdLabel);

        hisOrdDetailLabel = new JLabel("History Order");
        hisOrdDetailLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 22));
        hisOrdDetailLabel.setBounds(725, 395, 240, 40);
        root.add(hisOrdDetailLabel);

        //Historical order strings of ID
        hisOrdModel = new DefaultListModel<>();
        hisOrdJList = new JList<>();
        hisOrdJList.setModel(hisOrdModel);
        hisOrdJList.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        hisOrdPane = new JScrollPane(hisOrdJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        hisOrdPane.setBounds(300, 440, 160, 120);
        root.add(hisOrdPane);

        //Historical order detials
        hisOrdDetailArea = new JTextArea();
        hisOrdDetailArea = new JTextArea();
        hisOrdDetailArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        hisOrdDetailPane = new JScrollPane(hisOrdDetailArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        hisOrdDetailPane.setBounds(500, 440, 600, 120);
        root.add(hisOrdDetailPane);

        //Submit button
        subOrdBut = new JButton("Submit order");
        subOrdBut.setBounds(1160, 320, 140, 40);
        subOrdBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        subOrdBut.setEnabled(isLogin && onOrder);
        subOrdBut.addActionListener(e -> {
            try {
                submitOrder();
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(MyJFrame.this, exception.getMessage());
            }
        });
        root.add(subOrdBut);

        //Check selected historical order details
        cheOrdBut = new JButton("Check");
        cheOrdBut.setBounds(1160, 450, 140, 40);
        cheOrdBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        cheOrdBut.addActionListener(e -> {
            try {
                cheHisOrd();
            } catch (IllegalArgumentException exception){
                JOptionPane.showMessageDialog(MyJFrame.this, exception.getMessage());
            }
        });
        root.add(cheOrdBut);

        //Delete selected historical order
        delOrdBut = new JButton("Delete order");
        delOrdBut.setBounds(1160, 510, 140, 40);
        delOrdBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        delOrdBut.addActionListener(e -> {
            try {
                delOrd();
            } catch (IllegalArgumentException exception){
                JOptionPane.showMessageDialog(MyJFrame.this, exception.getMessage());
            }
        });
        root.add(delOrdBut);

        userInfoLabel1 = new JLabel();
        userInfoLabel1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        userInfoLabel1.setBounds(500, 640, 800, 40);
        root.add(userInfoLabel1);

        userInfoLabel2 = new JLabel();
        userInfoLabel2.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        userInfoLabel2.setBounds(500, 700, 800, 40);
        root.add(userInfoLabel2);

        userInfoLabel3 = new JLabel();
        userInfoLabel3.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        userInfoLabel3.setBounds(500, 760, 800, 40);
        root.add(userInfoLabel3);

        userInfoTitleLabel = new JLabel("Logged in user info");
        userInfoTitleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        userInfoTitleLabel.setBounds(695, 585, 340, 40);
        root.add(userInfoTitleLabel);

        //Export user's historical orders
        expOrdBut = new JButton("Export");
        expOrdBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        expOrdBut.setBounds(230, 650, 140, 50);
        expOrdBut.addActionListener(e -> {
            try {
                toExport();
                JOptionPane.showMessageDialog(MyJFrame.this,"The orders file has been generated");
            } catch (IllegalArgumentException exception){
                JOptionPane.showMessageDialog(MyJFrame.this, exception.getMessage());
                //We have already handle the IOExceptions in toExport(), just catch normal exceptions
            }
        });
        root.add(expOrdBut);

        //Exit current GUI, exit JVM
        exitBut = new JButton("Exit");
        exitBut.setBounds(230, 720, 140, 50);
        exitBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        exitBut.addActionListener(e -> {
            toExit();
        });
        root.add(exitBut);

        //Initialise buttons validations
        this.checkLogin();

        //Dialog for login as new
        //Pass a true value to initialise sign up dialog
        supDialog = new MyJDialog(this, this, true);

        //Dialog for login as existed user
        //Pass a false value to initialise login dialog
        logDialog = new MyJDialog(this, this, false);
        this.setBounds(0, 0, 1400, 900);
        this.setVisible(true);
    }

    public void toExport(){
        if (currentUser.getOrders().size()==0){
            throw new IllegalArgumentException("You have no order to export");
            //Orders cannot be exported when user has no order
        }
        try {
            //Define file as "user's ID" + ".txt"
            fos = new FileOutputStream(currentUser.getID() + ".txt");
            //Write down all info via byte[]
            fos.write((currentUser.toString() + currentUser.ordersPrinter()).getBytes());
            //Flush the remained data
            fos.flush();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();//Close the stream
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void toExit(){
        int isExit = JOptionPane.showConfirmDialog(this, "Are you sure to exit computer shop? All unsaved info will be lost",
                "Exit", JOptionPane.YES_NO_OPTION);
        //If user select "Yes" then will return a "0", "No" for "1"
        if (isExit==0) this.dispose();
    }

    public void delOrd() {
        if (hisOrdJList.isSelectionEmpty()){
            throw new IllegalArgumentException("Please select an order to delete");
            //Cannot delete order without a selection
        }
        currentUser.removeOrder(hisOrdJList.getSelectedIndex());
        currentUser.removeThat();
        //Reset JList
        hisOrdModel.clear();
        for (int i = 0; i < currentUser.getOrders().size(); i++) {
            hisOrdModel.addElement(String.format("%02d%2s%s", i, ": ", currentUser.getOrders().get(i).getID()));
        }
        hisOrdDetailArea.setText(""); //Reset JTextArea
    }

    public void cheHisOrd(){
        if (hisOrdJList.isSelectionEmpty()){
            throw new IllegalArgumentException("Please select an order to view");
            //Need a selection
        }
        String buffer = hisOrdJList.getSelectedValue();
        //buffer will get an index with ":", in order to get the useful data, use String.substring()
        for (Order order:currentUser.getOrders()){
            if (order.getID().equals(buffer.substring(4))){
                hisOrdDetailArea.setText(order.toString());
                return; //The for loop stops to improve the efficiency
            }
        }
    }

    public void logOut() {
        if (onOrder && thisOrder.getComputerParts().size() != 0) {
            throw new IllegalArgumentException("Check you bill before you log out");
            //Log out is denied if user is on an order
        }
        //Reset attributes to logout
        currentUser = null;
        isLogin = false;
        hisOrdModel.clear();
        hisOrdDetailArea.setText("");
        currentOrdArea.setText("");
        userInfoLabel1.setText("");
        userInfoLabel2.setText("");
        userInfoLabel3.setText("");
        checkLogin();
    }

    public void submitOrder() {
        if (thisOrder.getComputerParts().size() == 0) {
            throw new IllegalArgumentException("Your chart currently is empty");
            //No selected parts to sumbit
        }
        currentUser.addOrder(thisOrder);
        //Reset my chart and current order info
        myCartModel.clear();
        currentOrdArea.setText("");
        onOrder = false;
        thisOrder = null;
        checkLogin(); //Reset button validation
        hisOrdModel.clear(); //Reset JList
        for (int i = 0; i < currentUser.getOrders().size(); i++) {
            hisOrdModel.addElement(String.format("%02d%2s%s", i, ": ", currentUser.getOrders().get(i).getID()));
            //Refill JList elements
        }
    }

    public void delPart() {
        if (myCartJList.isSelectionEmpty()) {
            throw new IllegalArgumentException("Please select a part to delete");
        }
        thisOrder.removeComputerPart(myCartJList.getSelectedIndex());
        thisOrder.removeThat();
        myCartModel.clear();
        for (int i = 0; i < thisOrder.getComputerParts().size(); i++) {
            myCartModel.addElement(String.format("%02d%2s%s", i, ": ", thisOrder.getComputerParts().get(i).getID()));
            //Refill JList elements
        }
        currentOrdArea.setText(thisOrder.toString()); //Re-display current order with updated info
    }

    public void addPart() {
        //When user sumbited an order, he won't automatically log out, press addPart button to start a new order
        if (!onOrder) {
            //Check whether there is a discount order
            if (currentUser instanceof MemberCustomer) {
                thisOrder = new DiscountOrder();
            } else thisOrder = new Order();
            onOrder = true;
        }
        if (onOrder&&compJList.isSelectionEmpty()) {
            throw new IllegalArgumentException("Please select a part to add");
        }
        checkLogin(); //Update button validation
        thisOrder.addComputerPart(compJList.getSelectedValue());
        myCartModel.clear();
        //Re-fill elements of JList
        for (int i = 0; i < thisOrder.getComputerParts().size(); i++) {
            myCartModel.addElement(String.format("%02d%2s%s", i, ": ", thisOrder.getComputerParts().get(i).getID()));
        }
        currentOrdArea.setText(thisOrder.toString());
    }

    public void logUser(String[] info) {
        //Login with as new
        currentUser = new Customer(info[0], info[1], info[2], info[3]);
        isLogin = true;
        os.addCustomer(currentUser);
        userInfoLabel1.setText(currentUser.toString().substring(0, 52));
        userInfoLabel2.setText(currentUser.toString().substring(52, 84));
        //Fill JLabel with logged in user info
    }

    public void logUser(String info) {
        //Login as existed
        for (Customer customer : os.getCustomers()) {
            if (customer.getID().equals(info)) {
                currentUser = customer;
                isLogin = true;

                for (int i = 0; i < currentUser.getOrders().size(); i++) {
                    hisOrdModel.addElement(String.format("%02d%2s%s", i, ": ", currentUser.getOrders().get(i).getID()));
                }
                userInfoLabel1.setText(currentUser.toString().substring(0, 53));
                if (currentUser instanceof MemberCustomer){
                    //Member user has more strings
                    userInfoLabel2.setText(currentUser.toString().substring(53, 105));
                    userInfoLabel3.setText(currentUser.toString().substring(105));
                } else
                    userInfoLabel2.setText(currentUser.toString().substring(53));
                return;//Stop the method
            }
        }
        //If the compiler executes here, meaning that cannot match the id
        throw new IllegalArgumentException("Cannot match the ID you provided");
    }

    public void checkLogin() {
        //If user has already logged in, enable functions that needs users to log in
        //Instead, if user logged out or hasn't logged in, de-enable functions that needs users to log in
        //If user is not on an order, he is not allowed to delete parts or submit order

        signUpBut.setEnabled(!isLogin);
        logInBut.setEnabled(!isLogin);
        addPartBut.setEnabled(isLogin);
        delPartBut.setEnabled(isLogin && onOrder);
        subOrdBut.setEnabled(isLogin && onOrder);
        cheOrdBut.setEnabled(isLogin && currentUser.getOrders().size()!=0);
        delOrdBut.setEnabled(isLogin && currentUser.getOrders().size()!=0);
        userInfoTitleLabel.setVisible(isLogin);
        logOutBut.setEnabled(isLogin);
        expOrdBut.setEnabled(isLogin);

        //Because we did some button validation checks, in some of cases it won't throw exceptions regarding to login status
    }
}