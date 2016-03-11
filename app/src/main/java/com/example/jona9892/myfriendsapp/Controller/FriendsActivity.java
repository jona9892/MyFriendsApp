package com.example.jona9892.myfriendsapp.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Toast;

import com.example.jona9892.myfriendsapp.Model.Abstraction.IFriendLog;
import com.example.jona9892.myfriendsapp.Model.Implement.Friend;
import com.example.jona9892.myfriendsapp.Model.Implement.FriendLog;
import com.example.jona9892.myfriendsapp.R;

import java.util.ArrayList;

public class FriendsActivity extends Activity {
    FriendAdapter friendAdapter;
    IFriendLog friendLog;

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

        friendLog = FriendLog.getInstance();
        friendAdapter = new FriendAdapter(this, R.layout.friend_cell, friendLog.getAll());
        lstFriends.setAdapter(friendAdapter);
    }

    private void getWidgets(){
        lstFriends = (ListView)findViewById(R.id.lstFriends);
        btnAdd = (Button)findViewById(R.id.btnAdd);
    }
    private void setUpList(){
        lstFriends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick((ListView) parent, view, position, id);
            }
        });
    }

    private void setUpButtons(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    private void add(){
        Intent intent = new Intent();
        intent.setClass(this, AddFriendActivity.class);
        startActivity(intent);
    }

    public void onClick(ListView parent,
                                View v, int position, long id) {
        Toast.makeText(this, friendLog.getAll().get(position).getName() + "is clicked!!",
                Toast.LENGTH_LONG).show();

        name = friendLog.getAll().get(position).getName();
        phoneNumber = friendLog.getAll().get(position).getPhoneNumber();
        email = friendLog.getAll().get(position).getEmail();
        address = friendLog.getAll().get(position).getAddress();
        url = friendLog.getAll().get(position).getUrl();



        Intent intent = new Intent();
        intent.setClass(this, EditFriendActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("number", String.valueOf(phoneNumber));
        intent.putExtra("email", email);
        intent.putExtra("address", address);
        intent.putExtra("url", url);
        intent.putExtra("position", position);

        startActivity(intent);

    }


    @Override
    public void onResume() {
        super.onResume();
        //fa.notifyDataSetChanged();
    }


    public static class ViewHolder {
        public TextView name;

    }

    class FriendAdapter extends ArrayAdapter<Friend>
    {

        public FriendAdapter(Context context, int resource, ArrayList<Friend> friends) {
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
