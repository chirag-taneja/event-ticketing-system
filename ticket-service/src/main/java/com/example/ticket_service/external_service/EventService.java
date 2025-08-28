package com.example.ticket_service.external_service;

import com.example.ticket_service.external_class.Event;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "EVENT-SERVICE")
public interface EventService {

    @GetMapping("/api/events/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id);
}
