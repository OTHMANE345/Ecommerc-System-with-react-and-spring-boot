package com.Login.com.Login.Repositories;

import com.Login.com.Login.modules.Category;
import com.Login.com.Login.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> getAllCategory();
}
