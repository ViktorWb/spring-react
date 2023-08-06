package com.springreact.springreact.todoapi;

class TodoNotFoundException extends RuntimeException {
    TodoNotFoundException(Integer id) {
        super("Could not find todo " + id);
    }
}
