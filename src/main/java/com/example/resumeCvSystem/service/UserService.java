package com.example.resumeCvSystem.service;

import com.example.resumeCvSystem.common.ExceptionMessage;
import com.example.resumeCvSystem.common.resumeUtils;
import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.repository.UserRepository;
import com.example.resumeCvSystem.domain.User;
import org.springframework.stereotype.Service;
import com.example.resumeCvSystem.exception.userNotFoundException;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity addUser(User user) {
        UserEntity userInDb = resumeUtils.userEntityBuilder(user);
        return this.userRepository.save(userInDb);
    }

    public UserEntity findUserById(Long id) {
        Optional<UserEntity> userFound = this.userRepository.findById(id);
        return userFound.orElseThrow(() -> new userNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE));
    } //Exception首字母大写，包名小写
}
