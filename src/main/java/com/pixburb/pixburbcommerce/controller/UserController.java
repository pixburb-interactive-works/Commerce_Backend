package com.pixburb.pixburbcommerce.controller;

import com.pixburb.pixburbcommerce.data.Login;

import com.pixburb.pixburbcommerce.services.UserService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {


    @Resource
    private UserService userServiceImpl;

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity login(@RequestBody Login login)
    {

        Boolean response = userServiceImpl.login(login.getEmail(), login.getPassword());
        if(response)
        {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
