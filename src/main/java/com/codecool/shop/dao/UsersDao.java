package com.codecool.shop.dao;

import com.codecool.shop.dao.UsersDao;


public interface UsersDao {
    public String getPasswordForUser(String username);

    boolean checkUserNameIsUsed(String userName);
    boolean checkEmailIsUsed(String email);
    void registerUser(String userName, String hash, String email);
}
