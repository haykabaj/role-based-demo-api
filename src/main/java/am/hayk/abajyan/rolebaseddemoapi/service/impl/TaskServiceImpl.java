package am.hayk.abajyan.rolebaseddemoapi.service.impl;

import am.hayk.abajyan.rolebaseddemoapi.constants.TaskStatus;
import am.hayk.abajyan.rolebaseddemoapi.model.Task;
import am.hayk.abajyan.rolebaseddemoapi.repository.TaskRepository;
import am.hayk.abajyan.rolebaseddemoapi.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Optional<Task> getTask(int id) {
        return taskRepository.findById(id);
    }

    @Override
    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTask(Task task) {
        Optional<Task> fromDB =  taskRepository.findById(task.getId());
        if (fromDB.isPresent()){
            fromDB.get().setUpdatedAt(task.getUpdatedAt());
            fromDB.get().setCreatedAt(task.getCreatedAt());
            fromDB.get().setDescription(task.getDescription());
            fromDB.get().setName(task.getName());
            fromDB.get().setTaskStatus(task.getTaskStatus());
            fromDB.get().setUserId(task.getUserId());
            taskRepository.save(fromDB.get());
        }
        throw  new RuntimeException(String.format("Task with %s is not present !", task.getId()));
    }

    @Override
    public List<Task> getTasksByUserId(int userId) {
        return taskRepository.getTasksByUserId(userId);
    }

    @Override
    public List<Task> getTasksByTaskStatus(TaskStatus status) {
        return taskRepository.getTasksByTaskStatus(status);
    }

    @Override
    public List<Task> getTasksByCreatedAt(Date date) {
        return taskRepository.getTasksByCreatedAt(date);
    }

    @Override
    public void assignTask(int taskId, int userId) {
        taskRepository.assignTask(taskId, userId);
    }

    @Override
    public void changeTaskStatus(int id, String status) {
        taskRepository.changeTaskStatus(id, status);
    }
}

