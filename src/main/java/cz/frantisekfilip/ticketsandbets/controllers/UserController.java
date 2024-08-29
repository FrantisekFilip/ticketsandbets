package cz.frantisekfilip.ticketsandbets.controllers;

import cz.frantisekfilip.ticketsandbets.domain.dto.UserDto;
import cz.frantisekfilip.ticketsandbets.domain.entities.UserEntity;
import cz.frantisekfilip.ticketsandbets.mappers.impl.UserMapperImpl;
import cz.frantisekfilip.ticketsandbets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private UserMapperImpl userMapper;

    @Autowired
    public UserController(UserService userService, UserMapperImpl userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody final UserDto user) {
        UserDto userDto = userMapper.mapTo(userService.save(userMapper.mapFrom(user)));
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("")
    public List<UserDto> listAuthors(){
        List<UserEntity> users = userService.findAll();
        return users.stream().map(userMapper::mapTo).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long id){
        Optional<UserEntity> foundUser = userService.findById(id);
//        return foundUser.map(userEntity -> {
//            UserDto userDto = userMapper.mapTo(userEntity);
//            return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
//        })
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return foundUser.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<UserDto>(userMapper.mapTo(foundUser.get()), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody final UserDto user){
        if (!userService.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        UserEntity updatedUser = userService.save(userMapper.mapFrom(user));
        return new ResponseEntity<>(userMapper.mapTo(updatedUser), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
