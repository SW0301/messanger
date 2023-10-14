package com.messanger.DTO;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CreateUserDTO {

    private String email;

    private String password;

    private String nickname;

    private String firstName;

    private String secondName;
}
