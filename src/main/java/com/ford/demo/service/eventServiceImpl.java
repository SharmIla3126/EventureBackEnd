package com.ford.demo.service;

import com.ford.demo.model.Event;
import com.ford.demo.repository.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class eventServiceImpl implements IEventService {
    @Autowired
    IEventRepository eventRepository;
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {

        return eventRepository.findAll();
    }

    public Event getEventById(int id) {

        return eventRepository.findById(id).orElse(null);
    }

    public void deleteEvent(int id) {

        eventRepository.deleteById(id);
    }

//    public int getMaxParticipantsByEventId(int eventId) {
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + eventId));
//        return event.getMaxParticipants();
//    }
@Override
public int getMaxParticipantsByEventId(int eventId) {
    // Fetch the event by ID
    Optional<Event> eventOptional = eventRepository.findById(eventId);
    if (eventOptional.isPresent()) {
        return eventOptional.get().getMaxParticipants(); // Return max participants
    }
    return 0; // Return 0 if event not found
}
}


