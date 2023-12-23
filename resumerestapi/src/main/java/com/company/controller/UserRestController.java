package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.UserDTO;
import com.company.entity.User;
import com.company.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8085/", maxAge = 3600)
@RestController
public class UserRestController {

    @Autowired
    private UserServiceInter userService;

    @GetMapping("/users")
    public ResponseEntity<ResponseDTO> getUsers(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age
    ) {
        List<User> users = userService.getAll(name, surname, age);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
//        return ResponseEntity.status(HttpStatus.OK).body("users, alma2");
    }

    @GetMapping("/foo")
    public ResponseEntity<ResponseDTO> foo(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "surname", required = false) String surname,
            @RequestParam(name = "age", required = false) Integer age
    ) {
        List<User> users = userService.getAll(name, surname, age);

        List<UserDTO> userDTOS = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            userDTOS.add(new UserDTO(u));
        }

        return ResponseEntity.ok(ResponseDTO.of(userDTOS));
//        return ResponseEntity.status(HttpStatus.OK).body("users, alma2");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseDTO> getUser(@PathVariable("id") int id) {
        User user = userService.getById(id);
        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user)));
//        return ResponseEntity.status(HttpStatus.OK).body("users, alma2");
    }

    @PostMapping("/users/{id}")//post
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id) {
        User user = userService.getById(id);
        userService.remove(id);
        return ResponseEntity.ok(ResponseDTO.of(new UserDTO(user), "Succesfully deleted"));
//        return ResponseEntity.status(HttpStatus.OK).body("users, alma2");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setPassword(userDTO.getPassword());
        userService.add(user);

        UserDTO uDTO = new UserDTO();
        uDTO.setId(user.getId());
        uDTO.setName(user.getName());
        uDTO.setSurname(user.getSurname());
        return ResponseEntity.ok(ResponseDTO.of(uDTO, "Succesfully added"));
//        return ResponseEntity.status(HttpStatus.OK).body("users, alma2");
    }
}
