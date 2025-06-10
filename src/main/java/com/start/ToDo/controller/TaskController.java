//controller/TaskController.java
package com.start.ToDo.controller;

import com.start.ToDo.entity.Task;
import com.start.ToDo.entity.User;
import com.start.ToDo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepo;

@PostMapping
public Task createTask(@RequestBody Task task, Authentication auth) {
    System.out.println("Authenticated user: " + auth.getPrincipal());
    User user = (User) auth.getPrincipal();
    task.setUser(user);
    task.setCompleted(false);
    return taskRepo.save(task);
}

}
