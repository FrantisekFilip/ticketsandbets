package cz.frantisekfilip.ticketsandbets.services;

import cz.frantisekfilip.ticketsandbets.domain.entities.MatchEntity;
import cz.frantisekfilip.ticketsandbets.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface MatchService {

    List<MatchEntity> findAll();

    boolean isExists(Long id);

    MatchEntity save(MatchEntity matchEntity);

    Optional<MatchEntity> findById(Long id);
}
