package edu.aam.app.service.user;

import edu.aam.app.model.User;
import edu.aam.app.model.Role;
import edu.aam.app.repository.UserRepository;
import edu.aam.app.security.LoginAttemptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        String ip = getClientIP();

        if (loginAttemptService.isBlocked(ip)) {
            System.out.println(ip);
            throw new UsernameNotFoundException("User '" + ip + "' blocker");
        }

        User user = userRepository.findByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (user != null && user.isEnabled()) {
            for (Role role : user.getRoles()){
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            }

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException(email);
        }
    }

    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
