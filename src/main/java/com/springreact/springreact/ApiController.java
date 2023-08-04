package com.springreact.springreact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping(path = "/api")
public class ApiController {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(path = "/todos")
    public @ResponseBody Iterable<Todo> getAllTodos() {
        return this.todoRepository.findAll();
    }

    @GetMapping("/todos/{id}")
    Todo getTodo(@PathVariable Integer id) {
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @PostMapping("/todos")
    Todo newTodo(@RequestBody Todo newTodo) {
        return todoRepository.save(newTodo);
    }

    @PutMapping("/todos/{id}")
    Todo replaceTodo(@RequestBody Todo newTodo, @PathVariable Integer id) {
        return this.todoRepository.findById(id)
                .map(todo -> {
                    todo.setName(newTodo.getName());
                    todo.setDescription(newTodo.getDescription());
                    todo.setCompleted(newTodo.getCompleted());
                    return this.todoRepository.save(todo);
                })
                .orElseGet(() -> {
                    newTodo.setId(id);
                    return this.todoRepository.save(newTodo);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteTodo(@PathVariable Integer id) {
        this.todoRepository.deleteById(id);
    }
}
