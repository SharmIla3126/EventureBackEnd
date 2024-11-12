package com.ford.demo.controller;

import com.ford.demo.model.Event;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ford.demo.model.User;
import com.ford.demo.repository.IEventRepository;
import com.ford.demo.repository.IUserRepository;
import com.ford.demo.service.eventServiceImpl;
import com.ford.demo.service.userServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    userServiceImpl userService;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    eventServiceImpl eventService;

    @Autowired
    IEventRepository eventRepository;
    @PostMapping
    @Transactional
    public ResponseEntity<User> addUser(@Validated @RequestBody User user) {
        try {
            // Check if the event is null
            if (user.getEvent() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Event cannot be null
            }

            // Get the event ID
            Integer eventId = user.getEvent().getId(); // Use Integer to allow null checks

            // Check if the event ID is valid
            if (eventId == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Event ID cannot be null
            }

            // Fetch the event using the event ID
            Event event = eventService.getEventById(eventId);
            if (event == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Event not found
            }

            // Get max participants from the event
            int maxParticipants = event.getMaxParticipants();

            // Count current participants for the event
            int currentParticipantCount = userService.countUsersByEventId(event.getId());
            if (currentParticipantCount >= maxParticipants) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403 Forbidden
            }
            event.setNo_of_part(currentParticipantCount + 1);
            eventRepository.save(event);
            eventRepository.flush();

            // Add the user
            User createdUser = userService.addUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            System.err.println("Error adding user: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.err.println("Internal server error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = userService.getUserById(id);
        return user != null ? new ResponseEntity<>(user, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
