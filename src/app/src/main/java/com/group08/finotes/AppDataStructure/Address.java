package com.group08.finotes.AppDataStructure;

import java.io.Serializable;

public class Address implements Serializable {
    private String Province;

    @Override
    public String toString() {
        return "Address{" +
                "Province='" + Province + '\'' +
                ", District='" + District + '\'' +
                ", Sub_District='" + Sub_District + '\'' +
                '}';
    }

    private String District;
    private String Sub_District;
    public Address(String pro, String dis, String subDis){
        this.Province = pro;
        this.District = dis;
        this.Sub_District = subDis;
    }
    public Address(){

    }
    public String getDistrict() {
        return District;
    }
    public String getSub_District() {
        return Sub_District;
    }
    public String getProvince() {
        return Province;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public void setSub_District(String sub_District) {
        Sub_District = sub_District;
    }

    public void setProvince(String province) {
        Province = province;
    }
}
