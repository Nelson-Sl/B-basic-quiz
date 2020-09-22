package com.example.resumeCvSystem.service;

import com.example.resumeCvSystem.common.ExceptionMessage;
import com.example.resumeCvSystem.collections.UserCollection;
import com.example.resumeCvSystem.exception.UserNotFoundException;
import com.example.resumeCvSystem.exception.EducationNotFoundException;
import com.example.resumeCvSystem.repository.UserRepository;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.stereotype.Service;

@Service
public class EducationService {
    private final UserRepository userRepository;

    public EducationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCollection addEducationRecordByUserId(String userId, Education education) {
            UserCollection educationUser = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE));
            educationUser.getEducations().add(education);
            this.userRepository.save(educationUser);
            return educationUser;
    }

    public UserCollection findEducationRecordByUserId(String userId) {
        UserCollection foundUser =  this.userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE));
        if(foundUser.getEducations().isEmpty()) {
            throw new EducationNotFoundException(ExceptionMessage.EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE);
        }
        return foundUser;
    }
}
