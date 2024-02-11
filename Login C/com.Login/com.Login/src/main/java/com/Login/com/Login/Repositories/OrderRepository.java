package com.Login.com.Login.Repositories;

import com.Login.com.Login.modules.Category;
import com.Login.com.Login.modules.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
