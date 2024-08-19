package cz.frantisekfilip.ticketsandbets.mappers.impl;

import cz.frantisekfilip.ticketsandbets.domain.dto.TicketDto;
import cz.frantisekfilip.ticketsandbets.domain.entities.TicketEntity;
import cz.frantisekfilip.ticketsandbets.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketMapperImpl implements Mapper<TicketEntity, TicketDto> {

    private ModelMapper modelMapper;

    @Autowired
    public TicketMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TicketDto mapTo(TicketEntity ticketEntity) {
        return modelMapper.map(ticketEntity, TicketDto.class);
    }

    @Override
    public TicketEntity mapFrom(TicketDto ticketDto) {
        return modelMapper.map(ticketDto, TicketEntity.class);
    }
}
