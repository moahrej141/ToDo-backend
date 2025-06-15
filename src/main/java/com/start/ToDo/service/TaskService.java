//service/TskService.java
package com.start.ToDo.service;

import java.util.List;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.start.ToDo.entity.Task;
import com.start.ToDo.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional 
    public Task createTask(Task task ) {
        return taskRepository.save(task);
    }
    @Transactional
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    @Transactional
    public Task updateTask(Long id , Task task) {
        Task opt= taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        opt.setTitle(task.getTitle());
        opt.setDescription(task.getDescription());
        opt.setDueDate(task.getDueDate());
        return taskRepository.save(opt);
    }
    @Transactional
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}

