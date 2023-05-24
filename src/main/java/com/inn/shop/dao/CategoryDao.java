package com.inn.shop.dao;

import com.inn.shop.POJO.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryDao extends JpaRepository< Category, Integer>{
   
    List<Category> getAllCategory();
    
}
