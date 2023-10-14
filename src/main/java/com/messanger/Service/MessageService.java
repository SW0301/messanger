package com.messanger.Service;

import com.messanger.DTO.ShowMessageDTO;
import com.messanger.Model.Message;

import java.util.List;

public interface MessageService {
    void sendMessage(Long fromUserId, Long toUserId, String text);
    List<ShowMessageDTO> showMessages(Long fromUserId, Long toUserId);
}
