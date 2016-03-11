package com.example.jona9892.myfriendsapp.Model.Abstraction;

import com.example.jona9892.myfriendsapp.Model.Implement.Friend;

import java.util.ArrayList;

/**
 * Created by jona9892 on 09-03-2016.
 */
public interface IFriendLog {
    ArrayList<Friend> getAll();
    void add(Friend friend);
    void update(int position, Friend friend);
}
