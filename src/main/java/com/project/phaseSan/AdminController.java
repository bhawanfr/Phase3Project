package com.project.phaseSan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {
    @Autowired
    private AdminRepository adminRepo;

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public ResponseEntity<String> newAdmin(@RequestBody Admin newEntry)
    {
        String adminName = newEntry.getAdminName();
        if(adminRepo.findByAdminName(adminName).size() > 0)
        {
            return new ResponseEntity<>("Admin Already Exists. Please Login!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else
        {
            adminRepo.save(newEntry);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/adminUpdate", method = RequestMethod.POST)
    public ResponseEntity<String> updateAdminPassword(@RequestBody Admin entry)
    {
        String adminName = entry.getAdminName();
        if(adminRepo.findByAdminName(adminName).size() > 0)
        {
            int status = adminRepo.updateAdminByAdminName(entry.getAdminPassword(), entry.getAdminName());
            System.out.println(status);
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>("ADMIN NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
