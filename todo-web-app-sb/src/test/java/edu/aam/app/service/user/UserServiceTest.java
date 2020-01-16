package edu.aam.app.service.user;


import edu.aam.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void frameUp() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void findUserByEmailTest() {
        System.out.println("welcome to test demo");
    }
}
