package com.pixburb.pixburbcommerce.controller;


import com.pixburb.pixburbcommerce.data.Response;
import com.pixburb.pixburbcommerce.data.WarehouseRequestData;
import com.pixburb.pixburbcommerce.services.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Resource
    private WarehouseService warehouseServiceImpl;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/create-warehouse")
    public ResponseEntity createWarehouse(@RequestBody WarehouseRequestData warehouseRequestData)
    {
        ResponseEntity responseEntity;
        Response responseBody = new Response();
        boolean response = warehouseServiceImpl.createWarehouse(warehouseRequestData);
        if(response)
        {
            responseBody.setStatus(HttpStatus.OK.value());
            responseBody.setErrorMessage(HttpStatus.OK.name());
            responseBody.setDisplayMessage("Warehouse created");
            responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
            return responseEntity;
        }
        responseBody.setStatus(HttpStatus.BAD_REQUEST.value());
        responseBody.setErrorMessage(HttpStatus.BAD_REQUEST.name());
        responseBody.setDisplayMessage("Warehouse Creation Failed");
        responseEntity = new ResponseEntity(responseBody, HttpStatus.OK);
        return responseEntity;
    }

}
