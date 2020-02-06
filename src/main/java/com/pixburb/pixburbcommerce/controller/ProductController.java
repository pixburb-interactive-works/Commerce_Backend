package com.pixburb.pixburbcommerce.controller;

import com.pixburb.pixburbcommerce.data.CategoryRequestData;
import com.pixburb.pixburbcommerce.data.ProductRequestData;
import com.pixburb.pixburbcommerce.data.Response;
import com.pixburb.pixburbcommerce.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productServiceImpl;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/create-product")
    public ResponseEntity createProduct(@RequestBody ProductRequestData productRequestData)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = productServiceImpl.createProduct(productRequestData);
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Product created");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Product Creation Failed");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }
}
