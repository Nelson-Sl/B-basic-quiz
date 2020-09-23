package com.example.resumeCvSystem;

import com.example.resumeCvSystem.common.GlobalVariables;
import com.example.resumeCvSystem.common.ResumeUtils;
import com.example.resumeCvSystem.domain.Education;
import com.example.resumeCvSystem.domain.User;
import com.example.resumeCvSystem.entity.EducationEntity;
import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.exception.EducationNotFoundException;
import com.example.resumeCvSystem.exception.UserNotFoundException;
import com.example.resumeCvSystem.repository.EducationRepository;
import com.example.resumeCvSystem.repository.UserRepository;
import com.example.resumeCvSystem.service.EducationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EducationServiceTest {
    private EducationService educationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EducationRepository educationRepository;

    private UserEntity user;
    private Education education;

    @BeforeEach
    public void setUp() {
        educationService = new EducationService(educationRepository, userRepository);
        user = UserEntity.builder()
                .id(123L)
                .age(27L)
                .name("Nelson")
                .avatar("http://www.nelson.org/nelson.jpg")
                .description("A handsome guy")
                .build();

        education = Education.builder()
                .year(2005L)
                .description("University of Tsinghua")
                .title("Get Bachelor Degree")
                .build();
    }

    @Nested
    class createEducation {
        @Test
        void should_add_education_successfully_when_user_exists() {
            EducationEntity expectedEducation = ResumeUtils.educationEntityBuilder(education, user);

            when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
            when(educationRepository.save(any(EducationEntity.class))).thenReturn(expectedEducation);

            EducationEntity educationSaved = educationService.addEducationRecordByUserId(123L, education);
            Assertions.assertEquals(expectedEducation, educationSaved);

        }

        @Test
        void should_throw_exception_when_user_not_exist() {
            when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

            UserNotFoundException thrownException = Assertions.assertThrows(UserNotFoundException.class,
                    () -> educationService.addEducationRecordByUserId(123L,education));

            assertThat(thrownException.getMessage()).isEqualTo(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE);

        }
    }

    @Nested
    class findEducationsByUserId {
        @Test
        void should_find_user_if_user_and_education_exists() {
            List<EducationEntity> educations = new ArrayList<>();
            educations.add(ResumeUtils.educationEntityBuilder(education, user));

            when(userRepository.existsById(anyLong())).thenReturn(true);
            when(educationRepository.findAllByUser(anyLong())).thenReturn(Optional.of(educations));

            List<EducationEntity> foundEducations = educationService.findEducationRecordByUserId(123L);
            Assertions.assertEquals(educations, foundEducations);
        }

        @Test
        void should_throw_user_not_found_if_user_not_exist() {
            when(userRepository.existsById(anyLong())).thenReturn(false);

            UserNotFoundException thrownException = Assertions.assertThrows(UserNotFoundException.class,
                    () -> educationService.findEducationRecordByUserId(123L));

            assertThat(thrownException.getMessage()).isEqualTo(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        @Test
        void should_throw_education_not_found_if_education_not_exist() {
            when(userRepository.existsById(anyLong())).thenReturn(true);
            when(educationRepository.findAllByUser(anyLong())).thenReturn(Optional.empty());

            EducationNotFoundException thrownException = Assertions.assertThrows(EducationNotFoundException.class,
                    () -> educationService.findEducationRecordByUserId(123L));

            assertThat(thrownException.getMessage()).isEqualTo(GlobalVariables.EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }
}
