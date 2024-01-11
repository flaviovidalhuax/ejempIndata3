package main.ejempIndata3.controller;

import main.ejempIndata3.dto.UserDTO;
import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.serrvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/user")
public class UsuarioController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UsuarioEntity> getAllUser(){
        return userService.findAllUser();
    }

    @PostMapping("/save")
    public void postUser(@RequestBody UserDTO userDTO){
         userService.saveUser(userDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<UsuarioEntity>> getById(@PathVariable Long id){
         Optional<UsuarioEntity> user= userService.findById(id);
        return ResponseEntity.ok(user);
    }

}
