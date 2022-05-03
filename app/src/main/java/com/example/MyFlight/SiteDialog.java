package com.example.MyFlight;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;


class MyJDialog extends JDialog {
    private JPanel mRoot;
    private MyJFrame ownerJFrame;

    //Some of parts may not be initialised, depends on what the boolean value that user passed is

    /*
    Login dialog
    */
    private JLabel cusIdLabel;
    private JTextField cusIdField;
    //Login(log) submit(sub) button
    private JButton logSubBut;
    //Login(log) clear button
    private JButton logClrBut;

    /*
    Signup dialog
    */
    private JLabel nameLabel;
    private JLabel genderLabel;
    private JLabel mobLabel;
    private JLabel addLabel;
    private JTextField nameField;
    private JTextField mobileField;
    private JTextField addrField;
    private JComboBox<String> genderCom;
    private JButton supSubBut;    //Signup(sup) submit(sub) button
    private JButton supClrBut;    //Signup(sup) clear(clr) button

    public MyJDialog(Window owner, MyJFrame ownerJFrame, boolean isSignUp) {
        super(owner); //MyJFrame owns MyJDialog at the moment
        this.ownerJFrame = ownerJFrame; //Parent window reference
        mRoot = new JPanel(null); //Absolute layout
        this.setContentPane(mRoot);
        this.setTitle("Login system");
        this.setBounds(600,400,600,200);
        this.setModal(true);

        //If initialise different dialog depends on boolean value
        if (isSignUp) initialSignUp();
        else initialLogin();
    }

    public void initialLogin(){
        //Initialise login dialog

        cusIdLabel = new JLabel("Please enter ID: ");
        cusIdField = new JTextField();
        logClrBut = new JButton("Clear");
        logSubBut = new JButton("Submit");

        cusIdLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        cusIdLabel.setBounds(140, 50,cusIdLabel.getPreferredSize().width,cusIdLabel.getPreferredSize().height);
        mRoot.add(cusIdLabel);

        cusIdField.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        cusIdField.setBounds(270,48,140,22);
        mRoot.add(cusIdField);

        logClrBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        logClrBut.setBounds(310,110,85,25);
        logClrBut.addActionListener(e -> {
            //Reset text
            cusIdField.setText("");
        });
        mRoot.add(logClrBut);

        //Login submission button
        logSubBut.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        logSubBut.setBounds(180,110,85,25);
        logSubBut.addActionListener(e -> {
            try {
                logInfoSub();
                //Close the dialog when submitting correct id
                MyJDialog.this.dispose();
            } catch (IllegalArgumentException exception){
                JOptionPane.showMessageDialog(MyJDialog.this, exception.getMessage());
            }
        });
        mRoot.add(logSubBut);
    }

    public void initialSignUp(){
        //Initialise sign up dialog
        nameLabel = new JLabel("Name: ");
        genderLabel = new JLabel("Gender: ");
        mobLabel = new JLabel("Mobile: ");
        addLabel = new JLabel("Delivery addr: ");

        nameField = new JTextField();
        genderCom = new JComboBox<>();
        mobileField = new JTextField();
        addrField = new JTextField();

        supClrBut = new JButton("Clear");
        supSubBut = new JButton("Submit");

        nameLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        nameLabel.setBounds(84,30,nameLabel.getPreferredSize().width, nameLabel.getPreferredSize().height);
        mRoot.add(nameLabel);

        nameField.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        nameField.setBounds(145,28,100,22);
        mRoot.add(nameField);

        genderLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        genderLabel.setBounds(70,70,genderLabel.getPreferredSize().width, genderLabel.getPreferredSize().height);
        mRoot.add(genderLabel);

        genderCom.addItem("male");
        genderCom.addItem("female");
        genderCom.setBounds(145,65,genderCom.getPreferredSize().width,genderCom.getPreferredSize().height);
        mRoot.add(genderCom);

        mobLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        mobLabel.setBounds(330,30,mobLabel.getPreferredSize().width,mobLabel.getPreferredSize().height);
        mRoot.add(mobLabel);

        mobileField.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        mobileField.setBounds(400, 28,120,22);
        mRoot.add(mobileField);

        addLabel.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        addLabel.setBounds(284,70,addLabel.getPreferredSize().width,addLabel.getPreferredSize().height);
        mRoot.add(addLabel);

        addrField.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
        addrField.setBounds(400,68,120,22);
        mRoot.add(addrField);

        supClrBut.setBounds(315,120,75,25);
        supClrBut.addActionListener(e -> {
            nameField.setText("");
            genderCom.setSelectedIndex(0);
            mobileField.setText("");
            addrField.setText("");
        });
        mRoot.add(supClrBut);

        supSubBut.setBounds(170,120,75,25);
        supSubBut.addActionListener(e -> {
            try {
                creInfoSub();
                //Close the dialog when submitting correct info
                MyJDialog.this.dispose();
            } catch (IllegalArgumentException exception){
                JOptionPane.showMessageDialog(MyJDialog.this, exception.getMessage());
            }
        });
        mRoot.add(supSubBut);
    }

    public void logInfoSub(){
        //Login information submit
        if (cusIdField.getText().trim().equals("")){
            throw new IllegalArgumentException("Please enter your ID");
        }
        ownerJFrame.logUser(cusIdField.getText().trim());
        ownerJFrame.checkLogin();
        cusIdField.setText("");
    }

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

