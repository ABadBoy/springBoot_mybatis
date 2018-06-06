package com.badboy.controller;

import com.badboy.mapper.UserMapper;
import com.badboy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public void add(@RequestBody User user) {
        userMapper.insert(user);
    }

    @RequestMapping(value = "/findAllUsers",method = RequestMethod.GET)
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @RequestMapping(value = "user/find/{name}",method = RequestMethod.GET)
    public User findByName(@PathVariable("name") String name) {
        return userMapper.findByName(name);
    }


}
