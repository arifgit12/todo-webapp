package edu.aam.app.service.user;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.model.User;
import edu.aam.app.model.UserRole;
import edu.aam.app.repository.UserRepository;
import edu.aam.app.util.RoleNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public List<Task> findTaskByUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user.getTaskLists();
    }

    public List<Todo> getTodoByUserName(String userName) {
        User user = userRepository.findByEmail(userName);
        List<Task> taskList = user.getTaskLists();
        List<Todo> todoList = new ArrayList<>();
        for (Task task: taskList) {
            todoList.addAll(task.getTodoList());
        }
        return todoList;
    }

    public Todo getTodoByUserName(String userName, Long todoId) {
        List<Todo> todoList = getTodoByUserName(userName);
        Todo todo = todoList.stream().filter( td -> td.getId() == todoId ).findFirst().orElse(null);
        return todo;
    }

    public void DTOsave(UserDTO userDTO) {
        UserRole userRole = new UserRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userRole.setRoleName(RoleNames.USER.name());
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(Arrays.asList(userRole));
        user.setCreatedDate(new Date());
        userRepository.save(user);
    }
}
