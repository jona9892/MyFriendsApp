package com.example.jona9892.myfriendsapp.Model.Implement;

import com.example.jona9892.myfriendsapp.Model.Abstraction.IFriendLog;

import java.util.ArrayList;

/**
 * Created by jona9892 on 09-03-2016.
 */
public class FriendLog implements IFriendLog {
    private static FriendLog instance;
    private MockFriend mf;
    private ArrayList<Friend> friends;

    /**
     * Singleton
     * @return the single instance that will ever be made.
     */
    public static FriendLog getInstance(){
        if(instance == null){
            instance = new FriendLog();
        }
        return instance;
    }

    private FriendLog(){
        mf = new MockFriend();
        friends = new ArrayList<>();
    }

    @Override
    public ArrayList<Friend> getAll() {
        friends = mf.getAll();
        return friends;
    }

    @Override
    public void add(Friend friend) {
        friends.add(friend);
    }

    @Override
    public void update(int position, Friend friend) {
        friends.set(position, friend);
    }
}
