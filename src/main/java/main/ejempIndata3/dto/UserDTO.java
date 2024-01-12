package main.ejempIndata3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
@Component
public class UserDTO {
    private String nombre;
    private String apellido;
    private String pass;
    private Integer edad;
}
