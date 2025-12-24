package com.LFP.controller;

import com.LFP.model.Todo;
import com.LFP.model.product;
import com.LFP.service.todoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("todo/")
public class todoController {
    private final todoService todoService;

    public todoController(todoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("Todo",new Todo());
        return "todo/form";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("Todo") Todo Todo,
            RedirectAttributes redirectAttributes
    ){
        if (Todo.getTodoId() == null || Todo.getTodoId().isEmpty()) {

            Todo.setTodoId("TODO-" + System.currentTimeMillis());
        }
        todoService.saveTodo(Todo);

        return "redirect:/dashboard";
    }

    @GetMapping("/list")
    public String showProducts(Model model){
        List<Todo> todos = todoService.getTodos();
        model.addAttribute("todos", todos);

        // Add statistics
        model.addAttribute("totalTodos", todoService.getTodos().size());

        return "todo/todos";

    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        Todo x = todoService.getById(id);

        if(x != null){
            todoService.deleteTodo(x);
        }else{
            return "the todo with id " + id + " does not exists";
        }

        return "redirect:/todo/list";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model){
        Todo updatedTodo = todoService.getById(id);

        model.addAttribute("Todo", updatedTodo);

        return "todo/update";
    }

    @PostMapping("/update")
    public String saveUpdate(
            @ModelAttribute Todo Todo
    ){
        Todo x = todoService.getById( Todo.getId() );

        x.setTodoId( todoService.getById( x.getId() ).getTodoId() ) ;
        x.setName( Todo.getName());
        x.setDescription( Todo.getDescription());
        todoService.saveTodo(x);
        return "redirect:/todo/list";
    }

    @GetMapping("/view/{id}")
    public String View(@PathVariable Long id,Model model){
        Todo x = todoService.getById(id);

        model.addAttribute("Todo",x);

        return "todo/view";
    }
}
