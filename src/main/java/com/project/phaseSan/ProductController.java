package com.project.phaseSan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public ResponseEntity<String> newProduct(@RequestBody Product newEntry)
    {
        String productName = newEntry.getProductName();
        if(productRepo.findByProductName(productName).size() > 0)
        {
            return new ResponseEntity<>("Product Already Exists !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else
        {
            productRepo.save(newEntry);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/productGet", method = RequestMethod.GET)
    public ResponseEntity<List<ProductResult>> getCategoryList()
    {

        List<ProductResult> productList = productRepo.findAllProduct();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @RequestMapping(value = "/updateProductName/{productId}/{productName}", method = RequestMethod.POST)
    public ResponseEntity<String>  updateProductName(@PathVariable("productId") int productId, @PathVariable("productName") String productName)
    {
        int status = productRepo.updateProductNameByProductId(productName, productId);
        if(status > 0)
        {
            return new ResponseEntity<>("Successfully Updated !", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Operation Failed !", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/updateProductPrice/{productId}/{productPrice}", method = RequestMethod.POST)
    public ResponseEntity<String>  updateProductPrice(@PathVariable("productId") int productId, @PathVariable("productPrice") int productPrice)
    {
        int status = productRepo.updateProductPriceByProductId(productPrice, productId);
        if(status > 0)
        {
            return new ResponseEntity<>("Successfully Updated !", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Operation Failed !", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/updateProductCategory/{productId}/{categoryId}", method = RequestMethod.POST)
    public ResponseEntity<String>  updateProductCategory(@PathVariable("productId") int productId, @PathVariable("categoryId") int categoryId)
    {
        int status = productRepo.updateCategoryByProductId(categoryId, productId);
        if(status > 0)
        {
            return new ResponseEntity<>("Successfully Updated !", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("Operation Failed !", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/getProductsByCategory/{categoryName}", method = RequestMethod.GET)
    public ResponseEntity<List<ProductResult>> getProductsByCategory(@PathVariable("categoryName") String categoryName)
    {
        List<Category> cat = categoryRepo.findByCategoryName(categoryName);
        List<ProductResult> res = new ArrayList<>();
        if(cat.size() > 0)
        {
            int categoryId = cat.get(0).getCategoryId();
            res = productRepo.findAllProductByCategory(categoryId);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/productDelete/{productName}", method = RequestMethod.POST)
    public ResponseEntity<String> deleteProduct(@PathVariable("productName") String productName)
    {
        int status = productRepo.deleteByProductName(productName);
        if(status > 0)
        {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("PRODUCT NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
