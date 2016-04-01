package com.example.jona9892.myfriendsapp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jona9892.myfriendsapp.DALC.Implementation.DALCFriend;
import com.example.jona9892.myfriendsapp.Model.Implement.Friend;
import com.example.jona9892.myfriendsapp.Model.Implement.MockFriend;
import com.example.jona9892.myfriendsapp.R;

public class MainActivity extends AppCompatActivity {
    //----------Views----------
    Button btnView;
    TextView txtAmount;
    //-------------------------

    private final int FRIEND_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: we need the init to be run, again. this could be solved by doing the start for result.
        init();
        setUpListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case FRIEND_REQUEST_CODE:
                if(resultCode == RESULT_OK) {
                    init();
                }
                else {
                    //Find out what to do.
                }
                break;

        }
    }

    /**
     * Gets the widgets
     */
    private void init(){
        btnView = (Button)findViewById(R.id.btnView);
        txtAmount = (TextView)findViewById(R.id.txtAmount);

        txtAmount.setText("" + new DALCFriend(this).readAll().size());
    }

    /**
     * Sets up the listeners
     */
    private void setUpListeners(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeFriends();
            }
        });
    }

    /**
     * starts an intent to see all the friends.
     */
    private void seeFriends(){
        Intent intent = new Intent();
        intent.setClass(this, FriendsActivity.class);
        startActivityForResult(intent, FRIEND_REQUEST_CODE);
    }
}
