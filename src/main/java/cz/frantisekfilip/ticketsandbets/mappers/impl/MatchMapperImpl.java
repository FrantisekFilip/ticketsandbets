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
public class MatchMapperImpl implements MatchMapper<MatchEntity, MatchDto> {

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

    @Override
    public MatchDto mapToExport(MatchEntity matchEntity) {
        MatchDto returnedDto = modelMapper.map(matchEntity, MatchDto.class);

        MatchDto newMatch = new MatchDto(returnedDto.getId(), returnedDto.getSport(), returnedDto.getTeam1(), returnedDto.getTeam2(), returnedDto.getMatchDate(), null);
        for(BetDto bet : returnedDto.getBets()) {
            bet.setMatch(newMatch);
        }
        return returnedDto;
    }
}
