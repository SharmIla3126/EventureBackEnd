package com.ford.demo.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ford.demo.model.Event;
import com.ford.demo.repository.IEventRepository;
import com.ford.demo.service.eventServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    eventServiceImpl eventService;
    @PostMapping
    @Transactional
    public ResponseEntity<Event> addEvent(@Validated @RequestBody Event event) {
        System.out.println("Adding event with maxParticipants: " + event.getMaxParticipants());
        Event createdEvent = eventService.addEvent(event);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        Event event = eventService.getEventById(id);
        return event != null ? new ResponseEntity<>(event, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable int id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}/max-participants")
    public ResponseEntity<Integer> getMaxParticipantsByEventId(@PathVariable int id) {
        int maxParticipants = eventService.getMaxParticipantsByEventId(id);
        return new ResponseEntity<>(maxParticipants, HttpStatus.OK);
    }

}
