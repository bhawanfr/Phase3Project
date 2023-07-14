package com.project.phaseSan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> newUser(@RequestBody User newEntry)
    {
        String userName = newEntry.getUserName();
        if(userRepo.findByUserName(userName).size() > 0)
        {
            return new ResponseEntity<>("User Already Exists !", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else
        {
            userRepo.save(newEntry);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/userGet", method = RequestMethod.GET)
    public ResponseEntity<List<User>> userGetList()
    {
        List<User> res = userRepo.findAll();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value = "/userByUserName/{userName}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> userGetList(@PathVariable("userName") String userName)
    {
        List<User> res = userRepo.findByUserName(userName);
        if(res.size() > 0)
        {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

}


