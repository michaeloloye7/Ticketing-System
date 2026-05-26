package com.helpdesk.ticketing_system;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByCreatedBy(User User);
    List<Ticket> findbyStatus(String status); 
}
