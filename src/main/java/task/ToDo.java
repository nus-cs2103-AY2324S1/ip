package task;

/**
 * The ToDo class represents a simple task without a specific due date or time.
 * It provides methods to create a ToDo task and generate file and display strings.
 */
public class ToDo extends Task {
    private String title;

    /**
     * Constructs a ToDo object with the specified response string.
     *
     * @param response The user's input representing the task.
     */
    public ToDo(String response) {
        super(false);
        int toTrim = response.indexOf(" ");
        String taskTitle = response.substring(toTrim + 1);
        this.title = taskTitle;
    }

    /**
     * Generates a string representation of the ToDo task for storage in a file.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toFileString() {
        if (this.done == true) {
            return "T | 1 | " + this.title;
        }
        return "T | 0 | " + this.title;
    }

    /**
     * Generates a string representation of the ToDo task for display purposes.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        if (this.done == true) {
            return "[T] " + "[X] " + this.title;
        }
        return "[T] " + "[ ] " + this.title;
    }
}
