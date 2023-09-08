package iris;

/**
 * Represents a todo task in the Iris application.
 */
public class Todo extends Task {
    /**
     * Constructor for the Todo class.
     *
     * @param name The name of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Generate a string representation of the todo task for writing to a file.
     *
     * @return The formatted string for file output.
     */
    public String writeTaskToFile() {
        return String.format("%s | %s | %s", "T",
                this.isDone() ? 1 : 0,
                this.getDescription());
    }

    /**
     * Create a Todo task object from an array of strings read from a file.
     *
     * @param args The array of strings containing task information.
     * @return The Todo task object.
     */
    public static Todo readTaskFromFile(String[] args) {
        Todo newTodoTask = new Todo(args[2]);
        if (args[1].equals("1")) {
            newTodoTask.markDone();
        }
        return newTodoTask;
    }

    /**
     * Override the toString method to provide a custom string representation of the Todo task.
     *
     * @return The formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
