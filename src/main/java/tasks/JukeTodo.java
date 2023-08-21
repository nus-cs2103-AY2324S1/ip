package main.java.tasks;

/**
 * Represents a Todo task.
 */
public class JukeTodo extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[T] ";

    /**
     * Constructor to create a Todo Task.
     * @param task Task description
     */
    public JukeTodo(String task) {
        super(task);
    }

    /**
     * Overridden toString method which now include the task's identifier
     * in its String representation.
     * @return String representation of a Todo object.
     */
    @Override
    public String toString() {
        return JukeTodo.TASK_DESCRIPTOR + super.toString();
    }
}
