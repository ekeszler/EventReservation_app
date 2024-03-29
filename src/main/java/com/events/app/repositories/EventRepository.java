package com.events.app.repositories;

import com.events.app.entities.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findAllByName(String name);
    Optional<Event> findByName(String name);
    Optional<Event> findAllById(Long id);

}
