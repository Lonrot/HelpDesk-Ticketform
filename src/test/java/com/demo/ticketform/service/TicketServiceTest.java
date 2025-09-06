package com.demo.ticketform.service;

import com.demo.ticketform.model.Category;
import com.demo.ticketform.model.Status;
import com.demo.ticketform.model.Ticket;
import com.demo.ticketform.model.User;
import com.demo.ticketform.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.dao.EmptyResultDataAccessException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//TODO: Add thrown error checking once is done
@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    TicketRepository repository;

    @InjectMocks
    TicketService service;

    private Long existingUserID;
    private Long missingUserID;
    private Long existingTicketID;
    private Long missingTicketID;

    private Ticket t1;
    private Ticket t2;
    private Ticket t3; // new
    private Ticket existingTicket;
    private User user;

    @BeforeEach
    void setUp() {
        existingUserID = 1L;
        missingUserID = 500L;

        existingTicketID = 11L;
        missingTicketID = 404L;

        user = new User();
        user.setId(existingUserID);

        t1 = new Ticket(existingTicketID, Category.CATEGORY_A, "Subject Ticket 1", "Description T1", Status.IN_PROGRESS, "referenceCode-1", user);
        t2 = new Ticket(existingTicketID + 1, Category.CATEGORY_A, "Subject Ticket 2", "Description T2", Status.IN_PROGRESS, "referenceCode-2", user);
        t3 = new Ticket(existingTicketID + 2, Category.CATEGORY_A, "Subject Ticket 3", "Description T3", Status.IN_PROGRESS, "referenceCode-3", user);

        existingTicket = new Ticket();
    }

    @Test
    void getAllTicketsByUserID_returnsTickets_whenRepositoryHasResults() {
        when(repository.findByUser_Id(existingUserID)).thenReturn(List.of(t1, t2, t3));

        var result = service.getAllTicketsByUserID(existingUserID);

        assertEquals(3, result.size());
        assertSame(t1, result.get(0));
        assertSame(t2, result.get(1));
        assertSame(t3, result.get(2));
        verify(repository).findByUser_Id(existingUserID);
    }

    @Test
    @DisplayName("Get List Of Tickets")
    void getAllTicketsByUserID_returnsEmptyList_whenNoResults() {
        when(repository.findByUser_Id(missingUserID)).thenReturn(List.of());

        var result = service.getAllTicketsByUserID(missingUserID);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository,times(1)).findByUser_Id(missingUserID);
    }

    @Test
    void getTicketByID_returnsTicket_whenFound() {
        when(repository.findById(existingTicketID)).thenReturn(Optional.ofNullable(existingTicket));

        var result = service.getTicketByID(existingTicketID);

        assertSame(existingTicket, result);
        verify(repository).findById(existingTicketID);
    }

    @Test
    void getTicketByID_returnsNull_whenNotFound() {
        when(repository.findById(missingTicketID)).thenReturn(null);

        var result = service.getTicketByID(missingTicketID);

        assertNull(result);
        verify(repository).findById(missingTicketID);
    }

    @Test
    void deleteTicketByID_returnsTrue_whenDeleteSucceeds() {
        doNothing().when(repository).deleteById(existingTicketID);

        boolean deleted = service.deleteTicketByID(existingTicketID);

        assertTrue(deleted);
        verify(repository).deleteById(existingTicketID);
    }

    @Test
    void deleteTicketByID_returnsFalse_whenNotFound() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteById(missingTicketID);

        boolean deleted = service.deleteTicketByID(missingTicketID);

        assertFalse(deleted);
        verify(repository).deleteById(missingTicketID);
    }

    @Test
    void deleteTicketByUserID_returnsTrue_whenDeleteSucceeds() {
        doNothing().when(repository).deleteByUser_Id(existingUserID);

        boolean deleted = service.deleteTicketByUserID(existingUserID);

        assertTrue(deleted);
        verify(repository).deleteByUser_Id(existingUserID);
    }

    @Test
    void deleteTicketByUserID_returnsFalse_whenNotFound() {
        doThrow(new EmptyResultDataAccessException(1)).when(repository).deleteByUser_Id(missingUserID);

        boolean deleted = service.deleteTicketByUserID(missingUserID);

        assertFalse(deleted);
        verify(repository).deleteByUser_Id(missingUserID);
    }
}