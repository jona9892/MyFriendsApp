package com.example.jona9892.myfriendsapp.Model.Implement;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by jona9892 on 09-03-2016.
 */
public class Friend implements Serializable{
    private String name;
    private int phoneNumber;
    private String email;
    private String address;
    private String url;
    private int id;
    private LatLng home;
    public static int IDENTITY = 1;

    public int getId() {
        return id;
    }

    public Friend(String name, int phoneNumber, String email, String address, String url){
        id = IDENTITY++;

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.url = url;
    }

    public LatLng getHome() {
        return home;
    }

    public void setHome(LatLng home) {
        this.home = home;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
