package edu.aam.app.controller;

import edu.aam.app.model.User;
import edu.aam.app.service.user.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserController userController;

    @Autowired
    private UserService userService;

    private User sampleUser1;
    private User sampleUser2;
    private User sampleUser3;

    @Before
    public void setup() {
        sampleUser1 = new User();
        sampleUser1.setFirstName("John");
        sampleUser1.setLastName("Doe");
        sampleUser1.setPassword("John1");
        sampleUser1.setEmail("john@yahoo.com");

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

}
