package com.demo.ticketform.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
@Data
@NoArgsConstructor@AllArgsConstructor
public class User {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;


    /**
     * Inverse side of the relationship.
     * mappedBy MUST match the field name on Ticket ("user").
     */

    @OneToMany(mappedBy = "user")
    private List<Ticket> ticketList = new ArrayList<>();


}
