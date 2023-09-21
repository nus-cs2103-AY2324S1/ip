package pooh;

/**
 * Represents a Todo task.
 * <p>
 * This class is a subclass of the Task class and represents
 * a todo task, which has a description but no specific timing details.
 * </p>
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Writes the task details to a string in a format suitable for saving to a file.
     *
     * @return A string representing the task's details in a save-file-friendly format.
     */
    public String writeTaskToFile() {
        return String.format("%s | %s | %s", "T",
                this.getIsDone() ? 1 : 0,
                this.getDescription());
    }

    /**
     * Reads a todo task from a string array and returns a Todo object.
     *
     * @param args A string array containing the task details.
     * @return A new Todo object constructed from the provided string array.
     */
    public static Todo readTaskFromFile(String[] args) {
        Todo newTodoTask = new Todo(args[2]);
        if (args[1].equals("1")) {
            newTodoTask.markAsDone();
        }
        return newTodoTask;
    }

    /**
     * Returns a string representation of the task, suitable for user output.
     *
     * @return A string containing the type, status, and description of the task.
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
