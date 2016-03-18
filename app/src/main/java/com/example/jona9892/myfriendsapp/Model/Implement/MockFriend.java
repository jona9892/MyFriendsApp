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
        friends.put(Friend.IDENTITY, new Friend("Jonathan", 12345678, "Jonathan@sighotel.dk", "Vardevej 30", null));
        friends.put(Friend.IDENTITY, new Friend("Shaun", 12345678, "Shaun@sighotel.dk", "Shaunvej 30", null));
        friends.put(Friend.IDENTITY, new Friend("Wardrylace", 12345678, "Wardrylace@sighotel.dk", "Vardevej 30", null));
        friends.put(Friend.IDENTITY, new Friend("cool", 12345678, "Jonathan@sighotel.dk", "Vardevej 30", null));
        friends.put(Friend.IDENTITY, new Friend("nice", 12345678, "Shaun@sighotel.dk", "Shaunvej 30", null));
        friends.put(Friend.IDENTITY, new Friend("sassrylace", 12345678, "Wardrylace@sighotel.dk", "Vardevej 30", null));
        friends.put(Friend.IDENTITY, new Friend("Far", 41289200, "Knude@sighotel.dk", "Vardevej 30", "Google.com"));
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
