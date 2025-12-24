package com.LFP.repository;

import com.LFP.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface todoRepository extends JpaRepository<Todo,Long> {
    Todo getByTodoId(String todoId);
}
