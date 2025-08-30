package com.demo.ticketform.controllers;

import com.demo.ticketform.entities.User;
import org.springframework.ui.Model;
import com.demo.ticketform.entities.Ticket;
import com.demo.ticketform.services.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/tickets")
@SessionAttributes({"user", "ticket"})
public class TicketController {

    private final TicketService ticketService;
    //private final UserService userService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;

    }

    @GetMapping("/create/step1")
    public String userFormView(Model model){
        model.addAttribute("user", new User());
        return "ticket_step1";
    }
    @PostMapping("/create/step1")
    public String saveUser(@ModelAttribute ("user") User user){
        return "redirect:/ticket_step2";
    }
    @GetMapping("/create/step2")
    public String createTicketView(Model model){
        model.addAttribute("ticket", new Ticket());
        return "ticket_step2";
    }
    @PostMapping("/create/step2")
    public String saveTicketDraft(@ModelAttribute("ticket") Ticket ticket){
        return "redirect:/ticket_step3";
    }
    @GetMapping("/create/step3")
    public String reviewConfirmationView(){
        return"ticket_step3";
    }
    @PostMapping("/create/step3")
    public String createUser(@ModelAttribute("ticket") Ticket ticket){
        ticketService.saveTicket(ticket);
        return "redirect:/tickets"+ ticket.getId();
    }
    @GetMapping("{id}")
    public String detailsView(@PathVariable Long id, Model model){
        Ticket ticket = ticketService.findTicketById(id);
        model.addAttribute("ticket", ticket);
        return "ticket_success";
    }
}
