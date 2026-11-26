package org.example.springeducationproj.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.springeducationproj.dto.CreateTaskRequest;
import org.example.springeducationproj.dto.TaskPreview;
import org.example.springeducationproj.dto.TaskStatus;
import org.example.springeducationproj.dto.UpdateTaskRequest;
import org.example.springeducationproj.model.Task;
import org.example.springeducationproj.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Задачи",
        description = "API для создания, получения, изменения и удаления задач"
)
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){this.taskService = taskService;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskPreview createTask(
            @RequestBody @Valid CreateTaskRequest createTaskRequest){
        Task task = taskService.createTask(
                createTaskRequest.title(),
                createTaskRequest.description(),
                createTaskRequest.userId(),
                createTaskRequest.categoryId()
        );

        return new TaskPreview(
                task.getId(),
                task.getTitle(),
                task.getCategory().getName(),
                task.getCompleted()
        );
    }

    @GetMapping("/{id}")
    public TaskPreview getTaskById(@PathVariable Long id){
        Task task = taskService.getTaskById(id);

        return new TaskPreview(
                task.getId(),
                task.getTitle(),
                task.getCategory().getName(),
                task.getCompleted()
        );
    }

    @Operation(
            summary = "Получить список задач",
            description = "Возвращает все задачи или фильтрует их по ID категории"
    )
    @GetMapping
    public List<TaskPreview> getAllTasks(@RequestParam(required = false) Long categoryId){
        List<Task> taskList;
        if(categoryId == null)
        {
             taskList = taskService.getAllTasks();
        }else{
            taskList = taskService.getTasksByCategory(categoryId);
        }

        return taskList.stream()
                .map(task -> new TaskPreview(task.getId(),
                        task.getTitle(),
                        task.getCategory().getName(),
                        task.getCompleted())).toList();
    }

    @PatchMapping("/{id}/complete")
    public TaskPreview completeTask(@PathVariable Long id){
        Task task = taskService.completeTask(id);

        return new TaskPreview(
                task.getId(),
                task.getTitle(),
                task.getCategory().getName(),
                task.getCompleted()
        );
    }

    @PutMapping("/{id}")
    public TaskPreview updateTask(@PathVariable Long id,@Valid @RequestBody UpdateTaskRequest request){
        Task task =
                taskService.updateTask(id, request.title(),
                        request.description(), request.categoryId(),
                        request.completed());

        return new TaskPreview(task.getId(),
                task.getTitle(),
                task.getCategory().getName(),
                task.getCompleted());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

}