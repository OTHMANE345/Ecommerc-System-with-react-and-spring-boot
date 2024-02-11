package com.Login.com.Login.Controllers;

import com.Login.com.Login.Service.CartServices;
import com.Login.com.Login.Service.CategoryServices;
import com.Login.com.Login.modules.Cart;
import com.Login.com.Login.modules.Category;
import com.Login.com.Login.utils.apputils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/cart")
@RestController
public class CartController {

    @Autowired
    CartServices cartServices;
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return cartServices.addNewCart(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @PostMapping(path = "/get")
    public ResponseEntity<List<Cart>> getAllCart(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return cartServices.getAllCart(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<String> deleteNewCart(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return cartServices.deleteNewCart(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in controller", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
