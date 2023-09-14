package oscar.info;

/**
 * Todo task that contains description of task.
 */
public class TodoTask extends Task {
    /**
     * Public constructor of todo.
     *
     * @param description Details of todo.
     */
    public TodoTask(String description) {
        super(description, "T");
    }
}
