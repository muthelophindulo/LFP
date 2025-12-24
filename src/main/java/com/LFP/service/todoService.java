package com.LFP.service;

import com.LFP.model.Todo;
import com.LFP.repository.todoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class todoService {
    private final todoRepository todoRepository;

    public todoService(todoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodos(){
        return todoRepository.findAll();
    }

    public Todo saveTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteTodo(Todo todo){
        todoRepository.delete(todo);
    }

    public Todo getById(Long id){
        return todoRepository.getReferenceById(id);
    }

    public Todo GetByTodoId(String id){
        return todoRepository.getByTodoId(id);
    }
}
