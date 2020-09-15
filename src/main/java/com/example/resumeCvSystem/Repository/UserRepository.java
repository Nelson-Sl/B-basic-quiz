package com.example.resumeCvSystem.Repository;

import com.example.resumeCvSystem.Common.ExceptionMessage;
import com.example.resumeCvSystem.Common.GlobalVariables;
import com.example.resumeCvSystem.Exception.NewUserInfoInvalidException;
import com.example.resumeCvSystem.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public synchronized User addUser(User user) {
        checkUserInfoLength(user);
        long userId = (long) userList.size() + 1;
        user.setUserId(userId);
        userList.add(user);
        return user;
    }

    private void checkUserInfoLength(User user) {
        checkUserNameLength(user);
        checkUserAvatarLength(user);
        checkUserDescriptionLength(user);
    }

    private void checkUserNameLength(User user) {
        byte[] userNameBytes = user.getName().getBytes();
        if(userNameBytes.length < GlobalVariables.MINIMUM_NAME_BYTES ||
                userNameBytes.length > GlobalVariables.MAXIMUM_NAME_BYTES) {
            throw new NewUserInfoInvalidException(ExceptionMessage.USER_NAME_INVALID_EXCEPTION_MESSAGE);
        }
    }

    private void checkUserAvatarLength(User user) {
        byte[] userAvatarBytes = user.getAvatar().getBytes();
        if(userAvatarBytes.length < GlobalVariables.MINIMUM_AVATAR_BYTES ||
                userAvatarBytes.length > GlobalVariables.MAXIMUM_AVATAR_BYTES) {
            throw new NewUserInfoInvalidException(ExceptionMessage.USER_AVATAR_INVALID_EXCEPTION_MESSAGE);
        }
    }

    private void checkUserDescriptionLength(User user) {
        byte[] userDescriptionBytes = user.getDescription().getBytes();
        if(userDescriptionBytes.length > GlobalVariables.MAXIMUM_DESC_BYTES) {
            throw new NewUserInfoInvalidException(ExceptionMessage.USER_DESCRIPTION_INVALID_EXCEPTION_MESSAGE);
        }
    }
}
