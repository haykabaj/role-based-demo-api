package am.hayk.abajyan.rolebaseddemoapi.service.impl;

import am.hayk.abajyan.rolebaseddemoapi.model.User;
import am.hayk.abajyan.rolebaseddemoapi.repository.UserRepository;
import am.hayk.abajyan.rolebaseddemoapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
