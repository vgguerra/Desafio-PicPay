package com.guerra.picpay.picpay.controllers;

import com.guerra.picpay.picpay.DTO.UserDTO;
import com.guerra.picpay.picpay.models.Users;
import com.guerra.picpay.picpay.repositories.UserRepository;
import com.guerra.picpay.picpay.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserDTO userDTO) {

        Users newUser = usersService.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Users>> getUsers() {
        List<Users> users =  this.usersService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) throws Exception {
        Users users = usersService.findUserById(id);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
