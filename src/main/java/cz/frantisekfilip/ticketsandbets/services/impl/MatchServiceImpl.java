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
        if(matchEntity.getId() == null) {
            matchEntity = matchRepository.save(matchEntity);
        }

        for (BetEntity bet : matchEntity.getBets()) {
            bet.setMatch(matchEntity);
        }

        MatchEntity savedMatch = matchRepository.save(matchEntity);

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
