package com.pixburb.pixburbcommerce.controller;

import com.pixburb.pixburbcommerce.data.Login;

import com.pixburb.pixburbcommerce.data.Response;
import com.pixburb.pixburbcommerce.services.UserService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {


    @Resource
    private UserService userServiceImpl;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity login(@RequestBody Login login)
    {

        Response responseBody = new Response();;
        Boolean response = userServiceImpl.login(login.getEmail(), login.getPassword());
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("login accepted");
            return new ResponseEntity(responseBody, HttpStatus.OK);
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("invalid user id/password");
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }
}
