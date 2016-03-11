package com.example.jona9892.myfriendsapp.Model.Implement;

import java.util.ArrayList;

/**
 * Created by jona9892 on 09-03-2016.
 */
public class MockFriend {
    
    public ArrayList<Friend> getAll(){
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Jonathan", 12345678, "Jonathan@sighotel.dk", "Vardevej 30", null));
        friends.add(new Friend("Shaun", 12345678, "Shaun@sighotel.dk", "Shaunvej 30", null));
        friends.add(new Friend("Wardrylace", 12345678, "Wardrylace@sighotel.dk", "Vardevej 30", null));
        friends.add(new Friend("cool", 12345678, "Jonathan@sighotel.dk", "Vardevej 30", null));
        friends.add(new Friend("nice", 12345678, "Shaun@sighotel.dk", "Shaunvej 30", null));
        friends.add(new Friend("sassrylace", 12345678, "Wardrylace@sighotel.dk", "Vardevej 30", null));
        friends.add(new Friend("Far", 41289200, "Knude@sighotel.dk", "Vardevej 30", "Google.com"));
        return friends;
    }
}
