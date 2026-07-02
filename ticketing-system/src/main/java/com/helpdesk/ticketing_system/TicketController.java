package com.helpdesk.ticketing_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets/create")
    public String showCreateTicketForm() {
        return "create_ticket";
    }

    @PostMapping("/tickets/create")
    public String createTicket(@RequestParam String title, @RequestParam String description) {
        User createdBy = new User();
        createdBy.setEmail("user@example.com");
        ticketService.createTicket(title, description, createdBy);
        return "redirect:/tickets";
    }

    @GetMapping("/tickets")
    public String getTickets(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        List<Ticket> tickets = ticketService.getTickets(user);
        model.addAttribute("tickets", tickets);
        return "dashboard";
    }
    

}

