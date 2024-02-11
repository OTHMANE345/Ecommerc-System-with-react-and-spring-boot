package com.Login.com.Login.Service;

import com.Login.com.Login.JWT.JwtFilter;
import com.Login.com.Login.Repositories.CartRepository;
import com.Login.com.Login.Repositories.OrderRepository;
import com.Login.com.Login.modules.Cart;
import com.Login.com.Login.modules.Category;
import com.Login.com.Login.modules.Order;
import com.Login.com.Login.utils.apputils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServices {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    JwtFilter  jwtFilter;

    public ResponseEntity<String> addNewOrder(Map<String, String> requestMap) {
        try {

                if(validateOrderMap(requestMap, false)){
                    Order order = new Order();
                    String id = requestMap.get("user_id");
                    // List<Cart> cart = cartRepository.findAllByUserId(Integer.valueOf(id));
                    for(Cart cart : cartRepository.findAllByUserId(Integer.valueOf(id))){
                        order.setProduct_id(cart.getProduct_id());
                        order.setProduct_name(cart.getProduct_name());
                        order.setId(cart.getId());

                        order.setQuantity(cart.getQuantity());
                        order.setUser_id(cart.getUser_id());
                        order.setTotal(String.valueOf(Integer.valueOf(cart.getPrice()) * cart.getQuantity()));
                        orderRepository.save(order);
                        cartRepository.delete(cart);
                    }
                    return apputils.getResponseEntity("Order addes succefully", HttpStatus.OK);

                }


        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateOrderMap(Map<String, String> requestMap , boolean validateid) {
        if (requestMap.containsKey("user_id")) {
            if(validateid){
                return true;
            } else if (!validateid) {
                return  true;
            }
        }
        return false;


    }


    public ResponseEntity<List<Order>> getAllOrders() {
   List<Order> orders = new ArrayList<>();
   for(Order order : orderRepository.findAll()){
       orders.add(order);
   }
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }

    public ResponseEntity<String> deleteNewOrder(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()){


                orderRepository.deleteById(Integer.valueOf(requestMap.get("id"))); ;
                return apputils.getResponseEntity("Order deletes succefully", HttpStatus.OK);


            } else {
                return apputils.getResponseEntity("Unouthorized access", HttpStatus.UNAUTHORIZED);

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return apputils.getResponseEntity("Something went wrong in services", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
