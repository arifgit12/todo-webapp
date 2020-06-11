package edu.aam.app.controller.rest;

import edu.aam.app.model.User;
import edu.aam.app.model.vm.UserVM;
import edu.aam.app.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // This is just for testing Purpose - Remove this code in production
    @GetMapping("{email}")
    public ResponseEntity<UserVM>  getUser(@PathVariable String email) {
        User user = userService.findUserByUserName(email);
        if (user != null) {
            UserVM userVM = modelMapper.map(user, UserVM.class);
            return new ResponseEntity<>(userVM, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
