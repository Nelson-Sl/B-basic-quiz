package com.example.resumeCvSystem;

import com.example.resumeCvSystem.common.GlobalVariables;
import com.example.resumeCvSystem.common.ResumeUtils;
import com.example.resumeCvSystem.domain.User;
import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.exception.UserNotFoundException;
import com.example.resumeCvSystem.repository.UserRepository;
import com.example.resumeCvSystem.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        userService = new UserService(userRepository);
        user = User.builder().age(27L)
                .name("Nelson")
                .avatar("http://www.nelson.org/nelson.jpg")
                .description("A handsome guy")
                .build();
    }

    @Nested
    class createUser {
        @Test
        void should_add_user_successfully() {
            UserEntity expectedUser = ResumeUtils.userEntityBuilder(user);
            when(userRepository.save(any(UserEntity.class))).thenReturn(expectedUser);

            UserEntity savedUser = userService.addUser(user);
            Assertions.assertEquals(expectedUser, savedUser);

        }
    }

    @Nested
    class findUserByUserId {
        @Test
        void should_find_user_if_user_exists() {
            UserEntity expectedUser = ResumeUtils.userEntityBuilder(user);
            when(userRepository.findById(123L)).thenReturn(Optional.of(expectedUser));

            UserEntity foundUser = userService.findUserById(123L);
            Assertions.assertEquals(expectedUser, foundUser);
        }

        @Test
        void should_throw_not_found_if_user_not_exist() {
            when(userRepository.findById(123L)).thenReturn(Optional.empty());

            UserNotFoundException thrownException = Assertions.assertThrows(UserNotFoundException.class,
                    () -> userService.findUserById(123L));

            assertThat(thrownException.getMessage()).isEqualTo(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}
