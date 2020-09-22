package com.example.resumeCvSystem.service;

import com.example.resumeCvSystem.common.ExceptionMessage;
import com.example.resumeCvSystem.common.ResumeUtils;
import com.example.resumeCvSystem.collections.UserCollection;
import com.example.resumeCvSystem.repository.UserRepository;
import com.example.resumeCvSystem.domain.User;
import org.springframework.stereotype.Service;
import com.example.resumeCvSystem.exception.UserNotFoundException;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCollection addUser(User user) {
        UserCollection userInDb = ResumeUtils.newUserCollectionBuilder(user);
        return this.userRepository.save(userInDb);
    }

    public UserCollection findUserById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE));
    }
}
