package com.demo.ticketform.services;

import com.demo.ticketform.entities.Ticket;
import com.demo.ticketform.entities.User;
import com.demo.ticketform.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByUser(User user){
        List<Ticket> allTickets = ticketRepository.findAllByUserId(user.getId());
        return allTickets;
    }

    public Ticket findTicketByUser(User user){
        return ticketRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public void removeTicketByUser(User user){
        Optional<Ticket> optionalTicket = ticketRepository.findById(user.getId());
        optionalTicket.ifPresent(ticket -> ticketRepository.delete(ticket));
    }

    public void removeTicketById(Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Ticket not Found"));
        ticketRepository.delete(ticket);
    }

    public Ticket saveTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public Ticket findTicketById(Long id){
        return ticketRepository.getTicketById().orElseThrow(()-> new RuntimeException("Ticket not found"));
    }



}
