package com.ford.demo.repository;

import com.ford.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<Event,Integer> {

}
