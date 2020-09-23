package com.example.resumeCvSystem;

import com.example.resumeCvSystem.entity.EducationEntity;
import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.repository.EducationRepository;
import com.example.resumeCvSystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
public class EducationRepositoryTest {
    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private UserEntity user;
    private EducationEntity education;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
        user = UserEntity.builder()
                .age(27L)
                .name("Nelson")
                .avatar("http://www.nelson.org/nelson.jpg")
                .description("A handsome boy")
                .build();
        userRepository.save(user);

        education = EducationEntity.builder()
                .year(2005L)
                .title("TsingHua University")
                .description("Graduate from bachelor of computer science")
                .user(user)
                .build();
        entityManager.persistAndFlush(education);
    }

    @Test
    void should_find_education_when_userid_exists() {
        Optional<List<EducationEntity>> userEducation = educationRepository.findAllByUser(1L);
        List<EducationEntity> expectedResult = new ArrayList<>();
        expectedResult.add(EducationEntity.builder()
                .id(1L)
                .year(2005L)
                .title("TsingHua University")
                .description("Graduate from bachelor of computer science")
                .user(user)
                .build());

        assertThat(userEducation.isPresent()).isEqualTo(true);
        assertThat(userEducation.get()).isEqualTo(expectedResult);
    }

    @Test
    void should_not_find_education_when_userid_not_exist() {
        Optional<List<EducationEntity>> userEducation = educationRepository.findAllByUser(3L);
        assertThat(userEducation.isPresent()).isEqualTo(false);
    }
}
