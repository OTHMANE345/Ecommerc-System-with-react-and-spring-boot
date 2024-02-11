package com.Login.com.Login.Service;

import com.Login.com.Login.JWT.JwtFilter;
import com.Login.com.Login.Repositories.CartRepository;
import com.Login.com.Login.Repositories.ProductRepository;
import com.Login.com.Login.modules.Cart;
import com.Login.com.Login.modules.Product;
import com.Login.com.Login.utils.apputils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Slf4j
@Service
public class CartServices {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;
    @Autowired
    JwtFilter jwtFilter;

    Logger logger = Logger.getLogger("my.app");


    public ResponseEntity<String> addNewCart(Map<String, String> requestMap) {
        try {
            logger.info("inside try of serveces ");

            if(jwtFilter.isUser()){
                logger.info("inside try of validateCartMap ");

                if(validateCartMap(requestMap, false)){
                    logger.info("inside try of cartRepository ");

                    cartRepository.save(getCartFromMap(requestMap)) ;
                    return apputils.getResponseEntity("Cart addes succefully", HttpStatus.OK);

                }
            } else {
                return apputils.getResponseEntity("Unouthorized access", HttpStatus.UNAUTHORIZED);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);

    }


    private boolean validateCartMap(Map<String, String> requestMap , boolean validateid) {
        if (requestMap.containsKey("product_id")
        && requestMap.containsKey("product_name") && requestMap.containsKey("user_id")
        && requestMap.containsKey("quantity") && requestMap.containsKey("image")
        && requestMap.containsKey("price")) {
            if(validateid){
                logger.info("inside try of validateid ");

                return true;
            } else if (!validateid) {
                return  true;
            }
        }
        return false;


    }

    private Cart getCartFromMap(Map<String, String> requestMap){
        Cart cart = new Cart();
        String product_id4 =requestMap.get("product_id");
        logger.info(product_id4);
        cart.setProduct_id(Integer.valueOf(product_id4));
        cart.setProduct_name(requestMap.get("product_name"));
        String user_id =requestMap.get("user_id");
        logger.info(user_id);
        cart.setUser_id(Integer.valueOf(user_id));
        String quantity =requestMap.get("quantity");
        logger.info(quantity);
        cart.setQuantity(Integer.valueOf(quantity));
        logger.info("inside try of productRepository setImage  ");
        String product_id =requestMap.get("product_id");
        logger.info(product_id);
        Product product = productRepository.findById(Integer.valueOf(product_id)).get();
        logger.info("inside try of setImage setImage  ");

        cart.setImage(product.getImage());
        cart.setPrice(requestMap.get("price"));
        return cart;
    }


    public ResponseEntity<List<Cart>> getAllCart(Map<String, String> requestMap) {
        List<Cart> carts = new ArrayList<>();
        String id = requestMap.get("user_id");
        for(Cart cart : cartRepository.findAllByUserId(Integer.valueOf(id))){
            carts.add(cart);
        }
        return new ResponseEntity<>(carts,HttpStatus.OK);
    }


    public ResponseEntity<String> deleteNewCart(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isUser()){


                cartRepository.deleteById(Integer.valueOf(requestMap.get("id"))); ;
                return apputils.getResponseEntity("Cart deletes succefully", HttpStatus.OK);


            } else {
                return apputils.getResponseEntity("Unouthorized access", HttpStatus.UNAUTHORIZED);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in services", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

