package com.example.resumeCvSystem.Repository;

import com.example.resumeCvSystem.Common.ExceptionMessage;
import com.example.resumeCvSystem.Common.GlobalVariables;
import com.example.resumeCvSystem.Exception.NewUserInfoInvalidException;
import com.example.resumeCvSystem.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public synchronized User addUser(User user) {
        checkUserInfoLength(user);
        // GTB: 建议 id 使用专用的字段来生成
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

    // GTB: 可以通过自定义的 annotation 来实现，了解一下
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

    // GTB: - 用 Optional 的话，可以让 getUserById 直接返回 Optional，或者直接抛出异常；
    public User getUserById(Long id) {
        Optional<User> userById = userList.stream().filter(user -> id == user.getUserId())
                .findFirst();
        return userById.isPresent() ? userById.get() : null;
    }

    public List<User> getUserList() {
        return this.userList;
    }
}
