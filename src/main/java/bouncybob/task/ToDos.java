package bouncybob.task;

/**
 * Represents a To-Do task in the BouncyBob application.
 */
public class ToDos extends Task {

    /**
     * Constructs a new To-Do task with the given name.
     *
     * @param name The name of the task.
     */
    public ToDos(String name) {
        super(name);
    }

    /**
     * Returns the symbol representing the To-Do task type.
     *
     * @return "T" for To-Do tasks.
     */
    @Override
    public String getSymbol() {
        return "T";
    }
}
