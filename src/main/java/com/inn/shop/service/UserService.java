package com.inn.shop.service;

import com.inn.shop.wrapper.UserWrapper;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;


public interface UserService {

    public ResponseEntity<String> signUp(Map<String, String> requestMap);
    
    public ResponseEntity<String> login(Map<String, String> requestMap);
    
    ResponseEntity<List<UserWrapper>> getAllUser();
    
    ResponseEntity<String>update(Map<String, String>requestMap);

    ResponseEntity<String> checkToken();
    
    ResponseEntity<String> changePassword(Map<String, String> requestMap);
    
    ResponseEntity<String> forgotPasswrod(Map<String, String> requestMap);
    
    
}
