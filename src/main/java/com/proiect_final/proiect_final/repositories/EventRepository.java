package com.proiect_final.proiect_final.repositories;

import com.proiect_final.proiect_final.entities.Event;
import com.proiect_final.proiect_final.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {


}
