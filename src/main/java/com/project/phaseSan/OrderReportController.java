package com.project.phaseSan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderReportController {
    @Autowired
    private OrderReportRepository orderRepo;
    @Autowired
    private ProductRepository productRepo;

    @RequestMapping(value = "/orderReport", method = RequestMethod.POST)
    public ResponseEntity<String> addOrderReport(@RequestBody OrderReport newEntry)
    {
        int purchaseId = newEntry.getPurchaseOrderId();
        Optional<OrderReport> temp = orderRepo.findById(String.valueOf(purchaseId));
        if(temp.isPresent())
        {
            return new ResponseEntity<>("PurchaseOrder Already Exists !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else
        {
            orderRepo.save(newEntry);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/addProductToPurchaseOrder/{purchaseOrderId}", method = RequestMethod.POST)
    public ResponseEntity<String> addProductToPurchaseOrder(@RequestBody Product newEntry, @PathVariable("purchaseOrderId") int purchaseId)
    {
        Optional<OrderReport> temp = orderRepo.findById(String.valueOf(purchaseId));
        if(temp.isPresent())
        {
            String productName = newEntry.getProductName();
            if(productRepo.findByProductName(productName).size() > 0)
            {
                OrderReport alpha = temp.get();
                alpha.addProductToOrder(newEntry);
                orderRepo.save(alpha);
                return new ResponseEntity<>("Success!", HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>("Product Doesn't Exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else
        {
            return new ResponseEntity<>("Purchase Order Doesn't Exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/purchaseOrderGet", method = RequestMethod.GET)
    public ResponseEntity<List<OrderReport>> purchaseOrderList()
    {
        List<OrderReport> res = orderRepo.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @RequestMapping(value = "/purchaseOrderGetByCategory/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderReport>> purchaseOrderListByCategory(@PathVariable("categoryId") int categoryId)
    {
        List<OrderReport> res = orderRepo.findAllByCategory(categoryId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @RequestMapping(value = "/purchaseOrderGetByDate/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<OrderReport>> purchaseOrderListByCategory(@PathVariable("date") String date)
    {
        java.sql.Date temp = Date.valueOf(date);
        System.out.println(temp);
        List<OrderReport> res = orderRepo.findAllByPurchaseOrderDate(temp);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
