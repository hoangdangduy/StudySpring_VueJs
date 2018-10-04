package com.api;

import com.dom.User;
import com.repo.UserRepository;
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
    private UserRepository repository;

    @RequestMapping(value = "/login-api", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user) {

        User userFind = repository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (userFind != null) {
            return new ResponseEntity<User>(userFind, HttpStatus.OK);
        }

        userFind = repository.findByMailAndPassword(user.getMail(), user.getPassword());
        if (userFind != null) {
            return new ResponseEntity<User>(userFind, HttpStatus.OK);
        }

        return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/get-all-login", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        List<User> lstUser = new ArrayList<>();
        repository.findAll().forEach(domain -> lstUser.add(domain));
        return new ResponseEntity<List<User>>(lstUser, HttpStatus.OK);
    }
}
