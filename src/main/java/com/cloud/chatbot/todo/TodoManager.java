package com.cloud.chatbot.todo;

import java.util.ArrayList;
import java.util.List;



/**
 * Manages all TODOs while the bot is running.
 */
public class TodoManager {
    private List<Todo> todos = new ArrayList<>();

    /**
     * Adds the specified TODO.
     *
     * @param todo The TODO to add.
     */
    public void add(Todo todo) {
        this.todos.add(todo);
    }

    /**
     * Returns the TODO for the specified number.
     *
     * @param int The TODO's number.
     */
    public Todo get(int number) {
        return this.todos.get(number - 1);
    }

    /**
     * Removes and returns the TODO of the specified number.
     *
     * @param int The TODO's number.
     */
    public Todo remove(int number) {
        return this.todos.remove(number - 1);
    }

    /**
     * Returns the total number of TODOs.
     */
    public int getCount() {
        return this.todos.size();
    }
}
