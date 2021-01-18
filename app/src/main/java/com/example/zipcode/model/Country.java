package com.example.zipcode.model;

import java.io.Serializable;

public class Country implements Serializable{
    private String Name;
    private String Address;
    private String Area;
    private String Country;
    private String Zipcode;

    public String getName() {return Name;}

    public void setName(String name) {Name = name;}

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getZipCode() {
        return Zipcode;
    }

    public void setZipCode(String zipCode) {
        Zipcode = zipCode;
    }

    @Override
    public String toString(){
        return (getName());
    }
}