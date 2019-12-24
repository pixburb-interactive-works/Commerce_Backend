package com.pixburb.pixburbcommerce.controller;


import com.pixburb.pixburbcommerce.data.OrganizationData;
import com.pixburb.pixburbcommerce.data.Response;
import com.pixburb.pixburbcommerce.services.OrganizatonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@RestController
public class OrganizationController {

    private static final String ORG_URL = "organization";

    @Resource
    private OrganizatonService organizationServiceImpl;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = ORG_URL +"/create")
    public ResponseEntity create(@RequestBody OrganizationData organizationData, final HttpServletRequest httpServletRequest,
                                 final HttpServletResponse httpServletResponse)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();


        //check cookie for id
        Optional<String> presentCookie = readCookie("userId", httpServletRequest);

        if(presentCookie.isPresent())
        {
            organizationData.setCreatedBy(presentCookie.get());
        }


        boolean response = organizationServiceImpl.createOrganization(organizationData);
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Organization Created");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Organization Not Created");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

    private Optional<String> readCookie(String key, HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
