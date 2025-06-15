
// repository/TaskRepository.java
package com.start.ToDo.repository;

import com.start.ToDo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
