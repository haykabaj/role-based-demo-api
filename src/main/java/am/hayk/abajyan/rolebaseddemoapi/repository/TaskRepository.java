package am.hayk.abajyan.rolebaseddemoapi.repository;

import am.hayk.abajyan.rolebaseddemoapi.constants.TaskStatus;
import am.hayk.abajyan.rolebaseddemoapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Integer> {

    List<Task> getTasksByUserId(int userId);

   List<Task> getTasksByTaskStatus(TaskStatus status);

    List<Task> getTasksByCreatedAt(Date date);

    @Query(value = "insert into tasks (user_id) VALUES (user_id) where id = %?1%", nativeQuery = true)
    void assignTask(int id , @Param("user_id") int userId);

    @Query(value = "insert into tasks (status) VALUES (status) where id = %?1%", nativeQuery = true)
    void changeTaskStatus(int id, @Param("status")String status);


}
