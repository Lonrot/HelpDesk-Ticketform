package com.demo.ticketform.service;

import com.demo.ticketform.model.Ticket;
import com.demo.ticketform.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TicketService {

    TicketRepository repository;
    public TicketService(TicketRepository ticketRepository){
        this.repository = ticketRepository;
    }

    public List<Ticket> getAllTicketsByUserID(Long ID){
        var tickets = repository.findTicketsByUser_Id(ID);
        return tickets.isEmpty() ? List.of() : tickets;
    }
    public Ticket getTicketByID(Long ID){
        return repository.findTicketByID(ID);
    }

    public boolean deleteTicketByID(Long ID){
        try {
            repository.deleteTicketByID(ID);
            log.info("Deleting ticket with ID '{}'", ID);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean deleteTicketByUserID(Long userID){
        try {
            repository.deleteTicketByUser_Id(userID);
            log.info("Deleting ticket with user ID '{}'", userID);
            return true;
        } catch (EmptyResultDataAccessException e){
            return false;
        }
    }
}
