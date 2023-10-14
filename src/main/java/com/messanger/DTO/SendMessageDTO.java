package com.messanger.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendMessageDTO {
    private String text;
    private Long toUserId;
}
