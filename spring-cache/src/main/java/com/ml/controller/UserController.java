package com.ml.controller;

import com.ml.models.User;
import com.ml.service.OtherService;
import com.ml.service.UserService;
import com.ml.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OtherService otherService;

    @GetMapping("/")
    public Page<User> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping("/v1/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/v2/{id}")
    public User findById2(@PathVariable Long id) {
        return userService.findById1(id);
    }

    @PostMapping("/")
    public User save(String name, Integer age) {
        return userService.save(name, age);
    }

    @PutMapping("/")
    public User update(UserVo userVo) {
        return userService.update(userVo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/o/v1/{id}")
    public User findOtherById(@PathVariable Long id) {
        return otherService.findById(id);
    }

    @GetMapping("/o/v2/{id}")
    public User findOtherById1(@PathVariable Long id) {
        return otherService.findById1(id);
    }

}
