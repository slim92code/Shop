package com.inn.shop.restImpl;

import com.inn.shop.rest.DashboardRest;
import com.inn.shop.service.DashboardService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardRestImpl implements DashboardRest{

    @Autowired
    DashboardService dashboardService;
    
    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        return dashboardService.getCount();
    }
    
}
