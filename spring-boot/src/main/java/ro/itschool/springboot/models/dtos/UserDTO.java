package ro.itschool.springboot.models.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private String name;
    @NotNull(message = "Email is a mandatory field")
    private String email;
    @Min(18)
    @Max(100)
    private int age;
}