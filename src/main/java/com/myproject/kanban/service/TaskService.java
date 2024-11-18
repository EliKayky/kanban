package com.myproject.kanban.service;

import com.myproject.kanban.model.Task;
import com.myproject.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task moveTaskToNextColumn(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            Task updatedTask = task.get();
            switch (updatedTask.getStatus()) {
                case "A Fazer":
                    updatedTask.setStatus("Em Progresso");
                    break;
                case "Em Progresso":
                    updatedTask.setStatus("Concluído");
                    break;
                default:
                    throw new IllegalStateException("Tarefa já está concluída");
            }
            return taskRepository.save(updatedTask);
        }
        throw new IllegalArgumentException("Tarefa não encontrada");
    }
}