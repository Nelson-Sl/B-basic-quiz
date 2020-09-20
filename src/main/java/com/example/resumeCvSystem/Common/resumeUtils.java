package com.example.resumeCvSystem.Common;

import com.example.resumeCvSystem.Entity.EducationEntity;
import com.example.resumeCvSystem.Entity.UserEntity;
import com.example.resumeCvSystem.domain.Education;
import com.example.resumeCvSystem.domain.User;

public class resumeUtils {
    public static UserEntity userEntityBuilder(User user) {
        return UserEntity.builder()
                .name(user.getName()).age(user.getAge())
                .avatar(user.getAvatar()).description(user.getDescription()).build();
    }

    public static EducationEntity educationEntityBuilder(Education education, UserEntity user) {
        return EducationEntity.builder()
                .title(education.getTitle()).year(education.getYear())
                .description(education.getDescription()).user(user).build();
    }
}
