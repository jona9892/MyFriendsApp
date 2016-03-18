package com.example.jona9892.myfriendsapp.Model.Implement;

import com.example.jona9892.myfriendsapp.Model.Abstraction.ICrud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by jona9892 on 09-03-2016.
 */
public class MockFriend implements ICrud<Friend>{
    private HashMap<Integer,Friend> friends;
    private static int ID = 1;
    private static MockFriend instance;

    public static MockFriend getInstance(){
        if(instance == null)
            instance = new MockFriend();
        return instance;
    }

    private MockFriend(){
        friends= new HashMap<>();
        friends.put(Friend.IDENTITY, new Friend("Jonathan", 41289203, "Jonathangjoel@hotmail.dk", "Lerpøtparken 12", null, null));
        friends.put(Friend.IDENTITY, new Friend("Max", 12345678, "max@hotmail.dk", "I dont know", null, null));
        friends.put(Friend.IDENTITY, new Friend("Søren", 12345678, "Søren@hotmail.dk", "stupidvej 30", null,null));
        friends.put(Friend.IDENTITY, new Friend("Mads", 12345678, "Mads@hotmail.dk", "wuut 30", null, null));
        friends.put(Friend.IDENTITY, new Friend("Ole", 12345678, "Ole@hotmail.dk", "Shaunvej 30", null,null));
    }

    @Override
    public Friend add(Friend item) {
        friends.put(ID++, item);
        return item;
    }

    @Override
    public Friend read(int id) {
        return friends.get(id);
    }

    @Override
    public Collection<Friend> readAll() {
        ArrayList<Friend> result = new ArrayList<>();
        for(Integer id : friends.keySet()){
            result.add(friends.get(id));
        }

        return result;
    }

    @Override
    public void delete(int id) {
        friends.remove(id);
    }

    @Override
    public Friend update(Friend item) {
        friends.remove(item.getId());
        friends.put(item.getId(),item);
        return item;
    }
}
