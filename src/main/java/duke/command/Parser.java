package duke.command;

import duke.TaskType;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

import java.time.LocalDate;

public class Parser {

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
                return new MarkDoneCommand(input.toLowerCase());
            case "unmark":
                return new MarkNotDoneCommand(input.toLowerCase());
            case "delete":
                return new DeleteCommand(input.toLowerCase());
            case "find":
                return new FindCommand(input.toLowerCase());
            case "help":
                return new HelpCommand();
            default:
                return new AddCommand(input.toLowerCase());
        }
    }

    public static int extractTaskIndex(String input) throws DukeException {
        String[] parts = input.split(" ");
        assert parts.length > 0 : "Command parts cannot be empty";

        if (parts.length < 2) {
            throw new DukeException("OOPS!!! Please provide the task index.");
        }

        return Integer.parseInt(parts[1].trim()) - 1;
    }

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

        try {
            TaskType taskType = TaskType.valueOf(parts[0].toUpperCase());

            switch (taskType) {
                case TODO:
                    if (parts.length == 1 || parts[1].trim().isEmpty()) {
                        throw new DukeException("OOPS!!! Todo tasks must have a description.");
                    }
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
                    if (parts.length == 1 || parts[1].trim().isEmpty()) {
                        throw new DukeException("OOPS!!! Deadline tasks must have a description.");
                    }

                    String[] deadlineParts = parts[1].split(" /by ");

                    if (deadlineParts.length != 2) {
                        throw new DukeException("OOPS!!! Deadline tasks must include '/by' for date.");
                    }

                    LocalDate byDate = LocalDate.parse(deadlineParts[1]);

                    return new DeadlineTask(deadlineParts[0], byDate, false);

                case EVENT:
                    if (parts.length == 1 || parts[1].trim().isEmpty()) {
                        throw new DukeException("OOPS!!! Event tasks must have a description.");
                    }

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
        assert line != null : "Line cannot be null";
        // Parse the line and create tasks based on the format in the file
        String[] parts = line.split(" \\| ");

        if (parts.length != 3) {
            throw new DukeException("Invalid task format in the file.");
        }

        String taskType = parts[0];
        String description = parts[2];

        boolean isDone = parts[1].equals("1");


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
