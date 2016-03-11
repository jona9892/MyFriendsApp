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
    ICrud<Friend> friendLog;

    ListView lstFriends;
    Button btnAdd;

    String name;
    int phoneNumber;
    String email;
    String address;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_);
        getWidgets();
        setUpList();
        setUpButtons();

        friendLog = MockFriend.getInstance();
        friendAdapter = new FriendAdapter(this, R.layout.friend_cell, (Friend[]) MockFriend.getInstance().readAll().toArray());
        lstFriends.setAdapter(friendAdapter);
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
        intent.setClass(this, AddFriendActivity.class);
        startActivity(intent);
    }

    public void onClick(ListView parent,
                                View v, int position, long id) {

        Collection col = friendLog.readAll();
        Friend[] friends = (Friend[]) col.toArray();
        name = friends[position].getName();
        phoneNumber = friends[position].getPhoneNumber();
        email = friends[position].getEmail();
        address = friends[position].getAddress();
        url = friends[position].getUrl();

        Intent intent = new Intent();
        intent.setClass(this, EditFriendActivity.class);
        //TODO: We need the tag to be a constant.
        intent.putExtra("Friend",friends[position]);

        startActivity(intent);

    }

    public static class ViewHolder {
        public TextView name;

    }

    class FriendAdapter extends ArrayAdapter<Friend>
    {

        public FriendAdapter(Context context, int resource, Friend[] friends) {
            super(context, resource, friends);
        }

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
