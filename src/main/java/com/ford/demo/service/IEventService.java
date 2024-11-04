package com.ford.demo.service;

import com.ford.demo.model.Event;

import java.util.List;
import java.util.Optional;

public interface
IEventService{
    public Event addEvent(Event event);
    public List<Event> getAllEvents();
    public Event getEventById(int id);
    public void deleteEvent(int id);
    // Method to get the maximum number of participants for a specific event
    int getMaxParticipantsByEventId(int eventId);

}
