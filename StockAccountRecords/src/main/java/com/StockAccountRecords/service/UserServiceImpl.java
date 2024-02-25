package com.StockAccountRecords.service;

import com.StockAccountRecords.dao.UserRepository;
import com.StockAccountRecords.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl (UserRepository theUserRepository){
        this.userRepository = theUserRepository;
    }

    @Override
    public User findByUsername(String username) {
        return (User) this.userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.save(user);
    }
}