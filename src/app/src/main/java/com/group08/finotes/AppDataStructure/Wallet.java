package com.group08.finotes.AppDataStructure;
import java.io.Serializable;
import java.math.BigDecimal;

public class Wallet implements Serializable {
    private int amountOfMoney;
    private String currency, walletName;
    public Wallet(int money, String currency, String nameWallet){
        this.amountOfMoney = money;
        this.currency = currency;
        this.walletName = nameWallet;
    }
    public Wallet(){

    }
    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "amountOfMoney=" + amountOfMoney +
                ", currency='" + currency + '\'' +
                ", walletName='" + walletName + '\'' +
                '}';
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
}
