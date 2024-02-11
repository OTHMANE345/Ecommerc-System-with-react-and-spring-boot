package com.Login.com.Login.Controllers;

import com.Login.com.Login.Service.CategoryServices;
import com.Login.com.Login.Service.OrderServices;
import com.Login.com.Login.modules.Category;
import com.Login.com.Login.modules.Order;
import com.Login.com.Login.utils.apputils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/order")
@RestController
public class OrderController {
    @Autowired
    OrderServices orderServices;
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewOrder(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return orderServices.addNewOrder(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<Order>> getAllOrders(){
        try {
            return orderServices.getAllOrders();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<String> deleteNewOrder(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return orderServices.deleteNewOrder(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in controller", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
