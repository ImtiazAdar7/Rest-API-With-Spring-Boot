package com.rest_api.RestApi.controller;

import com.rest_api.RestApi.dto.UserAddDTO;
import com.rest_api.RestApi.dto.UserDTO;
import com.rest_api.RestApi.service.Services;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final Services services;

    public UserController(Services services) {
        this.services = services;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(services.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(services.getUserById(id));
    }
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserAddDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(services.createUser(user));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.OK).body(services.updateUser(id, user));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        services.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
