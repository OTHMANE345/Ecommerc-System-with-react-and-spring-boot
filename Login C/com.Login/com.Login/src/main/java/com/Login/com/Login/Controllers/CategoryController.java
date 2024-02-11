package com.Login.com.Login.Controllers;

import com.Login.com.Login.Service.CategoryServices;
import com.Login.com.Login.Service.UserServices;
import com.Login.com.Login.modules.Category;
import com.Login.com.Login.utils.apputils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/category")
@RestController
public class CategoryController {

    @Autowired
    CategoryServices categoryServices;
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return categoryServices.addNewCategory(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(path = "/get")
    public ResponseEntity<List<Category>> getAllCategory(){
        try {
            return categoryServices.getAllCategory();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<String> deleteNewCategory(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return categoryServices.deleteNewCategory(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in controller", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
