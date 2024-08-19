package cz.frantisekfilip.ticketsandbets.services.impl;

import cz.frantisekfilip.ticketsandbets.domain.entities.TicketEntity;
import cz.frantisekfilip.ticketsandbets.repository.TicketRepository;
import cz.frantisekfilip.ticketsandbets.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public TicketEntity createTicket(TicketEntity ticketEntity) {
        return ticketRepository.save(ticketEntity);
    }
}
