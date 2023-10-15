package com.messanger.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreateUserDTO {
    @Email
    @NotBlank
    private String email;

    private String password;

    private String nickname;

    private String firstName;

    private String secondName;
}
