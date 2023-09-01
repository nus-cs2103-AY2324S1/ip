package duke.util;

/**
 * This class encapsulates all methods that parses user input.
 */
public class Parser {

    /**
     * Parses new tasks by removing the type of tasks.
     */
    public String[] parseNewTaskByType(String task) {
        return task.split("todo |deadline |event ");
    }

    /**
     * Parses new tasks by separating the task description and dates.
     */
    public String[] parseNewTaskByDate(String task) {
        return task.split(" /by | /from | /to ");
    }

    /**
     * Parses stored tasks.
     */
    public String[] parseStoredTask(String task) {
        return task.split(" \\| ");
    }
}
