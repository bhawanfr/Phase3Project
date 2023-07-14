package com.project.phaseSan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepo;

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public ResponseEntity<String> newCategory(@RequestBody Category newEntry)
    {
        String categoryName = newEntry.getCategoryName();
        if(categoryRepo.findByCategoryName(categoryName).size() > 0)
        {
            return new ResponseEntity<>("Category Already Exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else
        {
            categoryRepo.save(newEntry);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/categoryDelete", method = RequestMethod.POST)
    public ResponseEntity<String> deleteCategory(@RequestBody Category entry)
    {
        String categoryName = entry.getCategoryName();
        if(categoryRepo.findByCategoryName(categoryName).size() > 0)
        {
            int status = categoryRepo.deleteCategoryByCategoryName(categoryName);
            System.out.println(status);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("CATEGORY NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @RequestMapping(value = "/categoryGet", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getCategoryList()
    {
        List<Category> categoryList = categoryRepo.findAll();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }




}
