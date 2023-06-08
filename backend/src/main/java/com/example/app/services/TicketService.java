package com.example.app.services;

import com.example.app.models.Ticket;
import com.example.app.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    // Assuming you have a movieRepository to access the movie data
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    /*public void deleteTicketsByMovieId(Integer movieId) {
        ticketRepository.deleteByMovieId(movieId);
    }*/

}
