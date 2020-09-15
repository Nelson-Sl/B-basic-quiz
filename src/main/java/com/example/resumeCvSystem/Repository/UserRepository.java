package com.example.resumeCvSystem.Repository;

import com.example.resumeCvSystem.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> userList = new ArrayList<>();

    public synchronized User addUser(User user) {
        long userId = (long) userList.size() + 1;
        user.setUserId(userId);
        userList.add(user);
        return user;
    }
}
