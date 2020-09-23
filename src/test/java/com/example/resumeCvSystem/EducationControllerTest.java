package com.example.resumeCvSystem;

import com.example.resumeCvSystem.common.GlobalVariables;
import com.example.resumeCvSystem.common.ResumeUtils;
import com.example.resumeCvSystem.controller.EducationController;
import com.example.resumeCvSystem.controller.UserController;
import com.example.resumeCvSystem.domain.Education;
import com.example.resumeCvSystem.domain.User;
import com.example.resumeCvSystem.entity.EducationEntity;
import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.exception.EducationNotFoundException;
import com.example.resumeCvSystem.exception.UserNotFoundException;
import com.example.resumeCvSystem.service.EducationService;
import com.example.resumeCvSystem.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EducationController.class)
@AutoConfigureJsonTesters
class EducationControllerTest {
	@MockBean
	private EducationService educationService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JacksonTester<Education> educationJson;

	@Autowired
	private JacksonTester<EducationEntity> educationEntityJson;

	@Autowired
	private JacksonTester<List<EducationEntity>> educationEntityListJson;

	private List<EducationEntity> educationRecords;
	private EducationEntity educationRecord;
	private Education firstEducationRecord;

	@Nested
	class createEducationRecord {
		@BeforeEach
		public void beforeEach() {
			firstEducationRecord = Education.builder()
					.year(2005L)
					.title("Secondary school specializing in artistic")
					.description("Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.")
					.build();
			User firstUser = User.builder()
					.name("Nelson")
					.age(27L)
					.avatar("http://www.nelson.com/nelson.jpg")
					.description("A handsome boy").build();
			educationRecord =
					ResumeUtils.educationEntityBuilder(firstEducationRecord,ResumeUtils.userEntityBuilder(firstUser));
		}

		@AfterEach
		public void afterEach() {
			Mockito.reset(educationService);
		}

		@Test
		void should_return_education_record_if_user_existed_and_successfully_added() throws Exception {
			when(educationService.addEducationRecordByUserId(123L, firstEducationRecord))
					.thenReturn(educationRecord);
			MockHttpServletResponse response = mockMvc.perform(post("/users/{id}/educations",123)
					.contentType(MediaType.APPLICATION_JSON)
					.content(educationJson.write(firstEducationRecord).getJson()))
					.andReturn().getResponse();

			verify(educationService).addEducationRecordByUserId(123L, firstEducationRecord);
			Assertions.assertThat(response.getContentAsString())
					.isEqualTo(educationEntityJson.write(educationRecord).getJson());
		}

		@Test
		void should_throw_exception_if_user_not_existed() throws Exception {
			when(educationService.addEducationRecordByUserId(123L, firstEducationRecord))
					.thenThrow(new UserNotFoundException(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE));

			mockMvc.perform(post("/users/{id}/educations",123)
					.contentType(MediaType.APPLICATION_JSON)
					.content(educationJson.write(firstEducationRecord).getJson()))
					.andExpect(status().isNotFound())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",is(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE)));

			verify(educationService).addEducationRecordByUserId(123L, firstEducationRecord);
		}

		@Test
		void show_throw_bad_request_if_education_title_not_valid() throws Exception {
			Education educationWithInvalidTitle = Education.builder()
					.year(2005L)
					.title("")
					.description("Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.")
					.build();

			mockMvc.perform(post("/users/{id}/educations", 123)
					.contentType(MediaType.APPLICATION_JSON)
					.content(educationJson.write(educationWithInvalidTitle).getJson()))
					.andExpect(status().isBadRequest())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",
							is(GlobalVariables.USER_EDUCATION_TITLE_INVALID_EXCEPTION_MESSAGE)));
		}

		@Test
		void show_throw_bad_request_if_education_description_not_valid() throws Exception {
			Education educationWithInvalidDescription = Education.builder()
					.year(2005L)
					.title("Secondary school specializing in artistic")
					.description("")
					.build();

			mockMvc.perform(post("/users/{id}/educations", 123)
					.contentType(MediaType.APPLICATION_JSON)
					.content(educationJson.write(educationWithInvalidDescription).getJson()))
					.andExpect(status().isBadRequest())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",
							is(GlobalVariables.USER_EDUCATION_DESC_INVALID_EXCEPTION_MESSAGE)));
		}
	}

	@Nested
	class findEducationByUserId {
		@BeforeEach
		public void beforeEach() {
			educationRecords = new ArrayList<>();
			Education firstEducationRecord = Education.builder()
					.year(2005L)
					.title("Secondary school specializing in artistic")
					.description("Eos, explicabo, nam, tenetur et ab eius deserunt aspernatur ipsum ducimus quibusdam quis voluptatibus.")
					.build();
			User firstUser = User.builder()
					.name("Nelson")
					.age(27L)
					.avatar("http://www.nelson.com/nelson.jpg")
					.description("A handsome boy").build();
			educationRecords.add(ResumeUtils.educationEntityBuilder(firstEducationRecord,ResumeUtils.userEntityBuilder(firstUser)));
		}

		@AfterEach
		public void afterEach() {
			Mockito.reset(educationService);
		}

		@Test
		void should_return_education_record_when_user_existed() throws Exception {
			when(educationService.findEducationRecordByUserId(123L)).thenReturn(educationRecords);

			MockHttpServletResponse response = mockMvc.perform(get("/users/{id}/educations",123))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andReturn().getResponse();

			verify(educationService).findEducationRecordByUserId(123L);
			Assertions.assertThat(response.getContentAsString())
					.isEqualTo(educationEntityListJson.write(educationRecords).getJson());
		}

		@Test
		void should_throw_error_when_user_not_existed() throws Exception {
			when(educationService.findEducationRecordByUserId(123L))
					.thenThrow(new UserNotFoundException(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE));

			mockMvc.perform(get("/users/{id}/educations",123))
					.andExpect(status().isNotFound())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",is(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE)));

			verify(educationService).findEducationRecordByUserId(123L);
		}

		@Test
		void should_throw_error_when_user_has_no_education_record() throws Exception {
			when(educationService.findEducationRecordByUserId(123L))
					.thenThrow(new EducationNotFoundException(GlobalVariables.EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE));

			mockMvc.perform(get("/users/{id}/educations",123))
					.andExpect(status().isNotFound())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",is(GlobalVariables.EDUCATION_NOT_FOUND_EXCEPTION_MESSAGE)));

			verify(educationService).findEducationRecordByUserId(123L);
		}
	}

}
