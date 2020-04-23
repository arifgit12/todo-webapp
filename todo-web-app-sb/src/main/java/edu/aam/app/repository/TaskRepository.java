package edu.aam.app.repository;

import edu.aam.app.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT tk FROM Task tk WHERE tk.id = ?1 AND tk.user.email = ?2")
    Task findTaskById(Long id, String username);
}
