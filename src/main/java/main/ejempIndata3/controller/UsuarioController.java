package main.ejempIndata3.controller;

import main.ejempIndata3.dto.UserDTO;
import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.serrvice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<List<UsuarioEntity>> getAllUser(){

        List<UsuarioEntity> data= userService.findAllUser();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(data);

    }

    @PostMapping("/saveDTO")
    public void postUser(@RequestBody UserDTO userDTO){
         userService.saveUser(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<UsuarioEntity> getById(@PathVariable Long id){
         Optional<UsuarioEntity> user= userService.findById(id);
        return user;
    }

    @PostMapping("/save")
    public ResponseEntity<UsuarioEntity> postUser(@RequestBody UsuarioEntity user){
        userService.saveUserN(user);
     return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(user);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("elim/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleted(id);
    }

}
