package com.prowings.user_service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.user_service.model.User;
import com.prowings.user_service.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Log4j2
public class UserController {

	@Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
    	log.info("Getting all users");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
    	log.info("Getting user by id: {}", id);
        Optional<User> user = userService.getUserById(id);
        
        return new ResponseEntity(user.orElse(null), user.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	log.info("Creating user: {}", user);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    	log.info("Deleting user by id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/role/{role}")
	public List<User> getUsersByRole(@PathVariable String role) {
    	 if (role == null || role.isEmpty()) {
             throw new IllegalArgumentException("Role parameter is required!");
         }
		log.info("Getting users by role: {}", role);
		return userService.getUsersByRole(role);
	}
}
