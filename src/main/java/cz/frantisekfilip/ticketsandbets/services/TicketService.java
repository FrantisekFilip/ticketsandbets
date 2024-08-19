package cz.frantisekfilip.ticketsandbets.services;

import cz.frantisekfilip.ticketsandbets.domain.entities.TicketEntity;

public interface TicketService {
    TicketEntity createTicket(TicketEntity ticketEntity);
}
