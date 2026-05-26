package com.helpdesk.ticketing_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(String title, String description, User createdBy) {
        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setStatus("OPEN");
        ticket.setCreatedBy(createdBy);

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTickets(User user){
        if(user.getRole().equals("USER")){
            return ticketRepository.findByCreatedBy(user);
        }

        else{
            return ticketRepository.findAll();
        }
    }

    public Ticket updateTicketStatus(Long ticketId, String newStatus, User user){
        if(user.getRole().equals("USER")){
            throw new RuntimeException("Unauthorized");
        }
        
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if(!optionalTicket.isPresent()){
            throw new RuntimeException("Ticket not found");
        } 

        Ticket ticket = optionalTicket.get();
        ticket.setStatus(newStatus);
        return ticketRepository.save(ticket); 
    }
    
    public Ticket getTicketByID(Long ticketId, User user){
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId); 
        
        if(optionalTicket.isEmpty()){
            throw new RuntimeException("Ticket not found");
        }

        Ticket ticket = optionalTicket.get();
        
        if(user.getRole().equals("USER") && !ticket.getCreatedBy().getId().equals(user.getId())){
            throw new RuntimeException("You do not have authorization to view this ticket");
        }

        return ticket;
    }

    public List<Ticket> getTicketsByStatus(String status, User user){
        if(user.getRole().equals("USER")){
            throw new RuntimeException("Unauthorized");
        }

        return ticketRepository.findbyStatus(status);
    }

    public void deleteTicket(Long ticketId, User user){
        if(!user.getRole().equals("ADMIN")){
            throw new RuntimeException("You are unauthorized to delete tickets");
        }

        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if(optionalTicket.isEmpty()){
            throw new RuntimeException("Ticket not found");
        }

        ticketRepository.deleteById(ticketId); 
    }
}
