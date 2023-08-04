package com.springreact.springreact;

class TodoNotFoundException extends RuntimeException {
    TodoNotFoundException(Integer id) {
        super("Could not find todo " + id);
    }
}
