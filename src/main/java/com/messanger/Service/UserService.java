package com.messanger.Service;

import com.messanger.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getUserById(Long id);
    User createUser(String email, String password, String nickname, String firstName, String secondName);
    User login(String emailOrNickname, String password);
    void logout(Long id);
    User changeProfile(Long id, String email, String nickname, String firstName, String secondName);
    User changePassword(Long id, String password);
    void deleteUser(Long id);
}
