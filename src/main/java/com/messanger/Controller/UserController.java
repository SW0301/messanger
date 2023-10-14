package com.messanger.Controller;

import com.messanger.DTO.ChangeProfileDTO;
import com.messanger.DTO.CreateUserDTO;
import com.messanger.DTO.LoginDTO;
import com.messanger.Model.User;
import com.messanger.Service.UserServiceImpl;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserDTO createUserDTO) {
        try{
            User user = userService.createUser(createUserDTO.getEmail(), createUserDTO.getPassword(),
                                                createUserDTO.getNickname(),
                                                createUserDTO.getFirstName(),
                                                createUserDTO.getSecondName());
            return ResponseEntity.ok(user);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("There is such email or nickname");
        }
    }

    @PutMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try{
            userService.login(loginDTO.getEmailOrNickname(), loginDTO.getPassword());
            return ResponseEntity.ok("Welcome!");
        }  catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Account is deleted or does not exists");
        } catch (SecurityException e) {
            return ResponseEntity.badRequest().body("Wrong password");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("You are already logged in");
        }
    }

    @PutMapping("/logout/{id}")
    public ResponseEntity<?> logout(@PathVariable Long id) {
        try{
            userService.logout(id);
            return ResponseEntity.ok("See you");
        } catch (SecurityException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Войдите в систему");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Something wrong");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changeProfile(@PathVariable Long id, @RequestBody ChangeProfileDTO changeProfileDTO) {
        try{
            User user = userService.changeProfile(id, changeProfileDTO.getEmail(),
                    changeProfileDTO.getNickname(), changeProfileDTO.getFirstName(), changeProfileDTO.getSecondName());
            return ResponseEntity.ok(user);
        } catch (SecurityException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Войдите в систему");
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("There is the same email or nickname");
        }

    }

    @PutMapping("/password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestParam String password) {
        try{
            User user = userService.changePassword(id, password);
            return ResponseEntity.ok(user);
        } catch(SecurityException e){
            return ResponseEntity.badRequest().body("Войдите в сисетму");
        } catch (EntityNotFoundException e ) {
            return ResponseEntity.badRequest().body("Something wrong");
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("Введите пароль");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try{
            userService.deleteUser(id);
            return ResponseEntity.ok("Bye");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("Something Wrong");
        } catch (SecurityException e) {
            return ResponseEntity.badRequest().body("Войдите в систему");
        }
    }

}
