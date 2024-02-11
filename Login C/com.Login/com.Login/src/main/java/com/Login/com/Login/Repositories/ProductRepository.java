package com.Login.com.Login.Repositories;

import com.Login.com.Login.modules.Category;
import com.Login.com.Login.modules.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> getAllProducts();

}
