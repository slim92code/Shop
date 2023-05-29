package com.inn.shop.serviceImpl;

import java.util.Optional;
import com.google.common.base.Strings;
import com.inn.shop.JWT.JwtFilter;
import com.inn.shop.POJO.Category;
import com.inn.shop.dao.CategoryDao;
import com.inn.shop.service.CategoryService;
import com.inn.shop.utils.ShopUtils;
import com.inn.shop.constents.ShopConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateCategoryMap(requestMap, false)) {
                    categoryDao.save(getCategoryFromMap(requestMap, false));
                    return ShopUtils.getResponseEntity("Category Added Successfully", HttpStatus.OK);
                }
            } else {
                return ShopUtils.getResponseEntity(ShopConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateCategoryMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Category getCategoryFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        if (isAdd) {
            category.setId(Integer.parseInt(requestMap.get("id")));
        }
        category.setName(requestMap.get("name"));
        return category;
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
        try{
            if(!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")){
                log.info("Inside if");
                return new ResponseEntity<List<Category>>(categoryDao.getAllCategory(), HttpStatus.OK);
            }
            return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Category>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategory(Map<String, String> reqiestMap) {
        try{
            if(jwtFilter.isAdmin()){
                if(validateCategoryMap(reqiestMap, true)){
                    Optional optional = categoryDao.findById(Integer.parseInt(reqiestMap.get("id")));
                    if(!optional.isEmpty()){
                        categoryDao.save(getCategoryFromMap(reqiestMap, true));
                        return ShopUtils.getResponseEntity("Category Updated Successfully", HttpStatus.OK);
                    }else {
                        return ShopUtils.getResponseEntity("Category id does not exist", HttpStatus.OK);
                    }
                }
                return ShopUtils.getResponseEntity(ShopConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }else{
                return ShopUtils.getResponseEntity(ShopConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return ShopUtils.getResponseEntity(ShopConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
}
