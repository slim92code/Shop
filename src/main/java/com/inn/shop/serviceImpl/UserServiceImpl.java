package com.inn.shop.serviceImpl;

import com.inn.shop.JWT.CustomerUsersDetailsService;
import com.inn.shop.JWT.JwtUtil;
import com.inn.shop.POJO.User;
import com.inn.shop.dao.UserDao;
import com.inn.shop.service.UserService;
import com.inn.shop.utils.ShopUtils;
import constents.ShopConstents;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;
    
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;
    
    @Autowired
    JwtUtil jwtUtil;
    
    
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
                && requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        return false;
    }
    
    
    private User getUserFromMap(Map<String,String> requestMap){
        
        User user = new User();
        
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        
        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try{
            org.springframework.security.core.Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestMap.get("email"),requestMap.get("password")) 
            );
            if(auth.isAuthenticated()){
                if(customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\""+
                            jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                                    customerUsersDetailsService.getUserDetail().getRole()) + "\"}",
                    HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<String>("{\"message\":\""+"Wait for adin approval."+"\"}",
                            HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception ex){
            log.error("{}",ex);
        }
        return new ResponseEntity<String>("{\"message\":\""+"Bad Credentials."+"\"}",
                            HttpStatus.BAD_REQUEST);

    }
}