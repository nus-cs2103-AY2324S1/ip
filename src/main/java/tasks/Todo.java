package tasks;

/**
 * The class that will create a Todo line task.
 * This class extends from the tasks class.
 */
public class Todo extends Task {
    /**
     * Constructs the method.
     *
     * @param name The name of the task.
     */
    public Todo(String name) {
        super(name);
        super.setType("T");
    }
}
