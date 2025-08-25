package com.example.ticket_service.repository;

import com.example.ticket_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {


    List<Ticket> findByUserId(Long userId);

    List<Ticket> findByEventId(Long eventId);

}
