package com.Login.com.Login.Service;

import com.Login.com.Login.JWT.CustomerUserDetailsSErvice;
import com.Login.com.Login.JWT.JwtFilter;
import com.Login.com.Login.JWT.JwtUtil;
import com.Login.com.Login.Repositories.CategoryRepository;
import com.Login.com.Login.Repositories.UserRepository;
import com.Login.com.Login.modules.Category;
import com.Login.com.Login.modules.User;
import com.Login.com.Login.utils.apputils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class CategoryServices {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    JwtFilter  jwtFilter;



    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
           if(jwtFilter.isAdmin()){
               if(validateCategoryMap(requestMap, false)){
                 categoryRepository.save(getCategoryFromMap(requestMap)) ;
                   return apputils.getResponseEntity("Category addes succefully", HttpStatus.OK);

               }
           } else {
               return apputils.getResponseEntity("Unouthorized access", HttpStatus.UNAUTHORIZED);

           }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateCategoryMap(Map<String, String> requestMap , boolean validateid) {
        if (requestMap.containsKey("name")) {
            if(validateid){
                return true;
            } else if (!validateid) {
                return  true;
            }
        }
            return false;


    }

    private Category getCategoryFromMap(Map<String, String> requestMap){
        Category category = new Category();
        category.setName(requestMap.get("name"));

        return category;

    }

    public ResponseEntity<List<Category>> getAllCategory() {
        Map<String, Object> object = new HashMap<>();
        ///here i use bard ai but i write the code by myslef

        List<Category> categories = new ArrayList<>();

        for (Category category : categoryRepository.getAllCategory()){
            categories.add(category);
        }
        ///
        object.put("categories", categories);

        ObjectMapper mapper = new ObjectMapper();


            return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    public ResponseEntity<String> deleteNewCategory(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()){


                categoryRepository.deleteById(Integer.valueOf(requestMap.get("id"))); ;
                return apputils.getResponseEntity("Category deletes succefully", HttpStatus.OK);


            } else {
                return apputils.getResponseEntity("Unouthorized access", HttpStatus.UNAUTHORIZED);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in services", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
