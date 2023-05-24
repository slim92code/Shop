package com.inn.shop.service;

import com.inn.shop.POJO.Category;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;


public interface CategoryService {
    
    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);
    
    ResponseEntity<List<Category>> getAllCategory(String filterValue);
    
    ResponseEntity<String> updateCategory(Map<String, String> requestMap);
    
}
