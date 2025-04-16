package com.group08.finotes.AppDataStructure;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bill implements Serializable {
    private String billID, Description, PersonID, category;
    private Wallet sourceMoney;
    private Date dateOfNotification, dateOfCreation;
    private float interestRate;
    private int amountOfMoney;

    public Bill(String Description, String Category, Date creationDate, int Money, Wallet source) {
        this.billID = billIDGenerator();
        this.Description = Description;
        this.category = Category;
        this.dateOfCreation = creationDate;
        this.amountOfMoney = Money;
        this.sourceMoney = source;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Date getDateOfNotification() {
        return dateOfNotification;
    }

    public void setDateOfNotification(Date dateOfNotification) {
        this.dateOfNotification = dateOfNotification;
    }

    @Override
    public String toString() {
        DateFormat templateDate = new SimpleDateFormat("DD-MM-YYYY");
        return "Bill{" +
                "billID='" + billID + '\'' +
                ", Description='" + Description + '\'' +
                ", category='" + category + '\'' +
                ", Wallet=" + sourceMoney.getWalletName() +
                ", dateOfCreation=" + templateDate.format(dateOfCreation) +
                ", amountOfMoney=" + amountOfMoney +
                '}';
    }

    public Bill(String description, String category, Date dateOfCreation,Date dateOfNotification, int amountOfMoney, Wallet source) {
        this.billID = billIDGenerator();
        Description = description;
        this.category = category;
        this.dateOfCreation = dateOfCreation;
        this.amountOfMoney = amountOfMoney;
        this.sourceMoney = source;
        this.PersonID = ""; // dummy value
        this.interestRate = 0f; // dummy value
        this.dateOfNotification = dateOfNotification;
    }

    public Bill(String description, String personID, String category, Date dateOfNotification,Wallet moneySource ,Date dateOfCreation, float interestRate, int amountOfMoney) {
        this.billID = billIDGenerator();
        Description = description;
        PersonID = personID;
        this.sourceMoney = moneySource;
        this.category = category;
        this.dateOfNotification = dateOfNotification;
        this.dateOfCreation = dateOfCreation;
        this.interestRate = interestRate;
        this.amountOfMoney = amountOfMoney;
    }

    public Bill(String billID, String description, String personID, String category, Wallet sourceMoney, Date dateOfNotification, Date dateOfCreation, float interestRate, int amountOfMoney) {
        this.billID = billID;
        Description = description;
        PersonID = personID;
        this.category = category;
        this.sourceMoney = sourceMoney;
        this.dateOfNotification = dateOfNotification;
        this.dateOfCreation = dateOfCreation;
        this.interestRate = interestRate;
        this.amountOfMoney = amountOfMoney;
    }

    public String billIDGenerator(){
        return String.valueOf(System.currentTimeMillis());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String personID) {
        PersonID = personID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getBillID() {
        return billID;
    }

    public String getWalletID(){
        return sourceMoney.getWalletName();
    }
    public Wallet getWallet(){
        return sourceMoney;
    }
    public void setWallet(Wallet newWallet){
        sourceMoney = newWallet;
    }
    public void setBillID(String billID) {
        this.billID = billID;
    }

    public boolean isBillValid(Bill temp){
        if(temp == null || temp.getInterestRate() < 0  ||  temp.getAmountOfMoney() < 0) return false;
        return true;
    }

}
