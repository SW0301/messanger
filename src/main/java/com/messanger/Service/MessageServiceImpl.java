package com.messanger.Service;

import com.messanger.DTO.ShowMessageDTO;
import com.messanger.Model.User;
import com.messanger.Repository.MessageRepository;
import com.messanger.Model.Message;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MessageServiceImpl implements MessageService{
    private final MessageRepository messageRepository;
    private final UserServiceImpl userService;

    public MessageServiceImpl(MessageRepository messageRepository, UserServiceImpl userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    @Override
    public void sendMessage(Long fromUserId, Long toUserId, String text) {
        try{
            User user = userService.getUserById(fromUserId);
            User user2 = userService.getUserById(toUserId);
            if(!user.isActive())
                throw new SecurityException();
            if(user2.isDeleted())
                throw new EntityNotFoundException();
        } catch (EntityNotFoundException e){
            throw e;
        }
        Message message = new Message();
        message.setFromUserId(fromUserId);
        message.setToUserId(toUserId);
        message.setText(text);
        messageRepository.save(message);
    }

    @Override
    public List<ShowMessageDTO> showMessages(Long fromUserId, Long toUserId) {
        try{
            User user = userService.getUserById(fromUserId);
            User user2 = userService.getUserById(toUserId);
            if(!user.isActive())
                throw new SecurityException();
            if(user2.isDeleted())
                throw new EntityNotFoundException();
            List<Message> messages = messageRepository.findMessageByFromUserIdAndToUserId(fromUserId, toUserId);
            List<ShowMessageDTO> showMessage = new ArrayList<>();
            for(Message m : messages) {
                showMessage.add(new ShowMessageDTO(userService.getUserById(m.getFromUserId()).getNickname(), m.getText()));
            }
            return showMessage;
        } catch (EntityNotFoundException e){
            throw e;
        }
    }
}
