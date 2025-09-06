package com.demo.ticketform.repository;

import com.demo.ticketform.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {

    List<Ticket> findByUser_Id(Long userId);
    void deleteByUser_Id(Long userId);
    boolean existsByUser_Id(Long userId);
    Optional<Ticket> findByReferenceCode(String referenceCode);
}
