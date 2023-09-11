package duke.util;

/**
 * This class encapsulates all methods that parses user input.
 */
public class Parser {

    /**
     * Parses new tasks by removing the type of tasks.
     */
    public String[] parseNewTaskByType(String task) {
        assert (task != null) : "The Task to be parsed by type cannot be null.";

        return task.split("todo |deadline |event ");
    }

    /**
     * Parses new tasks by separating the task description and dates.
     */
    public String[] parseNewTaskByDate(String task) {
        assert (task != null) : "The Deadline or Event to be parsed by date cannot be null.";

        return task.split(" /by | /from | /to ");
    }

    /**
     * Parses stored tasks.
     */
    public String[] parseStoredTask(String task) {
        return task.split(" \\| ");
    }
}
