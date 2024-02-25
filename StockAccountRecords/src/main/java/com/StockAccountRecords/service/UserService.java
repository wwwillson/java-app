package com.StockAccountRecords.service;

import com.StockAccountRecords.entity.User;

public interface UserService {
    User findByUsername(String username);

    void saveUser(User user);
}