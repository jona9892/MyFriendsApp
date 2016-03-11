package com.example.jona9892.myfriendsapp.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jona9892.myfriendsapp.Model.Implement.Friend;
import com.example.jona9892.myfriendsapp.Model.Implement.MockFriend;
import com.example.jona9892.myfriendsapp.R;

public class AddFriendActivity extends AppCompatActivity {
    EditText txtName;
    EditText txtPhone;
    EditText txtEmail;
    EditText txtAddress;
    EditText txtUrl;

    Button btnCancel;
    Button btnSave;

    private final String TAG = "HistoryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__edit_);
        getWidgets();
        setUpButtons();
    }

    private void getWidgets(){
        txtName = (EditText)findViewById(R.id.txtName);
        txtPhone = (EditText)findViewById(R.id.txtPhone);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtAddress = (EditText)findViewById(R.id.txtAddress);
        txtUrl = (EditText)findViewById(R.id.txtUrl);

        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnSave = (Button)findViewById(R.id.btnSave);
    }

    private void setUpButtons(){
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend();
                finish();
            }
        });
    }

    private void cancel(){
        finish();
    }

    private void addFriend(){
        String name = txtName.getText().toString();
        int number = Integer.parseInt(txtPhone.getText().toString());
        String email = txtEmail.getText().toString();
        String address = txtAddress.getText().toString();
        String url = txtUrl.getText().toString();

        Friend friend = new Friend(name, number, email, address, url);

        MockFriend.getInstance().add(friend);
        Log.d(TAG, String.valueOf(MockFriend.getInstance().readAll().size()));
    }
}
