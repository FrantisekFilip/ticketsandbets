package cz.frantisekfilip.ticketsandbets.mappers.impl;

import cz.frantisekfilip.ticketsandbets.domain.dto.BetDto;
import cz.frantisekfilip.ticketsandbets.domain.dto.MatchDto;
import cz.frantisekfilip.ticketsandbets.domain.entities.MatchEntity;
import cz.frantisekfilip.ticketsandbets.mappers.Mapper;
import cz.frantisekfilip.ticketsandbets.mappers.MatchMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchMapperImpl implements Mapper<MatchEntity, MatchDto> {

    private ModelMapper modelMapper;

    @Autowired
    public MatchMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MatchDto mapTo(MatchEntity matchEntity) {

        return modelMapper.map(matchEntity, MatchDto.class);
    }

    @Override
    public MatchEntity mapFrom(MatchDto matchDto) {

        return modelMapper.map(matchDto, MatchEntity.class);
    }
}
