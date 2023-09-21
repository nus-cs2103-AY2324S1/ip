package duke;

import duke.command.*;
import duke.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for parsing user input and converting it into
 * executable commands and tasks for the Duke application.
 */
public class Parser {

    /**
     * Parses the user input to determine the appropriate command to execute.
     *
     * @param input The user's input.
     * @return A Command object representing the parsed command.
     * @throws DukeException If the input cannot be parsed or is invalid.
     */
    public static Command parseCommand(String input) throws DukeException {
        String[] parts = input.split(" ", 2);
        String commandType = parts[0].toLowerCase();

        switch (commandType) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "hi":
                return new HiCommand();
            case "mark":
                if (parts.length > 1) {
                    return new MarkDoneCommand(input.toLowerCase());
                } else {
                    throw new DukeException("Please provide a task number to mark as done.");
                }
            case "unmark":
                if (parts.length > 1) {
                    return new MarkNotDoneCommand(input.toLowerCase());
                } else {
                    throw new DukeException("Please provide a task number to mark as not done.");
                }
            case "delete":
                if (parts.length > 1) {
                    return new DeleteCommand(input.toLowerCase());
                } else {
                    throw new DukeException("Please provide a task number to delete.");
                }
            case "find":
                if (parts.length > 1) {
                    return new FindCommand(input.toLowerCase());
                }
                break;
            case "help":
                return new HelpCommand();
            case "todo":
            case "deadline":
            case "event":
                // Handle adding tasks
                return new AddCommand(input.toLowerCase());
            default:
                throw new DukeException("Unrecognized command: " + input);
        }
        return null;
    }

    /**
     * Extracts the task index from a user input string.
     *
     * @param input The user's input.
     * @return The task index.
     * @throws DukeException If the input cannot be parsed or is invalid.
     */
    public static int extractTaskIndex(String input) throws DukeException {
        String[] parts = input.split(" ");
        assert parts.length > 0 : "Command parts cannot be empty";

        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please provide the task index.");
        }

        return Integer.parseInt(parts[1].trim()) - 1;
    }

    /**
     * Extracts the keyword from a user input string for searching tasks.
     *
     * @param input The user's input.
     * @return The extracted keyword.
     */
    public static String extractKeyword(String input) {
        return input.substring("find".length()).trim();
    }

    /**
     * Parses the given command and generates a Task object based on its content.
     *
     * @param command The user command to parse.
     * @return A Task object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or is invalid.
     */
    public static Task parseTask(String command) throws DukeException {
        assert command != null : "Command cannot be null";
        String[] parts = command.split(" ", 2);
        TaskPriority priority;
        String description;
        Task task;

        // Check if the command contains "/priority"
        if (command.contains("/priority")) {
            String[] priorityParts = command.split("/priority", 2);
            String priorityValue = priorityParts[1].trim().split(" ")[0]; // Extract the first word as priority
            priority = TaskPriority.parse(priorityValue); // Parse the priority

            // Remove the "/priority" part from the description
            parts[1] = parts[1].replace(" /priority " + priorityValue, "").trim();
        } else {
            priority = TaskPriority.LOW;
        }

        try {
            TaskType taskType = TaskType.valueOf(parts[0].toUpperCase());

            switch (taskType) {
                case TODO:
                    if (parts.length == 1 || parts[1].trim().isEmpty()) {
                        throw new DukeException("OOPS!!! TODO tasks must have a description.");
                    }
                    description = parts[1].trim();
                    task = parseTodoTask(description, priority);
                    break;
                case DEADLINE:
                    if (parts.length == 1 || parts[1].trim().isEmpty()) {
                        throw new DukeException("OOPS!!! DEADLINE tasks must have a description.");
                    }
                    description = parts[1].trim();
                    task = parseDeadlineTask(description, priority);
                    break;
                case EVENT:
                    if (parts.length == 1 || parts[1].trim().isEmpty()) {
                        throw new DukeException("OOPS!!! EVENT tasks must have a description.");
                    }
                    description = parts[1].trim();
                    task = parseEventTask(description, priority);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return task;
    }

    /**
     * Parses a command string to create a TodoTask object based on its content.
     *
     * @param description The description of the TodoTask.
     * @param priority    The priority of the TodoTask.
     * @return A TodoTask object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or is invalid.
     */
    static Task parseTodoTask(String description, TaskPriority priority) throws DukeException {
        String[] descriptionParts = description.split(" /from ");

        if (descriptionParts.length != 2) {
            throw new DukeException("OOPS!!! Todo tasks must include '/from' for date.");
        }

        String[] dateRange = descriptionParts[1].split(" /to ");

        if (dateRange.length != 2) {
            throw new DukeException("OOPS!!! Todo tasks must include '/to' for the end date.");
        }

        String taskDescription = descriptionParts[0];

        LocalDate fromDate;
        try {
            fromDate = LocalDate.parse(dateRange[0].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use YYYY-MM-DD format for dates.");
        }

        LocalDate toDate;
        try {
            toDate = LocalDate.parse(dateRange[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use YYYY-MM-DD format for dates.");
        }

        if (fromDate.isAfter(toDate)) {
            throw new DukeException("OOPS!!! 'From' date cannot be later than 'to' date.");
        }

        return new TodoTask(taskDescription, fromDate, toDate, false, priority);
    }

    /**
     * Parses a command string to create a DeadlineTask object based on its content.
     *
     * @param description The description of the DeadlineTask.
     * @param priority    The priority of the DeadlineTask.
     * @return A DeadlineTask object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or is invalid.
     */
    static Task parseDeadlineTask(String description, TaskPriority priority) throws DukeException {
        String[] deadlineParts = description.split(" /by ");

        if (deadlineParts.length != 2) {
            throw new DukeException("OOPS!!! Deadline tasks must include '/by' for date.");
        }

        String taskDescription = deadlineParts[0];
        LocalDate byDate;
        try {
            byDate = LocalDate.parse(deadlineParts[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use YYYY-MM-DD format for dates.");
        }

        if (taskDescription.isEmpty()) {
            throw new DukeException("OOPS!!! Deadline tasks must have a description.");
        }

        return new DeadlineTask(taskDescription, byDate, false, priority);
    }

    /**
     * Parses a command string to create an EventTask object based on its content.
     *
     * @param description The description of the EventTask.
     * @param priority    The priority of the EventTask.
     * @return An EventTask object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or is invalid.
     */
    static Task parseEventTask(String description, TaskPriority priority) throws DukeException {
        String[] eventParts = description.split(" /at ");

        if (eventParts.length != 2) {
            throw new DukeException("OOPS!!! Event tasks must include '/at' for date.");
        }

        String taskDescription = eventParts[0];
        LocalDate atDate;
        try {
            atDate = LocalDate.parse(eventParts[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use YYYY-MM-DD format for dates.");
        }

        if (taskDescription.isEmpty()) {
            throw new DukeException("OOPS!!! Event tasks must have a description.");
        }

        return new EventTask(taskDescription, atDate, false, priority);
    }


    /**
     * Parses a line from a file and generates a Task object based on its content.
     *
     * @param line The line from the file to parse.
     * @return A Task object representing the parsed line.
     * @throws DukeException If the line cannot be parsed or is invalid.
     */
    public static Task parseFileLine(String line) throws DukeException {
        assert line != null : "Line cannot be null";
        // Parse the line and create tasks based on the format in the file
        String[] parts = line.split(" \\| ");
        if (parts.length != 4) {
            throw new DukeException("Invalid task format in the file.");
        }
        String taskType = parts[0];
        String description = parts[3];
        boolean isDone = parts[1].equals("1");
        TaskPriority priority = TaskPriority.parse(parts[2]);


        switch (taskType) {
            case "T":
                // Check if the description contains date information
                String[] todoParts = description.split(" \\(from: | to: ", 3);

                if (todoParts.length == 3) {
                    LocalDate fromDate = LocalDate.parse(todoParts[1]);
                    LocalDate toDate = LocalDate.parse(todoParts[2].substring(0, todoParts[2].length() - 1));
                    return new TodoTask(todoParts[0], fromDate, toDate, isDone, priority);
                } else {
                    throw new DukeException("Invalid todo task format in the file.");
                }

            case "D":
                // Check if the description contains date information
                String[] deadlineParts = description.split(" \\(by: ", 2);

                if (deadlineParts.length == 2) {
                    LocalDate byDate = LocalDate.parse(deadlineParts[1].substring(0, deadlineParts[1].length() - 1));
                    return new DeadlineTask(deadlineParts[0], byDate, isDone, priority);
                } else {
                    throw new DukeException("Invalid deadline task format in the file.");
                }

            case "E":
                // Check if the description contains date information
                String[] eventParts = description.split(" \\(at: ", 2);

                if (eventParts.length == 2) {
                    LocalDate atDate = LocalDate.parse(eventParts[1].substring(0, eventParts[1].length() - 1));
                    return new EventTask(eventParts[0], atDate, isDone, priority);
                } else {
                    throw new DukeException("Invalid event task format in the file.");
                }

            default:
                throw new DukeException("Unknown task type in file.");
        }
    }
}
