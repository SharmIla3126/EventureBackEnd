package com.ford.demo.service;

import com.ford.demo.model.Event;
import com.ford.demo.model.User;
import com.ford.demo.repository.IEventRepository;
import com.ford.demo.repository.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IEventRepository eventRepository;
    @Override
    @Transactional
    public User addUser(User user) {
       // return userRepository.save(user);
        if (user.getEvent() != null) {
            // Fetch the Event entity using event_id
            Event event = eventRepository.findById(user.getEvent().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid event ID"));
            user.setEvent(event);


            // Increment the participant count for the event
            event.setNo_of_part(event.getNo_of_part() + 1);

            // Save the updated event
            eventRepository.save(event);  // This will persist the updated `no_of_part`
            eventRepository.flush();
        } else {
            throw new IllegalArgumentException("Event cannot be null");
        }

        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);

    }

    public Event findEventById(int eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID: " + eventId));
    }

    public int countUsersByEventId(int eventId) {
        return userRepository.countByEventId(eventId); //  this method exists in  IUserRepository
    }
}
