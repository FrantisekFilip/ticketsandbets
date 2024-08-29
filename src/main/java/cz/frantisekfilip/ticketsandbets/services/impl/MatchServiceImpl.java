package cz.frantisekfilip.ticketsandbets.services.impl;

import cz.frantisekfilip.ticketsandbets.domain.entities.MatchEntity;
import cz.frantisekfilip.ticketsandbets.domain.entities.BetEntity;
import cz.frantisekfilip.ticketsandbets.repository.MatchRepository;
import cz.frantisekfilip.ticketsandbets.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class MatchServiceImpl implements MatchService {

    MatchRepository matchRepository;

    @Autowired
    public MatchServiceImpl(MatchRepository matchRepository) {

        this.matchRepository = matchRepository;
    }

    @Override
    public MatchEntity save(MatchEntity matchEntity) {
        //nový match
        if(matchEntity.getId() == null) {
            matchEntity = matchRepository.save(matchEntity);
        }

        //přidáme id zápasu, aby se mohli bets uložit
        for (BetEntity bet : matchEntity.getBets()) {
            bet.setMatch_id(matchEntity.getId());
        }

        MatchEntity savedMatch = matchRepository.save(matchEntity);

        //přidáme zápas bez Listu betů, aby nedošlo k nekonečné rekurzi
        for (BetEntity bet : savedMatch.getBets()) {
            bet.setMatch(new MatchEntity(
                    savedMatch.getId(),
                    savedMatch.getSport(),
                    savedMatch.getTeam1(),
                    savedMatch.getTeam2(),
                    savedMatch.getMatchDate()
            ));
        }

        return savedMatch;
    }

    @Override
    public List<MatchEntity> findAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isExists(Long id) {
        return matchRepository.existsById(id);
    }

    @Override
    public Optional<MatchEntity> findById(Long id) {
        return matchRepository.findById(id);
    }
}
