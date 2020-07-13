package edu.aam.app.repository;

import java.util.List;

import edu.aam.app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

	@Query("from Todo td where td.taskList.user.email=:email order by td.status, td.targetDate DESC")
	List<Todo> findOrderedTodo(@Param("email")String email);

	@Query("from Todo td where td.id=:id AND td.taskList.user.email=:email")
	Todo findUserTodoById(@Param("id")Long id, @Param("email")String email);

	@Query("from Todo td where td.taskList.user.email=:email AND td.status=:status order by td.targetDate DESC")
	List<Todo> findTodoByEmailAndStatus(@Param("email")String email, @Param("status") Boolean status);

	@Query("FROM Todo td WHERE td.id=:id")
	Todo findTodoById(@Param("id") Long id);
}
