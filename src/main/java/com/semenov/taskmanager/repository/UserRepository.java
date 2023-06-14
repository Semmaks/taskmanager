package com.semenov.taskmanager.repository;

import com.semenov.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // todo: переписать под Optional
    User findByLogin(String login);
}
