package com.example.jona9892.myfriendsapp.DALC.Abstraction;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Max on 11-03-2016.
 */
public interface ICrud<T> extends Serializable{
    T add(T item);
    T read(int id);
    Collection<T> readAll();
    void delete(int id);
    T update(T item);
}
