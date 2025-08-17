package com.rev.revisao.service;

import com.rev.revisao.dto.TaskDTO;
import com.rev.revisao.mapper.TaskMapper;
import com.rev.revisao.model.Task;
import com.rev.revisao.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private TaskMapper mapper;

    public List<TaskDTO> getAllTasks() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    public TaskDTO createTask(TaskDTO dto) {
        Task task = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(task));
    }

    public TaskDTO updateTask(Long id, TaskDTO dto) {
        return repository.findById(id).map(task -> {
            mapper.updateEntityFromDTO(dto, task);
            return mapper.toDTO(repository.save(task));
        }).orElse(null);
    }

    public boolean deleteTask(Long id) {
        return repository.findById(id).map(task -> {
            repository.delete(task);
            return true;
        }).orElse(false);
    }

    public TaskDTO toggleTaskCompletion(Long id) {
        return repository.findById(id).map(task -> {
            task.setCompleted(!task.getCompleted());
            return mapper.toDTO(repository.save(task));
        }).orElse(null);
    }
}
