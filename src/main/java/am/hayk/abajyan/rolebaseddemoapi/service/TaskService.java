package am.hayk.abajyan.rolebaseddemoapi.service;

import am.hayk.abajyan.rolebaseddemoapi.constants.TaskStatus;
import am.hayk.abajyan.rolebaseddemoapi.model.Task;
import am.hayk.abajyan.rolebaseddemoapi.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskService {

    Task addTask(Task task);

    Optional<Task> getTask(int id);

    List<Task> getTaskList();

    void deleteTask(int id);

    Task updateTask(Task task);

    List<Task> getTasksByUserId(int userId);

    List<Task> getTasksByTaskStatus(TaskStatus taskStatus);

    List<Task> getTasksByCreatedAt(Date date);

    void assignTask(int taskId , int userId);

    void changeTaskStatus(int taskId, String taskStatus);
}
