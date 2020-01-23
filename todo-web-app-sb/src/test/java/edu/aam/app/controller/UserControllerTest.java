package edu.aam.app.controller;

import edu.aam.app.model.User;
import edu.aam.app.repository.TodoRepository;
import edu.aam.app.repository.UserRepository;
import edu.aam.app.service.user.UserDTO;
import edu.aam.app.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User sampleUser1;
    private User sampleUser2;
    private User sampleUser3;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Whitebox.setInternalState(userController, "userService", userService);
        Whitebox.setInternalState(userService, "userRepository", userRepository);
        sampleUser1 = new User();
        sampleUser1.setFirstName("John John");
        sampleUser1.setLastName("Doe Doe");
        sampleUser1.setPassword("John1234");
        sampleUser1.setEmail("john1234@yahoo.com");

        // user for updated user
        sampleUser2 = new User();
        sampleUser2.setFirstName("Johny");
        sampleUser2.setLastName("Doe");
        sampleUser2.setPassword("Johny1");
        sampleUser2.setEmail("johny@yahoo.com");

        // additional user
        sampleUser3 = new User();
        sampleUser3.setFirstName("Jake");
        sampleUser3.setLastName("Doe");
        sampleUser3.setPassword("Jake");
        sampleUser3.setEmail("jake@yahoo.com");
    }

    @Test
    public void contexLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void whenRegisterUser_thenCheckForUserAdded() {

    }

    @Test
    public void whenUpdateUserPassword_thenCheckForUpdatedUser() {

    }

    @Test
    public void getProfileViewTest() {

        UserDTO userDto = new UserDTO();
        userDto.setFirstName(sampleUser1.getFirstName());
        userDto.setLastName(sampleUser1.getLastName());
        userDto.setEmail(sampleUser1.getEmail());
        // given
        given(userService.findUserByEmail("abc123@gmail.com"))
                .willReturn(sampleUser1);
        given(userService.convertUserDto(sampleUser1)).willReturn(userDto);

        // when
        ResponseEntity<String> viewResponse = testRestTemplate.getForEntity("/profile", String.class);

        // then
        assertThat(viewResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
