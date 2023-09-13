package roo.task;

import java.time.LocalDateTime;
import java.util.ArrayList;

import roo.RooException;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo Task with specific details. It is initialised as not finished.
     * @param task The description of the todo task.
     * @throws RooException If the task description is empty or consists only of spaces.
     */
    public Todo(String task, ArrayList<String> tags) throws RooException {
        super(task, false, tags);
    }

    /**
     * Constructs a Todo Task with specific details.
     * @param task The description of the todo task.
     * @param isFinish The completion status of the task.
     * @throws RooException If the task description is empty or consists only of spaces.
     */
    public Todo(String task, boolean isFinish, ArrayList<String> tags) throws RooException {
        super(task, isFinish, tags);
    }

    /**
     * Returns null
     * @return null
     */
    @Override
    public LocalDateTime getDate() {
        return null;
    }

    /**
     * Returns a string representation of the task
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
