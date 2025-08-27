package com.demo.ticketform.repositories;

import com.demo.ticketform.entities.Ticket;
import com.demo.ticketform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

    Optional<Ticket> getTicketById();
    Optional<Ticket> getTicketByUserId(Long id);
    List<Ticket> findAllByUserId(Long id);
    void removeTicketByUser(Long id);
    Optional<Ticket> findTicketByUser(User users);
}
