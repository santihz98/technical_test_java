package com.technical_test.technical_test_java.dto;

import com.technical_test.technical_test_java.entity.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class UserRegistrationRequest {

    @NotEmpty(message = "Name is required")
    private String name;

    @Email(message = "Ingresa un correo valido (aaaaaaa@dominio.cl)")
    @NotEmpty(message = "El correo es requerido")
    private String email;

    @NotEmpty(message = "La contraseña es requerida")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$", message = "La contraseña debe contener al menos un dígito, una minúscula, una mayúscula y tener al menos 6 caracteres.")
    private String password;

    private List<Phone> phones;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
}
