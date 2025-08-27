package com.demo.ticketform.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    //TODO: Encapsulate and creationTime refactor, complete Enum. Check entity relation

    @Id
    private Long id;
    private String category;
    private String subject;
    private String description;
    private LocalDateTime createdAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne(targetEntity = User.class ,fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;
}
