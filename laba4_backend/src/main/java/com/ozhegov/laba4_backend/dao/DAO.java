package com.ozhegov.laba4_backend.dao;

import com.ozhegov.laba4_backend.exceptions.UserAlreadyExistsException;
import com.ozhegov.laba4_backend.model.User;

import java.util.List;

public interface DAO<T>{
    long create(T t) throws UserAlreadyExistsException;
    List<T> getAll(User userId);
    T get(long id);
}
