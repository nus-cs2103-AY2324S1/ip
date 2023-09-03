package duke.main;

import duke.exceptions.InvalidArgumentException;

/**
 * The Parser class is responsible for parsing user input and extracting relevant information.
 * It provides methods to parse different types of tasks and commands.
 */
public class Parser {

    /**
     * Extracts the command keyword from the user input.
     *
     * @param input The user input string.
     * @return The command keyword extracted from the input.
     */
    protected String getCommand(String input) {
        return input.split(" ")[0];
    }

    /**
     * Parses the task description for a ToDo task from the user input.
     *
     * @param input The user input string.
     * @return The task description for the ToDo task.
     * @throws InvalidArgumentException If the task description is missing or empty.
     */
    protected String parseToDo(String input) throws InvalidArgumentException {
        int indexOfSpace = input.indexOf(" ");
        if (indexOfSpace == -1 || indexOfSpace == input.length() - 1) {
            throw new InvalidArgumentException("Please enter a task description.");
        }
        String taskName = input.substring(input.indexOf(" ") + 1).trim();
        if (taskName.isEmpty()) {
            throw new InvalidArgumentException("Please enter a task description.");
        }

        return taskName;
    }

    /**
     * Parses the task name and due date for a Deadline task from the user input.
     *
     * @param input The user input string.
     * @return An array containing the task name and due date.
     * @throws InvalidArgumentException If the input format is incorrect.
     */
    protected String[] parseDeadline(String input) throws InvalidArgumentException {
        String suffix = input.substring(input.indexOf(" ") + 1);
        String[] parts = suffix.split(" /due ");
        if (parts.length != 2) {
            throw new InvalidArgumentException("Invalid format for deadline. " +
                    "Please use: deadline task name /due due Date");
        }
        String taskName = parts[0].trim();
        String dueDate = parts[1].trim();
        return new String[]{taskName, dueDate};
    }

    /**
     * Parses the task name, start time, and end time for an Event task from the user input.
     *
     * @param input The user input string.
     * @return An array containing the task name, start time, and end time.
     * @throws InvalidArgumentException If the input format is incorrect.
     */
    protected String[] parseEvent(String input) throws InvalidArgumentException {
        String suffix = input.substring(input.indexOf(" ") + 1);
        String[] parts = suffix.split(" /from ");
        if (parts.length != 2) {
            throw new InvalidArgumentException("Invalid format for event. " +
                    "Please use: event task_name /from start /to end");
        }
        String taskName = parts[0].trim();
        String[] timeParts = parts[1].split(" /to ");
        if (timeParts.length != 2) {
            throw new InvalidArgumentException("Invalid format for event. " +
                    "Please use: event task_name /from start /to end");
        }
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        return new String[]{taskName, from, to};
    }
}
