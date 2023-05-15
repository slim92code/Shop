package com.inn.shop.restImpl;

import com.inn.shop.rest.UserRest;
import com.inn.shop.service.UserService;
import com.inn.shop.utils.ShopUtils;
import constents.ShopConstents;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRestImpl implements UserRest{

    @Autowired
    UserService userService;
    
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{
            return userService.signUp(requestMap);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try
        {
            return userService.login(requestMap);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstents.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}