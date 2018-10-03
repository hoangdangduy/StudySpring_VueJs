package com.api;

import com.dom.Customer;
import com.dom.User;
import com.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestAPI {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping(value = "/login-api", method = RequestMethod.POST)
    public ResponseEntity<Object> login(@RequestBody User user) {

        if (repository.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
            return new ResponseEntity<>("receive ok", HttpStatus.OK);
        }

        if (repository.findByMailAndPassword(user.getMail(), user.getPassword()) != null) {
            return new ResponseEntity<>("receive ok", HttpStatus.OK);
        }

        return new ResponseEntity<>("don't find", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/get-all-login", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllUser() {
        List<Customer> lstCustomer = new ArrayList<>();
        repository.findAll().forEach(domain -> lstCustomer.add(domain));
        return new ResponseEntity<List<Customer>>(lstCustomer, HttpStatus.OK);
    }
}
