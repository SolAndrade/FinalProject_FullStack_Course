package com.example.app.controllers;

import com.example.app.models.Ticket;
import com.example.app.repositories.TicketRepository;
import com.example.app.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return tickets;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable("id") Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(Math.toIntExact(id));
        if (optionalTicket.isPresent()) {
            return ResponseEntity.ok(optionalTicket.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable("id") Long id, @RequestBody Ticket ticket) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(Math.toIntExact(id));
        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();
            existingTicket.setMovie(ticket.getMovie());
            existingTicket.setUser(ticket.getUser());
            Ticket updatedTicket = ticketRepository.save(existingTicket);
            return ResponseEntity.ok(updatedTicket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") Long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(Math.toIntExact(id));
        if (optionalTicket.isPresent()) {
            ticketRepository.delete(optionalTicket.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
