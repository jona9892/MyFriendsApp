package com.example.jona9892.myfriendsapp.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jona9892.myfriendsapp.Model.Abstraction.ICrud;
import com.example.jona9892.myfriendsapp.Model.Implement.Friend;
import com.example.jona9892.myfriendsapp.Model.Implement.MockFriend;
import com.example.jona9892.myfriendsapp.R;

import java.util.ArrayList;
import java.util.Collection;

public class FriendsActivity extends Activity {

    FriendAdapter friendAdapter;

    ListView lstFriends;
    Button btnAdd;


    private ICrud<Friend> friendDb;

    public static String FRIEND_TAG = "fr tag";

    private final int ADD_REQUEST_CODE = 1;
    private final int EDIT_REQUEST_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_);
        getWidgets();
        setUpList();
        setUpButtons();

        //----------------Should be recieved from the savedInstanceState-----------
        friendDb = MockFriend.getInstance();

        //---------------------------------------------------------------------------

        setAdapter();
        back();


    }

    /**
     * sets the adapter, of the list view
     */
    private void setAdapter() {
        friendAdapter = new FriendAdapter(this, R.layout.friend_cell, (ArrayList<Friend>) friendDb.readAll());
        lstFriends.setAdapter(friendAdapter);
    }

    /**
     * When going back to mainActivity, will set the Result to ok
     */
    private void back(){
        setResult(RESULT_OK);
    }

    /**
     * Gets all the widget.
     */
    private void getWidgets(){
        lstFriends = (ListView)findViewById(R.id.lstFriends);
        btnAdd = (Button)findViewById(R.id.btnAdd);
    }

    /**
     * sets up the list.
     */
    private void setUpList(){
        lstFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick((ListView) parent, view, position, id);

            }
        });
    }

    /**
     * sets up the buttons.
     */
    private void setUpButtons(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    /**
     * This will open an activity, to add a friend
     */
    private void add(){
        //TODO: should send an empty intend to an activity, we should use the start for result
        Intent intent = new Intent();
        intent.setClass(this, EditFriendActivity.class);
        startActivityForResult(intent, ADD_REQUEST_CODE);
    }

    /**
     * the method that will be called when the started activity returns
     * @param requestCode the code of the activity
     * @param resultCode how the action went
     * @param data the information
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case ADD_REQUEST_CODE:
                if(resultCode == RESULT_OK) {
                    addItemToDB((Friend) data.getSerializableExtra(FRIEND_TAG));
                    setAdapter();
                }
                else {
                    //Find out what to do.
                }
                break;
            case EDIT_REQUEST_CODE:
                if(resultCode == RESULT_OK) {
                    editItemInDB((Friend) data.getSerializableExtra(FRIEND_TAG));
                    setAdapter();
                }
                else {
                    //Find out what to do.
                }
                break;
        }

    }

    /**
     * Edits a friend in the database
     * @param theFriend the friend to be edited.
     */
    private void editItemInDB(Friend theFriend) {
        friendDb.update(theFriend);
    }

    /**
     * Will add a friend to the database.
     * @param theFriend the friend to be added.
     */
    private void addItemToDB(Friend theFriend) {
        friendDb.add(theFriend);
    }

    public void onClick(ListView parent,
                                View v, int position, long id) {

        ArrayList<Friend> col = (ArrayList<Friend>) friendDb.readAll();
        String name = col.get(position).getName();
        int phoneNumber = col.get(position).getPhoneNumber();
        String email = col.get(position).getEmail();
        String address = col.get(position).getAddress();
        String url = col.get(position).getUrl();

        Intent intent = new Intent();
        intent.setClass(this, EditFriendActivity.class);
        //TODO: We need the tag to be a constant.
        intent.putExtra(FRIEND_TAG,col.get(position));

        startActivityForResult(intent, EDIT_REQUEST_CODE);

    }

    public static class ViewHolder {
        public TextView name;

    }

    class FriendAdapter extends ArrayAdapter<Friend>
    {
        public FriendAdapter(Context context, int resource, ArrayList<Friend> friends) {
            super(context, resource, friends);
        }

        //TODO: we need to fix so that this only adds one.
        @Override
        public View getView(int position, View v, ViewGroup parent) {
            ViewHolder holder;
            if (v == null) {
                LayoutInflater li = (LayoutInflater) getContext().getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.friend_cell, null);
                Log.d("LIST", "Position: " + position + " View created");

                holder = new ViewHolder();
                holder.name = (TextView) v.findViewById(R.id.txtName);
                v.setTag(holder);

            }
            else
                Log.d("LIST", "Position: " + position + " View Reused");
                holder = (ViewHolder) v.getTag();

            v.setMinimumHeight(150);

            Friend friend = getItem(position);
            holder.name.setText(friend.getName());

            return v;
        }
    }
}
