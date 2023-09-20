package duke.util;

/**
 * This class encapsulates all methods that parses user input.
 */
public class Parser {

    /**
     * Parses new tasks by removing the type of tasks.
     * @return An array of Strings.
     */
    public String[] parseNewTaskByType(String task) {
        assert (task != null) : "The Task to be parsed by type cannot be null.";

        return task.split("todo |deadline |event |recur ");
    }

    /**
     * Parses new tasks by separating the task description and dates.
     * @return An array of Strings.
     */
    public String[] parseNewTaskByDate(String task) {
        assert (task != null) : "The Deadline or Event to be parsed by date cannot be null.";

        return task.split(" /by | /from | /to | /every ");
    }

    /**
     * Parses stored tasks.
     */
    public String[] parseStoredTask(String task) {
        return task.split(" \\| ");
    }
}
