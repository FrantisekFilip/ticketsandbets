package cz.frantisekfilip.ticketsandbets.controllers;

import cz.frantisekfilip.ticketsandbets.domain.dto.MatchDto;
import cz.frantisekfilip.ticketsandbets.domain.dto.UserDto;
import cz.frantisekfilip.ticketsandbets.domain.entities.MatchEntity;
import cz.frantisekfilip.ticketsandbets.domain.entities.UserEntity;
import cz.frantisekfilip.ticketsandbets.mappers.Mapper;
import cz.frantisekfilip.ticketsandbets.mappers.MatchMapper;
import cz.frantisekfilip.ticketsandbets.mappers.impl.MatchMapperImpl;
import cz.frantisekfilip.ticketsandbets.services.impl.MatchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private MatchServiceImpl matchService;

    private MatchMapper<MatchEntity, MatchDto> matchMapper;

    @Autowired
    public MatchController(MatchServiceImpl matchService, MatchMapperImpl matchMapper) {
        this.matchService = matchService;
        this.matchMapper = matchMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<MatchDto> createUser(@RequestBody final MatchDto matchDto) {
        MatchEntity newMatch = matchService.save(matchMapper.mapFrom(matchDto));
        MatchDto matchDto1 = matchMapper.mapToExport(newMatch);
        return new ResponseEntity<>(matchDto1, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<MatchDto> updateMatch(@PathVariable("id") Long id, @RequestBody final MatchDto match){
        if (!matchService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        match.setId(id);
        MatchEntity updatedMatch = matchService.save(matchMapper.mapFrom(match));
        return new ResponseEntity<>(matchMapper.mapToExport(updatedMatch), HttpStatus.OK);
    }

    @GetMapping("")
    public List<MatchDto> listMatches(){
        List<MatchEntity> matchEntityList = matchService.findAll();
        return matchEntityList.stream().map(matchMapper::mapToExport).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable("id") Long id){
        Optional<MatchEntity> foundMatch = matchService.findById(id);

        return foundMatch.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<MatchDto>(matchMapper.mapToExport(foundMatch.get()), HttpStatus.OK);
    }
}
