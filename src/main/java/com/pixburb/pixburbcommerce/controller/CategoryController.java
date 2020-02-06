package com.pixburb.pixburbcommerce.controller;

import com.pixburb.pixburbcommerce.data.CategoryRequestData;
import com.pixburb.pixburbcommerce.data.Response;
import com.pixburb.pixburbcommerce.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryServiceImpl;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/create-category")
    public ResponseEntity createCategory(@RequestBody CategoryRequestData categoryRequestData)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = categoryServiceImpl.createCategory(categoryRequestData);
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Category created");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Category Creation Failed");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

}
