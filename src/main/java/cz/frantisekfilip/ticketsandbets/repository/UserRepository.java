package cz.frantisekfilip.ticketsandbets.repository;

import cz.frantisekfilip.ticketsandbets.domain.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
