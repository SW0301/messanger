package com.messanger.DTO;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {
    private String emailOrNickname;
    private String password;
}
