package com.pixburb.pixburbcommerce.controller;

import com.pixburb.pixburbcommerce.data.Login;

import com.pixburb.pixburbcommerce.data.Response;
import com.pixburb.pixburbcommerce.data.UserData;
import com.pixburb.pixburbcommerce.data.UserVerificationData;
import com.pixburb.pixburbcommerce.services.UserService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    private static final String USER_URL = "users";

    @Resource
    private UserService userServiceImpl;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = USER_URL+"/login")
    public ResponseEntity login(@RequestBody Login login)
    {

        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = userServiceImpl.login(login.getEmail(), login.getPassword());
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Login accepted");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Invalid user-id/password");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = USER_URL+"/create")
    public ResponseEntity createUser(@RequestBody UserData userData)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = userServiceImpl.createUserRequest(userData);
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("User created");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("User Creation Failed");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = USER_URL+"/verify")
    public ResponseEntity verifyUser(@RequestBody UserVerificationData userVerificationData)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = userServiceImpl.verifyUserRequest(userVerificationData.getEmail(), userVerificationData.getOtp());
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Account Verified");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Verification Unsuccessful");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }


}
