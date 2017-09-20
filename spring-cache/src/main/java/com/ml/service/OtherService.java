package com.ml.service;

import com.ml.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtherService {

    @Autowired
    private UserService userService;

    public User findById(Long id) {
        User user = userService.findById(id);
        return user;
    }

    public User findById1(Long id) {
        return findById(id);
    }
}
