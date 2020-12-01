package com.consagrado.consagradoapp.DAO;

import android.content.Context;

import java.util.Collection;

public interface DAO<T>{
    public boolean insert(T data) throws ClassNotFoundException;
    public boolean alter(T data) throws ClassNotFoundException;
    public boolean delete(T data) throws ClassNotFoundException;
    public boolean get(T data) throws ClassNotFoundException;
    public Collection<T> list(String rule) throws ClassNotFoundException;
}
