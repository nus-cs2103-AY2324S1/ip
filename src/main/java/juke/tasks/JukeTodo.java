package juke.tasks;

/**
 * Represents a Todo task. Todo tasks do not have any deadlines.
 */
public class JukeTodo extends JukeTask {
    /** String which represents the Task Identifier. */
    private static final String TASK_DESCRIPTOR = "[T] ";

    /**
     * Constructor to create a {@code JukeTodo}.
     *
     * @param task Task description
     */
    public JukeTodo(String task) {
        super(task);
    }

    /**
     * Creates an instance of {@code JukeTodo}.
     *
     * @param task Task description
     * @param isCompleted Status of completion of the task
     */
    public JukeTodo(String task, boolean isCompleted) {
        super(task);

        if (isCompleted) {
            this.setAsComplete();
        }
    }

    /**
     * Returns the string which represents this object when it is saved into the datafile.
     *
     * @return Datafile representation of this object
     */
    @Override
    public String save() {
        return "T" + super.save();
    }

    /**
     * Returns String representation of this {@code JukeTodo} object
     *
     * @return String representation
     */
    @Override
    public String toString() {
        return JukeTodo.TASK_DESCRIPTOR + super.toString();
    }
}
