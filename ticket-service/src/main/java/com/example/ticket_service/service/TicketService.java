package com.example.ticket_service.service;

import com.example.ticket_service.model.Ticket;
import com.example.ticket_service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    TicketRepository ticketRepository;


    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket bookTicket(Ticket ticket) {
        if (!userExists(ticket.getUserId())) {
            throw new RuntimeException("User does not exist");
        }
        if (!eventExists(ticket.getEventId())) {
            throw new RuntimeException("Event does not exist");
        }
        ticket.setStatus("BOOKED");
        ticket.setBookingTime(LocalDateTime.now());
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public List<Ticket> getTicketsByEventId(Long eventId) {
        return ticketRepository.findByEventId(eventId);
    }

    public Ticket updateTicketStatus(Long id, String status) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    ticket.setStatus(status);
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public void cancelTicket(Long id) {
        updateTicketStatus(id, "CANCELLED");
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    private boolean userExists(Long userId) {
        String userServiceUrl = "http://localhost:8081/api/users?id=" + userId;
        try {
            restTemplate.getForObject(userServiceUrl, Object.class);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean eventExists(Long eventId) {
        String eventServiceUrl = "http://localhost:8082/api/events/" + eventId;
        try {
            restTemplate.getForObject(eventServiceUrl, Object.class);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
