package main.ejempIndata3.serrvice;

import main.ejempIndata3.dto.UserDTO;
import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;  //maunal config when
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @MockBean
    UserRepository userRepository;
    @Autowired
    UserService userService;


    @Test
    @DisplayName("saveUser testing")
    void saveUser(){
        //give
        UsuarioEntity user=UsuarioEntity.builder()
                .id(1L)
                .name("juan")
                .lastname("perez")
                .password("1234")
                .build();
        when(userRepository.save(user)).thenReturn(user);
        //where
        UsuarioEntity data =userService.saveUserN(user);
        //then
        assertNotNull(data);
        assertEquals(1L, data.getId());
        assertEquals("juan", data.getName());
        assertEquals("perez", data.getLastname());
    }

}