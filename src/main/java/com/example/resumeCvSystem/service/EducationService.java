package com.example.resumeCvSystem.service;

import com.example.resumeCvSystem.common.ExceptionMessage;
import com.example.resumeCvSystem.common.ResumeUtils;
import com.example.resumeCvSystem.entity.EducationEntity;
import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.exception.UserNotFoundException;
import com.example.resumeCvSystem.exception.EducationNotFoundException;
import com.example.resumeCvSystem.repository.EducationRepository;
import com.example.resumeCvSystem.repository.UserRepository;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;

    public EducationService(EducationRepository educationRepository, UserRepository userRepository) {
        this.educationRepository = educationRepository;
        this.userRepository = userRepository;
    }

    public EducationEntity addEducationRecordByUserId(Long userId, Education education) {
            UserEntity educationUser = this.userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE));
            EducationEntity educationInDb = ResumeUtils.educationEntityBuilder(education, educationUser);
            return this.educationRepository.save(educationInDb);
    }

    public List<EducationEntity> findEducationRecordByUserId(Long userId) {
        if(isUserExisted(userId)) {
            return this.educationRepository.findAllByUser(userId)
                    .orElseThrow(() -> new EducationNotFoundException(ExceptionMessage.EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE));
        }
        throw new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    private boolean isUserExisted(Long userId) {
        return this.userRepository.existsById(userId);
    }
}
