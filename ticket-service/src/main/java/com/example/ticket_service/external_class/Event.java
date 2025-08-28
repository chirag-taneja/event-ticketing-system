package com.example.ticket_service.external_class;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class Event {

    private Long id;


    private String title;

    private String description;

    private String location;


    private LocalDateTime startDateTime;


    private LocalDateTime endDateTime;

    //admin id
    private Long createdBy;

}
