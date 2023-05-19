package com.inn.shop.wrapper;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserWrapper {
    
    
    private Integer id;
    
    private String name;
    
    private String emal;
    
    private String contactNumber;
    
    private String status;


    public UserWrapper(Integer id, String name, String emal, String contactNumber, String status) {
        this.id = id;
        this.name = name;
        this.emal = emal;
        this.contactNumber = contactNumber;
        this.status = status;
    }
     
}
