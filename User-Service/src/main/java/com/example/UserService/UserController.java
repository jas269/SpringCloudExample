package com.example.UserService;

import java.util.List;

import com.example.UserService.model.User;
import com.example.UserService.repo.UserServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for the user application
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserServiceRepository repository;
    @Autowired
    public UserController(UserServiceRepository repository) {
        this.repository = repository;
    }

    /**
     * Api endpoint for getting list of all registered users
     */
    @GetMapping
    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    /**
     * Api endpoint to register a new user
     */
    @PostMapping(value = "/createUser")
    public User createUser(@RequestBody User user) {
        this.repository.save(user);
        return user;
    }

    /**
     * Api endpoint to get a User detail
     * @param emailId
     */
    @GetMapping(value = "/{emailId}")
    public User getUserByEmailId(@PathVariable("emailId") String emailId) {
        return this.repository.findUserByEmailId(emailId).orElseThrow();
    }

    /**
     * Api endpoint to get a User detail
     * @param userId
     */
    @GetMapping(value = "/{userId}")
    public User getUserByCustomerId(@PathVariable("userId") Integer userId) {
        return this.repository.findById(userId).orElseThrow();
    }

    /**
     * Api end point to update a user
     */
    @PutMapping(value = "/update")
    public User updateUser(@RequestBody User user){
        this.repository.save(user);
        return user;
    }

    /**
     * Api endpoint to delete a user
     */
    @DeleteMapping(value = "/delete/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        this.repository.delete(this.repository.getById(userId));
    }

}
