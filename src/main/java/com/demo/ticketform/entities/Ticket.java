package com.demo.ticketform.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    Long id;
    String category;
    String subject;
    String description;
    LocalDateTime createdAt;
    boolean status;

    @ManyToOne(targetEntity = User.class)
    User user;
}
