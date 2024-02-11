package com.Login.com.Login.Service;

import com.Login.com.Login.JWT.JwtFilter;
import com.Login.com.Login.Repositories.CategoryRepository;
import com.Login.com.Login.Repositories.ProductRepository;
import com.Login.com.Login.Wrapper.Productwrapper;
import com.Login.com.Login.Wrapper.productwrapperfordisplay;
import com.Login.com.Login.modules.Category;
import com.Login.com.Login.modules.Product;
import com.Login.com.Login.utils.apputils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
@Slf4j
@Service
public class ProductServices {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    JwtFilter jwtFilter;

    Logger logger = Logger.getLogger("my.app");

    public ResponseEntity<String> addNewProduct(Productwrapper requestMap) {
        try {
            if(jwtFilter.isAdmin()){
                productRepository.save(getProductFromMap(requestMap)) ;
                    return apputils.getResponseEntity("Product addes succefully", HttpStatus.OK);
            } else {
                return apputils.getResponseEntity("Unouthorized access", HttpStatus.UNAUTHORIZED);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in services", HttpStatus.INTERNAL_SERVER_ERROR);

    }




    private Product getProductFromMap(Productwrapper requestMap) throws IOException {
        Product product = new Product();
        product.setName(requestMap.getName());
        product.setPrice(requestMap.getPrice());
        product.setDescription(requestMap.getDescription());
        Category category = new Category();
        category.setId(Integer.valueOf(requestMap.getCategory_id()));
        product.setCategory(category);
        product.setImage(Base64.getEncoder().encodeToString(requestMap.getImage().getBytes()));
        return product;
    }





    public ResponseEntity<List<Product>> getAllProducts() {
     List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);

    }




    public ResponseEntity<productwrapperfordisplay> showProduct(Map<String, String> requestMap) {
        try {
            String id = requestMap.get("id");
            Product product = productRepository.findById(Integer.valueOf(id)).get();
            productwrapperfordisplay productwrapper = new productwrapperfordisplay();
            productwrapper.setCategoryName(product.getCategory().getName());
            productwrapper.setName(product.getName());
            productwrapper.setPrice(product.getPrice());
            productwrapper.setDescription(product.getDescription());
            productwrapper.setCategory_id(product.getCategory().getId());
            productwrapper.setImage(product.getImage());
            productwrapper.setId(Integer.valueOf(id));
            return new ResponseEntity<>(productwrapper, HttpStatus.OK);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new productwrapperfordisplay(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> updateNewProduct(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()){
                String id = requestMap.get("id");
                logger.info(id);
                Optional<Product> product = productRepository.findById(Integer.valueOf(id));
                if(!product.isEmpty()){
                    logger.info("here in product2 ");
                    Product product2 = new Product();
                    product2.setName(requestMap.get("name"));
                    logger.info("here in setId ");
                    product2.setId(Integer.valueOf(id));
                    product2.setDescription(requestMap.get("description"));
                    product2.setPrice(requestMap.get("price"));
                    Category category = new Category();
                    product2.setCategory(product.get().getCategory());
                    logger.info("here in productRepository ");

                    Product product22 = productRepository.findById(Integer.valueOf(id)).get();
                    logger.info("here in getImage)pp ");

                    product2.setImage(product22.getImage());
                    productRepository.save(product2) ;
                    return apputils.getResponseEntity("Product updates succefully", HttpStatus.OK);
                }else {
                    return apputils.getResponseEntity("Product id doesn't exist", HttpStatus.BAD_REQUEST);

                }
            } else {
                return apputils.getResponseEntity("Unouthorized access", HttpStatus.UNAUTHORIZED);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in services", HttpStatus.INTERNAL_SERVER_ERROR);

    }



    public ResponseEntity<String> deleteNewProduct(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()){


                productRepository.deleteById(Integer.valueOf(requestMap.get("id"))); ;
                return apputils.getResponseEntity("Product deletes succefully", HttpStatus.OK);


            } else {
                return apputils.getResponseEntity("Unouthorized access", HttpStatus.UNAUTHORIZED);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in services", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

