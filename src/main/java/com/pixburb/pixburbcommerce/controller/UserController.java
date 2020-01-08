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
import javax.servlet.http.Cookie;
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
    @RequestMapping(method = RequestMethod.POST, path = "/createAdmin")
    public ResponseEntity createAdminUser(@RequestBody UserData userData, final HttpServletRequest httpServletRequest,
                                          final HttpServletResponse httpServletResponse)
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
    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity createUser(@RequestBody UserData userData, final HttpServletRequest httpServletRequest,
                                          final HttpServletResponse httpServletResponse) {

        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = userServiceImpl.createUser(userData);
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
        List<UserData> userDataList = userServiceImpl.viewAllUsersByOrganization(organization);
        if(userDataList != null)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("");
            responseBody.setData(Collections.singletonList(userDataList));
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
        UserData userDataList = userServiceImpl.findUser(email);
        if(userDataList != null)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("");
            responseBody.setData(Collections.singletonList(userDataList));
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
            //remove cookie if present
            Cookie userNameCookieRemove = new Cookie("userId", "");
            userNameCookieRemove.setMaxAge(0);
            userNameCookieRemove.setDomain("localhost");
            userNameCookieRemove.setPath("/");
            httpServletResponse.addCookie(userNameCookieRemove);

            //create cookie
            Cookie userNameCookieCreate = new Cookie("userId", userVerificationData.getEmail());
            userNameCookieCreate.setMaxAge(60*15);
            userNameCookieCreate.setDomain("localhost");
            userNameCookieCreate.setPath("/");
            httpServletResponse.addCookie(userNameCookieCreate);

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
