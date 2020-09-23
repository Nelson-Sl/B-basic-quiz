package com.example.resumeCvSystem;

import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    public void setUp() {
        entityManager.persistAndFlush(UserEntity.builder()
                .age(27L)
                .name("Nelson")
                .avatar("http://www.nelson.org/nelson.jpg")
                .description("A handsome boy")
                .build()
        );
    }

    @Test
    void should_return_user_when_id_exists() {
        Optional<UserEntity> foundUser = userRepository.findById(1L);

        assertThat(foundUser.isPresent()).isEqualTo(true);
        assertThat(foundUser.get()).isEqualTo(UserEntity.builder()
                .id(1L)
                .age(27L)
                .name("Nelson")
                .avatar("http://www.nelson.org/nelson.jpg")
                .description("A handsome boy")
                .build());
    }

    @Test
    void should_return_empty_when_id_not_exist() {
        Optional<UserEntity> foundUser = userRepository.findById(5L);
        assertThat(foundUser.isPresent()).isEqualTo(false);
    }

}
