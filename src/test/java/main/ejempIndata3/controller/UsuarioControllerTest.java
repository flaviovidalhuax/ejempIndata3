package main.ejempIndata3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.serrvice.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;  //when
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // get, post, put, patch

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
@SpringJUnitConfig
class UsuarioControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserService userService;
    UsuarioEntity usuarioEntity;

    @Autowired
    private ObjectMapper objectMapper;
    //static ObjectMapper objectMapper;
//    @BeforeAll
//    static void beforeAll(){
//        objectMapper=new ObjectMapper();
//    }
    @BeforeEach
    void setUp(){
        usuarioEntity= new UsuarioEntity();
        usuarioEntity.setId(1L);
        usuarioEntity.setName("juan");
        usuarioEntity.setLastname("perez");
        usuarioEntity.setPassword("1234");
    }

    @Test
    void findByID() throws Exception{
        UsuarioEntity dat=UsuarioEntity.builder()
                .id(2L)
                .name("juan")
                .lastname("perez")
                .password("1234")
                .build();

        when(userService.findById(2L)).thenReturn(Optional.ofNullable(dat));
        mvc.perform(get("/v1/user/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("juan"))
                .andExpect(jsonPath("$.lastname").value("perez"))
                .andExpect(jsonPath("$.password").value("1234"));
    }

    @Test
    void Save() throws Exception{
        UsuarioEntity dat=UsuarioEntity.builder()
                .id(2L)
                .name("juan")
                .lastname("perez")
                .password("1234")
                .build();
//
        //when(userService.saveUserN(any(UsuarioEntity.class))).thenReturn(dat);

        mvc.perform(post("/v1/user/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dat)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("juan"))
                .andExpect(jsonPath("$.lastname").value("perez"))
                .andExpect(jsonPath("$.password").value("1234"));
    }
    @Test
    void  deletesUSerTest() throws Exception{
        UsuarioEntity dat=UsuarioEntity.builder()
                .id(2L)
                .name("juan")
                .lastname("perez")
                .password("1234")
                .build();

        mvc.perform(delete("/v1/user/elim/{id}", dat.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void allUSer() throws Exception {

        List<UsuarioEntity> userList = Arrays.asList(
                UsuarioEntity.builder().id(1L).name("John").lastname("Doe").password("pass123").build(),
                UsuarioEntity.builder().id(2L).name("Jane").lastname("Smith").password("pass456").build()
        );
                // Agrega más usuarios según sea necesario
                mvc.perform(get("/v1/user").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$[0].name").value("John"))
                        .andExpect(jsonPath("$[0].lastname").value("Doe"))
                        .andExpect(jsonPath("$[0].password").value("pass123"))
                        .andExpect(jsonPath("$[1].name").value("Jane"))
                        .andExpect(jsonPath("$[1].lastname").value("Smith"))
                        .andExpect(jsonPath("$[1].password").value("pass456"));



    }
}