package com.example.resumeCvSystem;

import com.example.resumeCvSystem.common.GlobalVariables;
import com.example.resumeCvSystem.common.ResumeUtils;
import com.example.resumeCvSystem.controller.UserController;
import com.example.resumeCvSystem.domain.User;
import com.example.resumeCvSystem.entity.UserEntity;
import com.example.resumeCvSystem.exception.UserNotFoundException;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureJsonTesters
class UserControllerTest {
	@MockBean
	private UserService userService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private JacksonTester<User> userJson;

	@Autowired
	private JacksonTester<UserEntity> userEntityJson;

	private User firstUser;

	@BeforeEach
	public void beforeEach() {
		firstUser = User.builder()
				.name("Nelson")
				.age(27L)
				.avatar("http://www.nelson.com/nelson.jpg")
				.description("A handsome boy").build();
	}

	@AfterEach
	public void afterEach() {
		Mockito.reset(userService);
	}

	@Nested
	class addResumeUser {
		@Test
		void should_add_user_and_return_entity_when_user_is_valid() throws Exception {
			UserEntity firstUserEntity = ResumeUtils.userEntityBuilder(firstUser);
			when(userService.addUser(firstUser)).thenReturn(firstUserEntity);

			MockHttpServletResponse response = mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userEntityJson.write(firstUserEntity).getJson()))
				.andReturn().getResponse();

			Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

		}

		@Test
		void should_throw_exception_when_user_age_is_invalid() throws Exception {
			User firstInvalidUser = User.builder()
					.name("Nelson")
					.age(15L)
					.avatar("http://www.nelson.com/nelson.jpg")
					.description("A handsome boy").build();

			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(userJson.write(firstInvalidUser).getJson()))
					.andExpect(status().isBadRequest())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",
							is(GlobalVariables.USER_AGE_TOO_SHORT_EXCEPTION_MESSAGE)));
		}

		@Test
		void should_throw_exception_when_user_name_is_invalid() throws Exception {
			User nameInvalidUser = User.builder()
					.name("")
					.age(27L)
					.avatar("http://www.nelson.com/nelson.jpg")
					.description("A handsome boy").build();

			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(userJson.write(nameInvalidUser).getJson()))
					.andExpect(status().isBadRequest())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",
							is(GlobalVariables.USER_NAME_INVALID_EXCEPTION_MESSAGE)));
		}

		@Test
		void should_throw_exception_when_user_avatar_is_invalid() throws Exception {
			User avatarInvalidUser = User.builder()
					.name("Nelson")
					.age(27L)
					.avatar("")
					.description("A handsome boy").build();

			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(userJson.write(avatarInvalidUser).getJson()))
					.andExpect(status().isBadRequest())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",
							is(GlobalVariables.USER_AVATAR_INVALID_EXCEPTION_MESSAGE)));
		}

		@Test
		void should_throw_exception_when_user_desc_is_invalid() throws Exception {
			User descInvalidUser = User.builder()
					.name("Nelson")
					.age(27L)
					.avatar("http://www.nelson.com/nelson.jpg")
					.description("@Test\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\tvoid should_throw_exception_when_user_avatar_is_invalid() throws Exception {\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\tUser avatarInvalidUser = User.builder()\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.name(\\\"Nelson\\\")\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.age(27L)\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.avatar(\\\"\\\")\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.description(\\\"A handsome boy\\\").build();\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\tmockMvc.perform(post(\\\"/users\\\")\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.contentType(MediaType.APPLICATION_JSON)\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.content(userJson.write(avatarInvalidUser).getJson()))\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.andExpect(status().isBadRequest())\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.andExpect(content().contentType(MediaType.APPLICATION_JSON))\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t.andExpect(jsonPath(\\\"$.message\\\",\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t\\t\\t\\t\\t\\tis(ExceptionMessage.USER_AVATAR_INVALID_EXCEPTION_MESSAGE)));\\n\" +\n" +
							"\t\t\t\t\t\t\t\"\\t\\t}").build();

			mockMvc.perform(post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(userJson.write(descInvalidUser).getJson()))
					.andExpect(status().isBadRequest())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",
							is(GlobalVariables.USER_DESCRIPTION_INVALID_EXCEPTION_MESSAGE)));
		}
	}

	@Nested
	class findUserById {
		@Test
		void should_return_user_when_user_existed() throws Exception {
			UserEntity firstUserEntity = ResumeUtils.userEntityBuilder(firstUser);
			when(userService.findUserById(123L)).thenReturn(firstUserEntity);

			MockHttpServletResponse response = mockMvc.perform(get("/users/{id}",123))
					.andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andReturn().getResponse();

			verify(userService).findUserById(123L);
			Assertions.assertThat(response.getContentAsString())
					.isEqualTo(userEntityJson.write(firstUserEntity).getJson());
		}

		@Test
		void should_throw_exception_when_user_is_not_found() throws Exception {
			when(userService.findUserById(123L))
					.thenThrow(new UserNotFoundException(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE));

			mockMvc.perform(get("/users/{id}",123))
					.andExpect(status().isNotFound())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.message",is(GlobalVariables.USER_NOT_FOUND_EXCEPTION_MESSAGE)));

			verify(userService).findUserById(123L);
		}
	}

}
