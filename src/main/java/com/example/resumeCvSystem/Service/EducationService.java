package com.example.resumeCvSystem.Service;

import com.example.resumeCvSystem.Common.ExceptionMessage;
import com.example.resumeCvSystem.Common.resumeUtils;
import com.example.resumeCvSystem.Entity.EducationEntity;
import com.example.resumeCvSystem.Entity.UserEntity;
import com.example.resumeCvSystem.Exception.userNotFoundException;
import com.example.resumeCvSystem.Exception.educationNotFoundException;
import com.example.resumeCvSystem.Repository.EducationRepository;
import com.example.resumeCvSystem.Repository.UserRepository;
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
        if(isUserExisted(userId)) {
            UserEntity educationUser = this.userRepository.findById(userId).get();
            EducationEntity educationInDb = resumeUtils.educationEntityBuilder(education, educationUser);
            return this.educationRepository.save(educationInDb);
        }
        throw new userNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE);

    }

    public List<EducationEntity> findEducationRecordByUserId(Long userId) {
        if(isUserExisted(userId)) {
            return this.educationRepository.findAllByUser(userId)
                    .orElseThrow(() -> new educationNotFoundException(ExceptionMessage.EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE));
        }
        throw new userNotFoundException(ExceptionMessage.USER_NOT_FOUND_EXCEPTION_MESSAGE);
    }

    private boolean isUserExisted(Long userId) {
        return this.userRepository.existsById(userId);
    }
}
