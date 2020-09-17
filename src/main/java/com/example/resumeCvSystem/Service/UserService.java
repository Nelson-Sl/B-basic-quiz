package com.example.resumeCvSystem.Service;

import com.example.resumeCvSystem.Repository.UserRepository;
import com.example.resumeCvSystem.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return this.userRepository.addUser(user);
    }

    public User getUserById(Long id) {
        return this.userRepository.getUserById(id);
    }
}
