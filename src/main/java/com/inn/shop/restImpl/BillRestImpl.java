package com.inn.shop.restImpl;

import com.inn.shop.constents.ShopConstants;
import com.inn.shop.rest.BillRest;
import com.inn.shop.service.BillService;
import com.inn.shop.utils.ShopUtils;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BillRestImpl implements BillRest{

    @Autowired
    BillService billService;
    
    @Override
    public ResponseEntity<String> generateReport(Map<String,Object> requestMap) {
        try{
            return billService.generateReport(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
