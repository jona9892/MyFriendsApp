package com.example.jona9892.myfriendsapp.Controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jona9892.myfriendsapp.R;
import com.google.android.gms.maps.model.LatLng;

public class LocationActivity extends AppCompatActivity {

    LatLng xom;
    private Button btnBack;
    private TextView lblDestinationResult, lblCurrentResult,lblDistanceResult;

    //TODO: we need to be able to set the location of the friend.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        getWidgets();
        setUpButtons();
    }

    /**
     * Sets up the buttons.
     */
    private void setUpButtons() {
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                closeThisActivity();
            }
        });
    }

    /**
     * Closes this activity
     */
    private void closeThisActivity() {
        finish();
    }

    /**
     * Gets hold of the widgets in the view.
     */
    private void getWidgets(){
        //----------------Buttons--------------------
        btnBack = (Button) findViewById(R.id.btnBack);
        //-------------------------------------------

        //----------------Labels-----------------------
        lblDestinationResult = (TextView) findViewById(R.id.lblDestinationResult);
        lblCurrentResult = (TextView) findViewById(R.id.lblCurrentResult);
        lblDistanceResult = (TextView) findViewById(R.id.lblDistanceResult);
        //---------------------------------------------
    }

}
