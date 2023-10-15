package com.messanger.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeProfileDTO {

    @Email
    @NotBlank
    private String email;

    private String nickname;

    private String firstName;

    private String secondName;

}
