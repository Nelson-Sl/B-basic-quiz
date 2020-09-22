package com.example.resumeCvSystem.common;

import com.example.resumeCvSystem.collections.UserCollection;
import com.example.resumeCvSystem.domain.User;

import java.util.ArrayList;

public class ResumeUtils {
    public static UserCollection newUserCollectionBuilder(User user) {
        return UserCollection.builder()
                .name(user.getName()).age(user.getAge())
                .avatar(user.getAvatar())
                .description(user.getDescription())
                .educations(new ArrayList<>())
                .build();
    }
}
