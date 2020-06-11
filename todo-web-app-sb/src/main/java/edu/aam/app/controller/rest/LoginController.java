package edu.aam.app.controller.rest;

import edu.aam.app.model.jwt.JwtRequest;
import edu.aam.app.model.jwt.JwtResponse;
import edu.aam.app.security.jwt.JwtTokenUtil;
import edu.aam.app.service.user.CustomUserDetailsService;
import edu.aam.app.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/login")
public class LoginController {

    @Autowired
    private IUserService uService;

    @Autowired
    private CustomUserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        //uService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtInMemoryUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
