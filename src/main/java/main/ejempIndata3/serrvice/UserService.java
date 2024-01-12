package main.ejempIndata3.serrvice;

import main.ejempIndata3.dto.UserDTO;
import main.ejempIndata3.entity.UsuarioEntity;
import main.ejempIndata3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    UsuarioEntity usuarioEntity;
    public List<UsuarioEntity> findAllUser() {
        return (List<UsuarioEntity>) userRepository.findAll();
    }

    public UsuarioEntity saveUser(UserDTO user) {
        UsuarioEntity data=UsuarioEntity.builder()
                .name(user.getNombre())
                .lastname(user.getApellido())
                .password(user.getPass())
                .build();
        userRepository.save(data);
        return data;

    }
    public UsuarioEntity saveUserN(UsuarioEntity user) {

        userRepository.save(user);


        return user;
    }

    public Optional<UsuarioEntity> findById(Long id) {
        return userRepository.findById(id);
    }
}
