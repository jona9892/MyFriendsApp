package com.example.jona9892.myfriendsapp.Controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jona9892.myfriendsapp.Model.Implement.Friend;
import com.example.jona9892.myfriendsapp.Model.Implement.MockFriend;
import com.example.jona9892.myfriendsapp.R;

public class EditFriendActivity extends AppCompatActivity {
    //-----------Views----------
    EditText txtName;
    EditText txtPhone;
    EditText txtEmail;
    EditText txtAddress;
    EditText txtUrl;
    //---------Buttons--------
    Button btnCancel;
    Button btnSave;
    Button btnCall;
    Button btnSMS;
    Button btnEmail;
    Button btnHomePage;

    int position;
    ActivityType theType;

    private enum ActivityType {
        ADD, EDIT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__edit_);
        getWidgets();

        decideType();

        setUpButtons();

    }

    /**
     * finds out wether this is to edit or add.
     */
    private void decideType() {
        if (isEdit()) {
            theType = ActivityType.EDIT;
            setInfo();
        } else {
            theType = ActivityType.ADD;
        }
    }

    /**
     * Checks if this activity should be used to edit or add.
     * @return true if it's to edit.
     */
    private boolean isEdit() {
        return getIntent().getSerializableExtra("Friend") != null;
    }

    /**
     * initialises all the widgets.
     */
    private void getWidgets() {
        txtName = (EditText) findViewById(R.id.txtName);
        txtPhone = (EditText) findViewById(R.id.txtPhone);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtAddress = (EditText) findViewById(R.id.txtAddress);
        txtUrl = (EditText) findViewById(R.id.txtUrl);

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnCall = (Button) findViewById(R.id.btnCall);
        btnSMS = (Button) findViewById(R.id.btnSMS);
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnHomePage = (Button) findViewById(R.id.btnHomepage);
    }

    /**
     * sets the information in the widget, but only if it's an edit
     */
    private void setInfo() {
        //TODO: this should get the friend that is in the intent instead.
        txtName.setText(getIntent().getExtras().getString("name"));
        txtPhone.setText(getIntent().getExtras().getString("number"));
        txtEmail.setText(getIntent().getExtras().getString("email"));
        txtAddress.setText(getIntent().getExtras().getString("address"));
        txtUrl.setText(getIntent().getExtras().getString("url"));
    }

    /**
     * sets up the buttons, so they are ready.
     */
    private void setUpButtons() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editFriend();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall();
            }
        });
        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSmsActivity();
            }
        });
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        btnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHomePage();
            }
        });
    }

    /**
     * if the adding or editing is canceled
     */
    private void cancel() {
        //TODO: since we now use, start for activity, this need to return a CANCEL value or something like that
        finish();
    }

    /**
     * This is what happens, when the edit button is pressed
     */
    private void editFriend() {
        //TODO: make this return an UPDATED INTENT thing
        String name = txtName.getText().toString();
        int number = Integer.parseInt(txtPhone.getText().toString());
        String email = txtEmail.getText().toString();
        String address = txtAddress.getText().toString();
        String url = txtUrl.getText().toString();
        Friend friend = new Friend(name, number, email, address, url);
        position = Integer.parseInt(getIntent().getExtras().getString("position"));
        MockFriend.getInstance().update(friend);
    }

    /**
     * makes a call to the telephone
     */
    private void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + txtPhone.getText()));

        startActivity(callIntent);
    }

    /**
     * sends an sms
     */
    private void sendSmsActivity(){
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"+txtPhone.getText()));
        sendIntent.putExtra("sms_body", "This is a test...");
        startActivity(sendIntent);
    }

    /**
     * sends an email
     */
    private void sendEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        String[] recievers = {"oe@easv.dk"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recievers);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This is a test..");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "This is a test..");
        startActivity(emailIntent);
    }

    /**
     * opens a homepage.
     */
    private void openHomePage(){
        String url = "http://www." + txtUrl.getText();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(url));
        startActivity(browserIntent);
    }

}
