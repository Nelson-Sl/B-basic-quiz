package com.example.resumeCvSystem.Service;

import com.example.resumeCvSystem.Common.ExceptionMessage;
import com.example.resumeCvSystem.Common.resumeUtils;
import com.example.resumeCvSystem.Entity.UserEntity;
import com.example.resumeCvSystem.Repository.UserRepository;
import com.example.resumeCvSystem.domain.User;
import org.springframework.stereotype.Service;
import com.example.resumeCvSystem.Exception.userNotFoundException;

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
