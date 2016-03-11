package com.example.jona9892.myfriendsapp.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jona9892.myfriendsapp.Model.Abstraction.IFriendLog;
import com.example.jona9892.myfriendsapp.Model.Implement.FriendLog;
import com.example.jona9892.myfriendsapp.R;

public class MainActivity extends AppCompatActivity {
    //----------Views----------
    Button btnView;
    TextView txtAmount;
    //-------------------------

    //--------Variables--------
    private IFriendLog friendLog;
    //-------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        friendLog = FriendLog.getInstance();

        init();
        setUpListeners();
    }

    private void init(){
        btnView = (Button)findViewById(R.id.btnView);
        txtAmount = (TextView)findViewById(R.id.txtAmount);
        txtAmount.setText(""+friendLog.getAll().size());
    }

    private void setUpListeners(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeFriends();
            }
        });
    }

    private void seeFriends(){
        Intent intent = new Intent();
        intent.setClass(this, FriendsActivity.class);
        startActivity(intent);
    }
}
