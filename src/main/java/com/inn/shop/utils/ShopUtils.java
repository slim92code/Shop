package com.inn.shop.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ShopUtils {
    
    private ShopUtils(){       
    }
    
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }
    
 }
