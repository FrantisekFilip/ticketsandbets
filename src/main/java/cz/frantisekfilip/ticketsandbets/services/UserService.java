package cz.frantisekfilip.ticketsandbets.services;

import cz.frantisekfilip.ticketsandbets.domain.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity save(UserEntity user);

    List<UserEntity> findAll();

    Optional<UserEntity> findById(Long id);

    boolean isExists(Long id);

    void delete(Long id);
}
