package cz.frantisekfilip.ticketsandbets.repository;

import cz.frantisekfilip.ticketsandbets.domain.entities.MatchEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends CrudRepository<MatchEntity, Long> {
}
