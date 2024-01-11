package main.ejempIndata3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String nombre;
    private String apellido;
    private String pass;
    private Integer edad;
}
