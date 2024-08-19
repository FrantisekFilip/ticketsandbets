package cz.frantisekfilip.ticketsandbets.services.impl;

import cz.frantisekfilip.ticketsandbets.domain.entities.TicketEntity;
import cz.frantisekfilip.ticketsandbets.domain.entities.UserEntity;

import cz.frantisekfilip.ticketsandbets.repository.UserRepository;
import cz.frantisekfilip.ticketsandbets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity user) {
        for (TicketEntity ticket : user.getTickets()) {
            if (!ticket.getUserId().equals(user.getId())) {
                throw new IllegalArgumentException("Ticket userId does not match User ID.");
            }
        }
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
//        List<UserEntity> userList = new ArrayList<>();
//        userRepository.findAll().forEach(userList::add);
        List<UserEntity> userList = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return userList;
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
