package edu.aam.app.repository;

import edu.aam.app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // SELECT *
    //FROM tasks tk
    //WHERE tk.id = 12 AND tk.user_id = 22;
    @Query("SELECT tk FROM Task tk WHERE tk.id = ?1")
    Task findTaskById(Long id);
}
