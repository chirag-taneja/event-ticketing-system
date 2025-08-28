package com.example.ticket_service.service;

import com.example.ticket_service.external_class.Event;
import com.example.ticket_service.external_class.User;
import com.example.ticket_service.external_service.EventService;
import com.example.ticket_service.external_service.UserService;
import com.example.ticket_service.model.Ticket;
import com.example.ticket_service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    UserService userService;
    EventService eventService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

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
//        String userServiceUrl = "http://USER-SERVICE/api/users?id=" + userId;
//        try {
//            restTemplate.getForObject(userServiceUrl, Object.class);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
        ResponseEntity<User> user = userService.getUserById(userId);
        if (user.getStatusCode()== HttpStatus.OK)
        {
            return true;
        }
        return false;
    }

    private boolean eventExists(Long eventId) {
//        String eventServiceUrl = "http://EVENT-SERVICE/api/events/" + eventId;
//        try {
//            restTemplate.getForObject(eventServiceUrl, Object.class);
//            return true;
//        } catch (Exception ex) {
//            return false;
//        }
        ResponseEntity<Event> event = eventService.getEvent(eventId);
        if (event.getStatusCode()== HttpStatus.OK)
        {
            return true;
        }
        return false;
    }

}
