package duke.command;

import duke.TaskType;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import java.time.LocalDate;

/**
 * The Parser class is responsible for parsing user commands and generating corresponding Task objects.
 * It also provides utility methods for checking specific command types.
 */
public class Parser {

    /**
     * Checks if the given command represents the "bye" command.
     *
     * @param command The user command to check.
     * @return True if the command is "bye," false otherwise.
     */
    public static boolean isBye(String command) {
        return command.equalsIgnoreCase("bye");
    }

    /**
     * Checks if the given command represents the "list" command.
     *
     * @param command The user command to check.
     * @return True if the command is "list," false otherwise.
     */
    public static boolean isList(String command) {
        return command.equalsIgnoreCase("list");
    }

    /**
     * Checks if the given command represents the "mark" command.
     *
     * @param command The user command to check.
     * @return True if the command starts with "mark," false otherwise.
     */
    public static boolean isMarkDone(String command) {
        return command.startsWith("mark");
    }

    /**
     * Checks if the given command represents the "unmark" command.
     *
     * @param command The user command to check.
     * @return True if the command starts with "unmark," false otherwise.
     */
    public static boolean isMarkNotDone(String command) {
        return command.startsWith("unmark");
    }

    /**
     * Checks if the given command represents the "delete" command.
     *
     * @param command The user command to check.
     * @return True if the command starts with "delete," false otherwise.
     */
    public static boolean isDelete(String command) {
        return command.startsWith("delete");
    }

    /**
     * Extracts the task index from the given command.
     *
     * @param command The user command containing the task index.
     * @return The extracted task index.
     * @throws DukeException If the task index cannot be extracted or is invalid.
     */
    public static int extractTaskIndex(String command) throws DukeException {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please provide the task index.");
        }
        return Integer.parseInt(parts[1].trim()) - 1;
    }

    /**
     * Parses the given command and generates a Task object based on its content.
     *
     * @param command The user command to parse.
     * @return A Task object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or is invalid.
     */
    public static Task parseTask(String command) throws DukeException {
        String[] parts = command.split(" ", 2);

        try {
            TaskType taskType = TaskType.valueOf(parts[0].toUpperCase());

            switch (taskType) {
                case TODO:
                    // Parse the description, fromDate, and toDate
                    String[] todoParts = parts[1].split(" /from ");
                    if (todoParts.length != 2) {
                        throw new DukeException("OOPS!!! Todo tasks must include '/from' for date.");
                    }
                    String[] dateRange = todoParts[1].split(" /to ");
                    if (dateRange.length != 2) {
                        throw new DukeException("OOPS!!! Todo tasks must include '/to' for the end date.");
                    }
                    LocalDate fromDate = LocalDate.parse(dateRange[0]);
                    LocalDate toDate = LocalDate.parse(dateRange[1]);
                    return new TodoTask(todoParts[0], fromDate, toDate, false);

                case DEADLINE:
                    // Parse the description and byDate
                    String[] deadlineParts = parts[1].split(" /by ");
                    if (deadlineParts.length != 2) {
                        throw new DukeException("OOPS!!! Deadline tasks must include '/by' for date.");
                    }
                    LocalDate byDate = LocalDate.parse(deadlineParts[1]);
                    return new DeadlineTask(deadlineParts[0], byDate, false);

                case EVENT:
                    // Parse the description and atDate
                    String[] eventParts = parts[1].split(" /at ");
                    if (eventParts.length != 2) {
                        throw new DukeException("OOPS!!! Event tasks must include '/at' for date.");
                    }
                    LocalDate atDate = LocalDate.parse(eventParts[1]);
                    return new EventTask(eventParts[0], atDate, false);

                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses a line from a file and generates a Task object based on its content.
     *
     * @param line The line from the file to parse.
     * @return A Task object representing the parsed line.
     * @throws DukeException If the line cannot be parsed or is invalid.
     */
    public static Task parseFileLine(String line) throws DukeException {
        // Parse the line and create tasks based on the format in the file
        String[] parts = line.split(" \\| ");
        if (parts.length != 3) {
            throw new DukeException("Invalid task format in the file.");
        }

        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case "T":
                // Check if the description contains date information
                String[] todoParts = description.split(" \\(from: | to: ", 3);
                if (todoParts.length == 3) {
                    LocalDate fromDate = LocalDate.parse(todoParts[1]);
                    LocalDate toDate = LocalDate.parse(todoParts[2].substring(0, todoParts[2].length() - 1));
                    return new TodoTask(todoParts[0], fromDate, toDate, isDone);
                } else {
                    throw new DukeException("Invalid todo task format in the file.");
                }

            case "D":
                // Check if the description contains date information
                String[] deadlineParts = description.split(" \\(by: ", 2);
                if (deadlineParts.length == 2) {
                    LocalDate byDate = LocalDate.parse(deadlineParts[1].substring(0, deadlineParts[1].length() - 1));
                    return new DeadlineTask(deadlineParts[0], byDate, isDone);
                } else {
                    throw new DukeException("Invalid deadline task format in the file.");
                }

            case "E":
                // Check if the description contains date information
                String[] eventParts = description.split(" \\(at: ", 2);
                if (eventParts.length == 2) {
                    LocalDate atDate = LocalDate.parse(eventParts[1].substring(0, eventParts[1].length() - 1));
                    return new EventTask(eventParts[0], atDate, isDone);
                } else {
                    throw new DukeException("Invalid event task format in the file.");
                }

            default:
                throw new DukeException("Unknown task type in file.");
        }
    }
}
