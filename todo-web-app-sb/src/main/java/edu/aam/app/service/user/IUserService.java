package edu.aam.app.service.user;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.model.User;
import edu.aam.app.model.vm.UserDTO;

import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User findUserByEmail(String email);
    List<Task> findTaskByUserEmail(String email);
    List<Todo> getTodoByUserName(String userName);
    Todo getTodoByUserName(String userName, Long todoId);
    User createUser(UserDTO userDTO);
    User updateUser(User user);
    void updatePassword(String email, String newPassword);
    User findUserByUserName(String username);
    User updateToken(String email, Boolean status);
}
