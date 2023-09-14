package sae.task;

import sae.task.Task;

/**
 * The sae.task.Todo class represents a simple sae.task without any additional details.
 * It extends the sae.task.Task class and inherits its description field.
 */
public class Todo extends Task {

    /**
     * Constructs a new sae.task.Todo sae.task with the given description.
     *
     * @param description The description of the sae.task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method to format the sae.task.Todo sae.task's details.
     *
     * @return A formatted string representing the sae.task.Todo sae.task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    public String toFileString() {
        String completionStatus = isDone ? "1" : "0";
        return String.format("%s | %s | %s" , "T", completionStatus, description.trim());
    }
}