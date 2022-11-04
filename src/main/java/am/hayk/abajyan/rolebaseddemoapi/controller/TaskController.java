package am.hayk.abajyan.rolebaseddemoapi.controller;

import am.hayk.abajyan.rolebaseddemoapi.constants.TaskStatus;
import am.hayk.abajyan.rolebaseddemoapi.exception.NotFoundException;
import am.hayk.abajyan.rolebaseddemoapi.model.Task;
import am.hayk.abajyan.rolebaseddemoapi.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    @Autowired
    public TaskController(TaskServiceImpl taskServiceImpl) {
        this.taskServiceImpl = taskServiceImpl;
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping
    public String getTasks(Model model) {
        model.addAttribute("tasks", taskServiceImpl.getTaskList());
        return "admin";
    }

    @PreAuthorize("hasAuthority('developers:read') and hasAuthority('developers:write')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(@PathVariable int id) {
        Optional<Task>  task = taskServiceImpl.getTask(id);
        if (task.isPresent()){
            ResponseEntity.ok(task);
        }
        return new ResponseEntity<>(new NotFoundException("Task not found !"), HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('developers:read') and hasAuthority('developers:write')")
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskServiceImpl.addTask(task));
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskServiceImpl.updateTask(task));
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        taskServiceImpl.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully !");
    }

    @PreAuthorize("hasAuthority('developers:read') and hasAuthority('developers:write')")
    @PostMapping("/change-status")
    public ResponseEntity<?> changeTaskStatus(@RequestParam int taskId, @RequestParam String status) {
        taskServiceImpl.changeTaskStatus(taskId, status);
        return ResponseEntity.ok("task status changed successfully !");
    }

    @PreAuthorize("hasAuthority('developers:read') and hasAuthority('developers:write')")
    @GetMapping("/by-creation-date")
    public ResponseEntity<?> getTaskByCreationDate(@RequestParam Date date) {
        List<Task> taskByCreatedAt = taskServiceImpl.getTasksByCreatedAt(date);
        if (!taskByCreatedAt.isEmpty()) {
            return ResponseEntity.ok(taskByCreatedAt);
        }
        return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('developers:read') and hasAuthority('developers:write')")
    @GetMapping("/by-status")
    public ResponseEntity<?> getTaskByStatus(@RequestParam String status) {
        List<Task> taskByStatus = taskServiceImpl.getTasksByTaskStatus(TaskStatus.valueOf(status));
        if (!taskByStatus.isEmpty()) {
            return ResponseEntity.ok(taskByStatus);
        }
        return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('developers:write')")
    @GetMapping("/{userId}")
    public ResponseEntity<?> getTasksByUserId(@PathVariable("userId") int userId) {
        List<Task> taskByUserId = taskServiceImpl.getTasksByUserId(userId);
        if (taskByUserId.isEmpty()) {
            return ResponseEntity.ok(taskByUserId);
        }
        return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

}
