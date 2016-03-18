package com.example.jona9892.myfriendsapp.Controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jona9892.myfriendsapp.Model.Implement.Friend;
import com.example.jona9892.myfriendsapp.R;

import java.io.File;
import java.net.URI;
import java.text.SimpleDateFormat;
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

    int position;
    ActivityType theType;
    private Friend theFriend;

    private final int CAMERA_REQUEST_CODE = 0;

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
        String imagePath;

        imagePath = Environment.getExternalStorageState() + "/images/myimage.jpg";
        File file = new File( imagePath );
        Uri outputFileUri = Uri.fromFile( file );

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra( MediaStore.EXTRA_OUTPUT, outputFileUri );
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    private File getOutputPhotoFile() {
        File directory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), getPackageName());
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                Log.e("PICTURE", "Failed to create storage directory.");
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss").format(new Date());
        return new File(directory.getPath() + File.separator + "IMG_"
                + timeStamp + ".jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(resultCode == RESULT_OK){

            Uri photoUri = data.getData();
            File file = new File(photoUri.toString());
            if(!file.exists()){
                Log.d("PICTURE", "THE FILE DOESN'T EXIST");
            }
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            imgPicture.setImageBitmap(bitmap);
        }

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
        Friend friend = new Friend(name, number, email, address, url);
        //position = Integer.parseInt(getIntent().getExtras().getString("position"));

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
