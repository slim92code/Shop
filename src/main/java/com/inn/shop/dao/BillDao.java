package com.inn.shop.dao;

import com.inn.shop.POJO.Bill;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BillDao extends JpaRepository<Bill, Integer>{
    
}
