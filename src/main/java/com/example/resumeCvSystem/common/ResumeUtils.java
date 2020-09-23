package com.example.resumeCvSystem.common;

import com.example.resumeCvSystem.entity.EducationEntity;
import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.domain.Education;
import com.example.resumeCvSystem.domain.User;

public class ResumeUtils {
    public static UserEntity userEntityBuilder(User user) {
        return UserEntity.builder()
                .name(user.getName()).age(user.getAge())
                .avatar(user.getAvatar()).description(user.getDescription()).build();
    }

    public static EducationEntity educationEntityBuilder(Education education, UserEntity user) {
        return EducationEntity.builder()
                .title(education.getTitle())
                .year(education.getYear())
                .description(education.getDescription())
                .user(user)
                .build();
    }
}
