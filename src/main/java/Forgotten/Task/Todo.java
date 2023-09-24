package Forgotten.Task;

import Forgotten.Priority;

public class Todo extends Task {

    private Todo(String description) {
        super(description);
    }

    /**
     * Default constructor method.
     *
     * @param description Description of this todo task.
     * @param isDone Status of this task, either done or not done.
     */
    public Todo(String description, boolean isDone, Priority priority) {
        super(description);
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * This is a factory method which generates a Todo task.
     *
     * @param description Description of this Todo task.
     * @return The Todo task.
     */
    public static Todo createNewTodoTask(String description) {
        return new Todo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + " [P: " + this.priority + "]";
    }

    /**
     * This method returns the string representation of this Todo task
     * in a format which can be stored in the hard disk.
     *
     * @return The string representation of this Todo task.
     */
    @Override
    public String toFileString() {
        return "[T]" + super.toString() + " [P: " + this.priority + "]";
    }
}
