package edu.aam.app.service.user;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import edu.aam.app.model.User;
import edu.aam.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    private User setUser() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("testmail@gmail.com");
        return user;
    }

    @Test
    public void findUserByEmailNullTest() {
        when(userRepository.findByEmail("test1@gmail.com")).thenReturn(null);
        User testuser = userService.findUserByEmail("test1@gmail.com");
        assertNull(testuser);
    }

    @Test
    public void findUserByEmailTest() {
        User user = setUser();
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(user);
        User testuser = userService.findUserByEmail("test@gmail.com");
        assertNotNull(testuser);
        assertEquals("testmail@gmail.com", testuser.getEmail());
    }
}
