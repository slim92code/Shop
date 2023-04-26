package com.inn.shop.serviceImpl;

import com.inn.shop.POJO.User;
import com.inn.shop.dao.UserDao;
import com.inn.shop.service.UserService;
import com.inn.shop.utils.ShopUtils;
import constents.ShopConstents;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try{
            if(ValidatedSignUpMap(requestMap)){
                User user = userDao.findByEmailId(requestMap.get("email"));
                if(Objects.isNull(user)){
                    userDao.save(getUserFromMap(requestMap));
                    return ShopUtils.getResponseEntity( "Successfully Registred.", HttpStatus.OK);
                }
                else
                {
                    return ShopUtils.getResponseEntity( "Email alredy exist.",HttpStatus.BAD_REQUEST);        
                } 
            } 
            else {
            return ShopUtils.getResponseEntity(ShopConstents.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstents.SOMTHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
        
    private boolean ValidatedSignUpMap(Map<String, String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password"))
        {
            return true;
        }
        return false;
    }
    
    private User getUserFromMap(Map<String,String> requestMap){
        
        User user = new User();
        
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("emal"));
        user.setPassword("password");
        user.setStatus("false");
        user.setRole("user");
        
        return user;
    }
    
    public void nebitnaFunkcija(){
        int a=5;
    }
}