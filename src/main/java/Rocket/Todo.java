package Rocket;

import Rocket.Task;

public class Todo extends Task {
    /**
     * Make new Rocket.Todo
     * @param description todo description
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
