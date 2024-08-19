package cz.frantisekfilip.ticketsandbets.controllers;

import cz.frantisekfilip.ticketsandbets.domain.dto.TicketDto;
import cz.frantisekfilip.ticketsandbets.domain.dto.UserDto;
import cz.frantisekfilip.ticketsandbets.domain.entities.TicketEntity;
import cz.frantisekfilip.ticketsandbets.mappers.Mapper;
import cz.frantisekfilip.ticketsandbets.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private Mapper<TicketEntity, TicketDto> ticketMapper;

    private TicketService ticketService;

    @Autowired
    public TicketController(Mapper<TicketEntity, TicketDto> ticketMapper, TicketService ticketService) {
        this.ticketMapper = ticketMapper;
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public ResponseEntity<TicketDto> createUser(@RequestBody final TicketDto ticketDto) {
        TicketDto createdTickedDto = ticketMapper.mapTo(ticketService.createTicket(ticketMapper.mapFrom(ticketDto)));
        return new ResponseEntity<>(createdTickedDto, HttpStatus.CREATED);
    }
}
