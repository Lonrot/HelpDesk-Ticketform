package com.demo.ticketform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

@Entity
@Data
@Table(name = "TICKET")
@NoArgsConstructor@AllArgsConstructor
public class Ticket {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private Category category;

    @Column
    private String subject;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status = Status.IN_PROGRESS;

    @Column(name = "reference_code", length = 6, unique = true)
    private String referenceCode;

    /**
     * LAZY avoids fetching User unless needed.
     * Cascade PERSIST/MERGE saving a Ticket will also persist/merge its User.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
