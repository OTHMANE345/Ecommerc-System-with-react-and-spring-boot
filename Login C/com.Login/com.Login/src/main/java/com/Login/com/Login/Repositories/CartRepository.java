package com.Login.com.Login.Repositories;

import com.Login.com.Login.modules.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserId(Integer id);

}
