package com.inn.shop.dao;

import com.inn.shop.POJO.Bill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface BillDao extends JpaRepository<Bill, Integer>{
    
    List<Bill> getAllBills();
    
    List<Bill> getBillByUserName(@Param("username") String username);
    
}
