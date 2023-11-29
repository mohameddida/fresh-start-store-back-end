package com.freshstratstore.freshstartstore.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freshstratstore.freshstartstore.doa.entities.Users;
import com.freshstratstore.freshstartstore.web.models.request.UsersForm;

@RestController
@RequestMapping("/users")
public class UserControllers {
    private static List<Users> users = new ArrayList<Users>();

    private static long idCount = 0L;
    static {
        users.add(new Users(++idCount, "user 1", "hello@gmail.com", "azer123", "90128208", "male"));
        users.add(new Users(++idCount, "user 2", "hello@gmail.com", "azer1234", "90129208", "male"));
        users.add(new Users(++idCount, "user 3", "hello@gmail.com", "azer12345", "90127208", "female"));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        if (this.users.isEmpty())
            return new ResponseEntity<>("list user is empty", HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(this.users, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Object> addUser(@RequestBody() UsersForm userForm) {
        this.users.add(new Users(++idCount, userForm.getName(), userForm.getEmail(),
                userForm.getPassword(), userForm.getPhone(), userForm.getGendre()));
        return new ResponseEntity<>("User is created !", HttpStatus.CREATED);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") Long id) {
        Users userFound = null;
        try {
            userFound = this.findUserById(id);
            this.users.remove(userFound);
            return new ResponseEntity("User is deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UsersForm UserForm, @PathVariable("id") Long id) {
        Users UserFound = null;
        try {
            UserFound = this.findUserById(id);
            UserFound.setName(UserForm.getName());
            UserFound.setEmail(UserForm.getEmail());
            UserFound.setPassword(UserForm.getPassword());
            UserFound.setPhone(UserForm.getPhone());
            return new ResponseEntity<>(UserFound, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        }
    }

    private Users findUserById(Long id) {

        return this.users.stream().filter(p -> p.getId() == id).findFirst().get();
    }
}
