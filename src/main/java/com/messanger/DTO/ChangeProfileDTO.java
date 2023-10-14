package com.messanger.DTO;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChangeProfileDTO {
    @Nullable
    private String email;
    @Nullable
    private String nickname;
    @Nullable
    private String firstName;
    @Nullable
    private String secondName;

}
