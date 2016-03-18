package com.example.jona9892.myfriendsapp.Model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Max on 18-03-2016.
 */
public class Config {
    private static Config instance;
    private LatLng home;

    /**
     * singleton
     * @return
     */
    public static Config getInstance(){
        if(instance == null)
            instance = new Config();
        return instance;
    }

    /**
     * constructor: sets the location.
     */
    private Config(){
        home= new LatLng(55.476845,8.446445);
    }

    /**
     * @return the home location of the home.
     */
    public LatLng getHome() {
        return home;
    }
}
