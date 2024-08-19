package cz.frantisekfilip.ticketsandbets.mappers.impl;

import cz.frantisekfilip.ticketsandbets.domain.dto.BetDto;
import cz.frantisekfilip.ticketsandbets.domain.entities.BetEntity;
import cz.frantisekfilip.ticketsandbets.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BetMapperImpl implements Mapper<BetEntity, BetDto> {

    private ModelMapper modelMapper;

    @Autowired
    public BetMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BetDto mapTo(BetEntity betEntity) {
        return modelMapper.map(betEntity, BetDto.class);
    }

    @Override
    public BetEntity mapFrom(BetDto betDto) {
        return modelMapper.map(betDto, BetEntity.class);
    }
}
