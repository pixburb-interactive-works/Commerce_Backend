package com.pixburb.pixburbcommerce.controller;


import com.pixburb.pixburbcommerce.data.Response;
import com.pixburb.pixburbcommerce.data.RoleData;
import com.pixburb.pixburbcommerce.services.RoleService;
import com.pixburb.pixburbcommerce.services.impl.RoleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RoleController {

    private static final String ROLE_URL = "roles";

    @Resource
    private RoleService roleServiceImpl;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = ROLE_URL+"/create")
    public ResponseEntity createUser(@RequestBody RoleData roleData, final HttpServletRequest httpServletRequest,
                                     final HttpServletResponse httpServletResponse) {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = roleServiceImpl.createRole(roleData);
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Role created");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Role Creation Failed");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = ROLE_URL+"/remove")
    public ResponseEntity removeRole(@RequestBody RoleData roleData, final HttpServletRequest httpServletRequest,
                                     final HttpServletResponse httpServletResponse)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = roleServiceImpl.removeRole(roleData.getRoleName());
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Role Removed");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Role Removal Failed");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }


}
