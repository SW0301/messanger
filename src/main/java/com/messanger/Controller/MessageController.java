package com.messanger.Controller;


import com.messanger.DTO.SendMessageDTO;
import com.messanger.Service.MessageServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    private final MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    // send message
    // show messages with id

    @PostMapping("/message/{id}")
    public ResponseEntity<?> sendMessage(@PathVariable Long id, @RequestBody SendMessageDTO sendMessageDTO) {
        try{
            messageService.sendMessage(id, sendMessageDTO.getToUserId(), sendMessageDTO.getText());
            return ResponseEntity.ok("The message has been sent");
        } catch (EntityNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Can not find user with this id");
        } catch (SecurityException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Войдите в систему");
        }
    }

    @GetMapping("/message/{id}")
    public ResponseEntity<?> showMessages(@PathVariable Long id, @RequestParam Long toUserid) {
        try{
            return ResponseEntity.ok(messageService.showMessages(id, toUserid));
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Can not find user with what id");
        } catch (SecurityException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Войдите в систему");
        }
    }
}
