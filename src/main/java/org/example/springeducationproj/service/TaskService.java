package org.example.springeducationproj.service;

import jakarta.transaction.Transactional;
import org.example.springeducationproj.exception.ResourceNotFoundException;
import org.example.springeducationproj.model.Category;
import org.example.springeducationproj.model.Task;
import org.example.springeducationproj.model.User;
import org.example.springeducationproj.repository.CategoryRepository;
import org.example.springeducationproj.repository.TaskRepository;
import org.example.springeducationproj.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(
            TaskRepository taskRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Task createTask(
            String title,
            String description,
            Long userId,
            Long categoryId
    ){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь отсутствует"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Категория не найдена"));

        Task task = new Task(title, description, user, category);

        return taskRepository.save(task);
    }

    @Transactional
    public Task getTaskById(Long id){

        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Задача не найдена"));
    }

    @Transactional
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Transactional
    public Task completeTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task не найден"));

        task.setCompleted(true);

        return task;
    }

    @Transactional
    public Task updateTask(
            Long id,
            String title,
            String description,
            Long categoryId,
            boolean completed
    )
    {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task не найден"));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category не найден"));

        task.setDiscription(description);
        task.setTitle(title);
        task.setCategory(category);
        task.setCompleted(completed);

        return task;
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task не найден"));
        taskRepository.delete(task);
    }

    @Transactional
    public List<Task> getTasksByCategory(Long categotyId){
        return taskRepository.findByCategory_Id(categotyId);
    }
}
