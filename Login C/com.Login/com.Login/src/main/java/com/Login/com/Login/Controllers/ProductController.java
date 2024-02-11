package com.Login.com.Login.Controllers;

import com.Login.com.Login.Service.CategoryServices;
import com.Login.com.Login.Service.ProductServices;
import com.Login.com.Login.Wrapper.Productwrapper;
import com.Login.com.Login.Wrapper.productwrapperfordisplay;
import com.Login.com.Login.modules.Cart;
import com.Login.com.Login.modules.Category;
import com.Login.com.Login.modules.Product;
import com.Login.com.Login.utils.apputils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/product")
@RestController
public class ProductController {

    @Autowired
    ProductServices productServices;
// tis api by myself just
    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewProduct(@ModelAttribute Productwrapper requestMap){
        try {
            return productServices.addNewProduct(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in controller", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping(path = "/get")
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            return productServices.getAllProducts();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/show")
    public ResponseEntity<productwrapperfordisplay> showProduct(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return productServices.showProduct(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new productwrapperfordisplay(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<String> updateNewProduct(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return productServices.updateNewProduct(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in controller", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<String> deleteNewProduct(@RequestBody(required = true) Map<String,String> requestMap){
        try {
            return productServices.deleteNewProduct(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in controller", HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
