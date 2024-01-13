package main.ejempIndata3.serrvice;

import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;  //maunal config when
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @MockBean
    UserRepository userRepository;
    @Autowired
    UserService userService;
    static UsuarioEntity usuario1;
    @BeforeEach
     void setUp() {
        usuario1=new UsuarioEntity();
        usuario1.setId(5L);
        usuario1.setName("juan");
        usuario1.setLastname("perez");
        usuario1.setPassword("1234");
    }

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
        //verify(userRepository,times(1)).save(user);
        //where
        UsuarioEntity data =userService.saveUserN(user);
        //then
        assertNotNull(data);
        assertEquals(1L, data.getId());
        assertEquals("juan", data.getName());
        assertEquals("perez", data.getLastname());
    }

    @Test
    void findAll(){
        List<UsuarioEntity> data=Arrays.asList(new UsuarioEntity(1l, "juan", "perez","1234"),new UsuarioEntity(2L, "maria", "angeles", "1234"));
        when(userRepository.findAll()).thenReturn(data);
        assertNotNull(userService.findAllUser());
        assertEquals(2, userService.findAllUser().size());
    }
    @Test
    void deltedTest(){
        doNothing().when(userRepository).deleteById(usuario1.getId());
        userService.deleted(usuario1.getId());
        verify(userRepository,times(1)).deleteById(usuario1.getId());

    }
    @Test
    void findById(){
        when(userRepository.findById(usuario1.getId())).thenReturn(Optional.ofNullable(usuario1));
        Optional<UsuarioEntity> data=userService.findById(usuario1.getId());
        assertNotNull(data);
        assertEquals(5L,data.orElseThrow().getId());
        assertEquals("juan",data.orElseThrow().getName());
        assertEquals("perez",data.orElseThrow().getLastname());
        verify(userRepository,times(1) ).findById(usuario1.getId());
    }

}