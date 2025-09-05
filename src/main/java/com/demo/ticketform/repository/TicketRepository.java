package com.demo.ticketform.repository;

import com.demo.ticketform.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findTicketsByUser_Id(Long userId);
    Ticket findTicketByID(Long id);
    void deleteTicketByID(Long id);
    void deleteTicketByUser_Id(Long userId);
}
