package com.pixburb.pixburbcommerce.controller;

import com.pixburb.pixburbcommerce.data.*;

import com.pixburb.pixburbcommerce.services.UserService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {



    @Resource
    private UserService userServiceImpl;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity login(@RequestBody Login login, final HttpServletRequest httpServletRequest,
                                final HttpServletResponse httpServletResponse)
    {

        ResponseEntity responseEntity;
        Response responseBody = new Response();
        String response = userServiceImpl.login(login);
        if(response!=null)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Login accepted");
            responseBody.setData(Collections.singletonList(response));
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
    @RequestMapping(method = RequestMethod.POST, path = "/createAdmin")
    public ResponseEntity createAdminUser(@RequestBody UserRequestData userRequestData, final HttpServletRequest httpServletRequest,
                                          final HttpServletResponse httpServletResponse)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = userServiceImpl.createUserRequest(userRequestData);
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
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity createUser(@RequestBody UserRequestData userRequestData, final HttpServletRequest httpServletRequest,
                                     final HttpServletResponse httpServletResponse) {

        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = userServiceImpl.createUser(userRequestData);
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
    @RequestMapping(method = RequestMethod.POST, path = "/org-users")
    public ResponseEntity viewUsersByOrg(@RequestParam("organization")String organization, final HttpServletRequest httpServletRequest,
                                     final HttpServletResponse httpServletResponse) {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        List<UserRequestData> userRequestDataList = userServiceImpl.viewAllUsersByOrganization(organization);
        if(userRequestDataList != null)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("");
            responseBody.setData(Collections.singletonList(userRequestDataList));
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Something went wrong");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/findUser")
    public ResponseEntity findUser(@RequestParam("email") String email)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        UserResponseData userResponseData = userServiceImpl.findUser(email);
        if(userResponseData != null)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("");
            responseBody.setData(Collections.singletonList(userResponseData));
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Something went wrong");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public ResponseEntity updateUser(@RequestBody UserRequestData userRequestData, final HttpServletRequest httpServletRequest,
                                     final HttpServletResponse httpServletResponse)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = userServiceImpl.updateUser(userRequestData);
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("User updated");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("User Updation Failed");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/deactivate")
    public ResponseEntity deactivateUser(@RequestParam("email") String email)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean state = userServiceImpl.deactivateUser(email);
        if(state)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("User Deactivated");
            responseBody.setData(null);
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Something went wrong");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/verify")
    public ResponseEntity verifyUser(@RequestBody UserVerificationData userVerificationData, final HttpServletRequest httpServletRequest,
                                     final HttpServletResponse httpServletResponse)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = userServiceImpl.verifyUserRequest(userVerificationData.getEmail(), userVerificationData.getOtp());
        if(response)
        {
            //creating response body
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
