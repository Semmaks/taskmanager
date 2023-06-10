package com.semenov.taskmanager.service;

import com.semenov.taskmanager.model.User;
import com.semenov.taskmanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // TODO: 10.06.2023 сделать возможность добавления пользователей с ролью ROLE_ADMIN
    /**
     * Метод добавление/регистрации пользователя
     * Если передаваемый клиент уже есть, возвращается false
     * @param user
     */
    public boolean createUser(User user) {
        String userLogin = user.getLogin();
        if (userRepository.findByLogin(userLogin) != null) return false;
        user.getRoles().add(User.Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
