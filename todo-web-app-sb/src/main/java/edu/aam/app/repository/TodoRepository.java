package edu.aam.app.repository;

import java.util.List;

import edu.aam.app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
	List<Todo> findByUserName(String user);

	@Query("from Todo td where td.userName=:username order by td.status, td.targetDate DESC")
	List<Todo> findOrderedTodo(@Param("username")String username);
}
