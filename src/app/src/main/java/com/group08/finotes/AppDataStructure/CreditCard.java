package com.group08.finotes.AppDataStructure;
import java.util.Date;
public class CreditCard {
    private int cardNumber, signatureNumber;
    private String ownerID;
    private Date creationDate;

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                ", signatureNumber=" + signatureNumber +
                ", ownerID='" + ownerID + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }

    CreditCard(){

    }
    CreditCard(int cardNum,int cvv, String owner, Date initDate){
        this.cardNumber = cardNum;
        this.signatureNumber = cvv;
        this.ownerID = owner;
        this.creationDate = initDate;
    }
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public int getSignatureNumber() {
        return signatureNumber;
    }

    public void setSignatureNumber(int signatureNumber) {
        this.signatureNumber = signatureNumber;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
}
