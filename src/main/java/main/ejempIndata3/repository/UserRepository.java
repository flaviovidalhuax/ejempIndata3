package main.ejempIndata3.repository;

import main.ejempIndata3.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UsuarioEntity, Long> {
}
