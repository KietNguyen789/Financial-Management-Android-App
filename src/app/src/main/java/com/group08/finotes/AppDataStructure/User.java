package com.group08.finotes.AppDataStructure;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.group08.finotes.AppDataStructure.Address;
import com.group08.finotes.AppDataStructure.Wallet;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class User {
    private String userName;
    private String userEmail;
    private Address userAdd;
    private PremiumStatus userStatus;
    private ArrayList<Bill> userBills;
    private ArrayList<Wallet> userWallets;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAdd=" + userAdd +
                ", userStatus=" + userStatus.getState() +
                '}';
    }

    public User(String name, String email, Address address){
        this.userName = name;
        this.userEmail = email;
        this.userAdd = address;
        this.userWallets = new ArrayList<>();
        this.userWallets.add(new Wallet(0,"VND","Default"));
        this.userBills = new ArrayList<>();
        this.userStatus = new PremiumStatus();
    }
    public User(){

    }
    public User(String name, String email, Address address, ArrayList<Wallet> wallets, ArrayList<Bill> bills, PremiumStatus premium){
        this.userName = name;
        this.userEmail = email;
        this.userAdd = address;
        this.userWallets = wallets;
        this.userBills = bills;
        this.userStatus = premium;
    }

    public ArrayList<Bill> getUserBills() {
        return userBills;
    }


    public ArrayList<Wallet> getUserWallets() {
        return userWallets;
    }

    public void setUserWallets(ArrayList<Wallet> userWallets) {
        this.userWallets = userWallets;
    }

    public Address getUserAdd() {
        return userAdd;
    }

    public void setUserAdd(Address userAdd) {
        this.userAdd = userAdd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    static boolean isPassValid(String pass){
        if(pass.length() >= 8)  return true;
        return false;
    }
    public void setUserBills(ArrayList<Bill> userBills) {
        this.userBills = userBills;
    }

    public PremiumStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(PremiumStatus userStatus) {
        this.userStatus = userStatus;
    }

}
