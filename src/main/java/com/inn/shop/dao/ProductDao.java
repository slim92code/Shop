package com.inn.shop.dao;

import com.inn.shop.POJO.Product;
import com.inn.shop.wrapper.ProductWrapper;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;


public interface ProductDao extends JpaRepository<Product, Integer> {
    
    List<ProductWrapper> getAllProduct();
    
    @Modifying
    @Transactional
    Integer updateProductStatus(@Param("status") String status, @Param("id") Integer id);

    
    List<ProductWrapper> getProductByCategory(@Param("id") Integer id);
    
    ProductWrapper  getProductById(@Param("id") Integer id);
}
