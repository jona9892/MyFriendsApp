package com.example.jona9892.myfriendsapp.Controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jona9892.myfriendsapp.Model.Implement.Friend;
import com.example.jona9892.myfriendsapp.R;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EditFriendActivity extends AppCompatActivity {
    //-----------Views----------
    EditText txtName;
    EditText txtPhone;
    EditText txtEmail;
    EditText txtAddress;
    EditText txtUrl;
    ImageView imgPicture;
    //---------Buttons--------
    Button btnCancel;
    Button btnSave;
    Button btnCall;
    Button btnSMS;
    Button btnEmail;
    Button btnHomePage;

    ActivityType theType;
    private Friend theFriend;


    File file;
    String fileName;

    private enum MediaType { IMAGE}

    private final int CAMERA_REQUEST_CODE = 0;

    public static String TAG = "edit fr tag";

    private enum ActivityType {
        ADD, EDIT
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__edit_);

        //---------------This should be gotten from the savedinstancestate
        //theType;
        //theFriend;
        //------------------------
        getWidgets();
        setUpImgPicture();

        decideType();

        setUpButtons();

    }

    //TODO: we need to send a friend to the location activity.

    /**
     * sets up the imagepicture
     */
    private void setUpImgPicture() {
        imgPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePicture();
            }
        });
    }

    /**
     * starts an intent, to start the picture.
     */
    private void updatePicture() {

        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        file = getOutputPhotoFile(MediaType.IMAGE);
        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));

        startActivityForResult(pictureIntent, CAMERA_REQUEST_CODE);
    }

    /**
     * This will get file from the picture, and set the file info
     * @param image sets the media type to image
     * @return the file
     */
    private File getOutputPhotoFile(MediaType image) {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");

        if (!mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d(TAG,"failed to create directory");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String postfix = image == MediaType.IMAGE ? "jpg" : "mp4";
        String prefix = image == MediaType.IMAGE ? "IMG" : "VID";

        File mediaFile = new File(mediaStorageDir.getPath() +
                File.separator + prefix +
                "_"+ timeStamp + "." + postfix);

        return mediaFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK){
            fileName = file.toString();
            theFriend.setFilePath(fileName);
            showPictureTaken(theFriend.getFilePath(), imgPicture);

            if(!file.exists()){
                Log.d("PICTURE", "THE FILE DOESN'T EXIST");
            }
        }

    }

    /**
     * This will show the picture taken in a imageview
     * @param filename thie filename of the picture
     * @param myImage the imageview that the picture should be shown in
     */
    private void showPictureTaken(String filename, ImageView myImage) {

        File f = new File(filename);
        if (!f.exists()) {
            return;
        }
        myImage.setImageURI(Uri.fromFile(f));
        myImage.setRotation(270);
        myImage.setBackgroundColor(Color.BLACK);
        scaleImage(myImage);
    }

    /**
     * This will scale the image
     */
    private void scaleImage(ImageView myImage)
    {
        final Display display = getWindowManager().getDefaultDisplay();
        Point p = new Point();
        display.getSize(p);
        myImage.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    /**
     * finds out wether this is to edit or add.
     */
    private void decideType() {
        if (isEdit()) {
            theType = ActivityType.EDIT;
            theFriend = (Friend) getIntent().getSerializableExtra(FriendsActivity.FRIEND_TAG);
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
        return getIntent().getSerializableExtra(FriendsActivity.FRIEND_TAG) != null;
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
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

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
        txtName.setText(theFriend.getName() != null ? theFriend.getName().toString() : "");
        txtPhone.setText("" + theFriend.getPhoneNumber());
        txtEmail.setText(theFriend.getEmail() != null ? theFriend.getEmail().toString() : "");
        txtAddress.setText(theFriend.getAddress() != null ? theFriend.getAddress().toString() : "" );
        txtUrl.setText(theFriend.getUrl()!= null ? theFriend.getUrl().toString() : "");
        showPictureTaken(theFriend.getFilePath() != null ? theFriend.getFilePath(): "", imgPicture);
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
        setResult(Activity.RESULT_CANCELED);
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
        Friend friend = new Friend(name, number, email, address, url, fileName);

        Intent intent = new Intent();
        intent.putExtra(FriendsActivity.FRIEND_TAG,friend);
        setResult(RESULT_OK, intent);
        finish();
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
