package com.messanger.Service;

import com.messanger.Model.User;
import com.messanger.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        try {
            return userRepository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public User createUser(String email, String password, String nickname, String firstName, String secondName) {
        User user = new User();

        if (email != null && email.length() != 0)
            user.setEmail(email);
        else throw new NullPointerException("Введите email");

        if (nickname != null && nickname.length() != 0)
            user.setNickname(nickname);
        else throw new NullPointerException("Введите nickname");

        if (userRepository.getUserByEmailOrNickname(email).isPresent())
            throw new EntityNotFoundException();

        if (password != null && password.length() != 0)
            user.setPassword(password);
        else throw new NullPointerException("Введите пароль");

        if (firstName != null && firstName.length() != 0)
            user.setFirstName(firstName);
        else throw new NullPointerException("Введите имя");

        if (secondName != null && secondName.length() != 0)
            user.setSecondName(secondName);
        else throw new NullPointerException("Введите фамилию");

        user.setActive(true);
        user.setDeleted(false);
        userRepository.save(user);
        return user;
    }

    @Override
    public User login(String emailOrNickname, String password) {
        try {
            if(!userRepository.getUserByEmailOrNickname(emailOrNickname).isPresent())
                throw new EntityNotFoundException();
            User user = userRepository.getUserByEmailOrNickname(emailOrNickname).get();
            if (user.isDeleted()) throw new EntityNotFoundException();
            if (user.isActive()) throw new RuntimeException();
            if (user.getPassword().equals(password))
                user.setActive(true);
            else throw new SecurityException();
            userRepository.save(user);
            return user;
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public void logout(Long id) {
        try {
            User user = userRepository.getReferenceById(id);
            user.setActive(false);
            userRepository.save(user);
        } catch (EntityNotFoundException e) {
            throw e;
        }

    }

    @Override
    public User changeProfile(Long id, String email, String nickname, String firstName, String secondName) {
        try {
            User user = userRepository.getReferenceById(id);

            if (!user.isActive())
                throw new SecurityException();
            if (userRepository.getUserByEmailOrNickname(email).isPresent())
                throw new EntityNotFoundException();
            // exception на уникальность
            if (email != null)
                user.setEmail(email);
            if (nickname != null)
                user.setNickname(nickname);
            if (firstName != null)
                user.setFirstName(firstName);
            if (secondName != null)
                user.setSecondName(secondName);
            userRepository.save(user);
            return user;
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }

    @Override
    public User changePassword(Long id, String password) {
        try {
            User user = userRepository.getReferenceById(id);
            if (!user.isActive())
                throw new SecurityException();
            if (password != null && password.length() != 0)
                user.setPassword(password);
            else throw new NullPointerException();
            userRepository.save(user);
            return user;
        } catch (EntityNotFoundException e) {
            throw e;
        }

    }

    @Override
    public void deleteUser(Long id) {
        try {
            User user = userRepository.getReferenceById(id);
            if (!user.isActive())
                throw new SecurityException();
            if(user.isDeleted())
                throw new EntityNotFoundException();
            user.setDeleted(true);
            user.setActive(false);
        } catch (EntityNotFoundException e) {
            throw e;
        }
    }
}
