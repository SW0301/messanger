package com.messanger.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ShowMessageDTO {
    private String from;
    private String text;
}
