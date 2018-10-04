package com.api;

import com.dom.User;
import com.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestAPI {

    @Autowired
    private UserRepository repository;

    /*
     *  Check login
     */
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

    /*
     *  Add user
     */
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User userFind = repository.findByUsernameOrMail(user.getUsername(), user.getMail());
        if (userFind == null) {
            repository.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }

        return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
    }

    /*
     *  Get all user
     */
    @RequestMapping(value = "/get-all-login", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        List<User> lstUser = new ArrayList<>();
        repository.findAll().forEach(domain -> lstUser.add(domain));
        return new ResponseEntity<List<User>>(lstUser, HttpStatus.OK);
    }

    /*
     *  Delete user
     */
    @GetMapping("/delete-by-id")
    public String greeting(@RequestParam(name="id") long id) {
        repository.deleteById(id);
        return "greeting";
    }
}
