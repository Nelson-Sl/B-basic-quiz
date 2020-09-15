package com.example.resumeCvSystem.Repository;

import com.example.resumeCvSystem.Common.ExceptionMessage;
import com.example.resumeCvSystem.Common.GlobalVariables;
import com.example.resumeCvSystem.Exception.NewUserEducationInfoInvalidException;
import com.example.resumeCvSystem.domain.Education;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EducationRepository {
    private List<Education> educations = new ArrayList<>();
    private final UserRepository userRepository;

    public EducationRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Education addEducationRecordById(long id, Education education) {
        boolean isUserExisted = userRepository.getUserList().stream().anyMatch(user -> user.getUserId() == id);
        if(isUserExisted) {
            Education newEducation = Education.builder().userId(id).year(education.getYear())
                    .title(education.getTitle()).description(education.getDescription()).build();
            checkEducationInfoLength(newEducation);
            educations.add(newEducation);
            return newEducation;
        }
        return null;
    }

    private void checkEducationInfoLength(Education newEducation) {
        checkEducationTitleLength(newEducation);
        checkEducationDescLength(newEducation);
    }

    private void checkEducationTitleLength(Education newEducation) {
        byte[] educationTitleBytes = newEducation.getTitle().getBytes();
        if(educationTitleBytes.length < GlobalVariables.MINIMUM_EDUCATION_TITLE_BYTES ||
                educationTitleBytes.length > GlobalVariables.MAXIMUM_EDUCATION_TITLE_BYTES) {
            throw new NewUserEducationInfoInvalidException(ExceptionMessage.USER_EDUCATION_TITLE_INVALID_EXCEPTION_MESSAGE);
        }
    }

    private void checkEducationDescLength(Education newEducation) {
        byte[] educationDescBytes = newEducation.getDescription().getBytes();
        if(educationDescBytes.length < GlobalVariables.MINIMUM_EDUCATION_DESC_BYTES ||
                educationDescBytes.length > GlobalVariables.MAXIMUM_EDUCATION_DESC_BYTES) {
            throw new NewUserEducationInfoInvalidException(ExceptionMessage.USER_EDUCATION_DESC_INVALID_EXCEPTION_MESSAGE);
        }
    }
}
