package am.hayk.abajyan.rolebaseddemoapi.service;

import am.hayk.abajyan.rolebaseddemoapi.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User addUser(User user);

    User updateUser(User user);

    Optional<User> getUser(int id);

    List<User> getUserList();

    void deleteUser(int id);
}
