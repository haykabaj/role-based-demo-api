package am.hayk.abajyan.rolebaseddemoapi.controller;

import am.hayk.abajyan.rolebaseddemoapi.model.User;
import am.hayk.abajyan.rolebaseddemoapi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/v1/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PreAuthorize("hasAuthority('developers:read')")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable int id){
        return ResponseEntity.ok(userServiceImpl.getUser(id));
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping
    public ResponseEntity<?> getUserList(Model model){
        List<User> userList = userServiceImpl.getUserList();
        if (!userList.isEmpty()){
            ResponseEntity.ok(userList);
        }
        return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userServiceImpl.addUser(user));
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id){
        userServiceImpl.deleteUser(id);
        return ResponseEntity.ok("user deleted !");
    }
}
