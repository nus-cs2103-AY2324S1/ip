package sae.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import sae.exceptions.InvalidDeadlineException;
import sae.exceptions.InvalidEventException;
import sae.exceptions.InvalidTodoException;
import sae.exceptions.SaeException;
import sae.task.TaskList;


/**
 * The Parser class handles user commands and interacts with TaskList and Ui classes.
 */
public class Parser {

    /**
     * Executes a command based on user input.
     *
     * @param store The TaskList containing tasks.
     * @param str   The user input string.
     * @return A response message to the user's command.
     */
    public static String executeCommand(TaskList store, String str) {

        assert str != null : "Input string 'str' is null.";

        String[] commandTask = str.split(" ", 2);

        assert commandTask.length >= 1 : "Command task array should have at least one element.";

        String command = commandTask[0];
        if (commandTask.length == 0) {
            return "Invalid command.";
        }

        try {
            switch (command) {
                case "delete":
                case "mark":
                case "unmark":
                    return handleDeleteMarkUnmark(store, command, commandTask);

                case "list":
                    return store.listTasks();

                case "todo":
                    return handleTodo(store, commandTask);

                case "deadline":
                    return handleDeadline(store, commandTask);

                case "event":
                    return handleEvent(store, commandTask);

                case "find":
                    return store.findKeyword(commandTask[1]);

                case "bye" :
                    return handleBye();

                default:
                    throw new IllegalArgumentException("Invalid command.");
            }
        } catch (SaeException | IllegalArgumentException errorMessage) {
            return "☹ OOPS!!! " + errorMessage.getMessage();
        }
    }

    /**
     * Handles deleting, marking, or unmarking a task.
     *
     * @param store       The TaskList containing tasks.
     * @param command     The command (delete, mark, unmark).
     * @param commandTask The command and task details.
     * @return A response message to the user's command.
     */
    private static String handleDeleteMarkUnmark(TaskList store, String command, String[] commandTask) {
        assert store != null : "TaskList 'store' should not be null.";

        if (commandTask.length < 2) {
            throw new IllegalArgumentException("Command requires an index.");
        }

        if (command.equals("delete")) {
            String[] indices = commandTask[1].split(" to ");
            if (indices.length == 2) {
                try {
                    int startIndex = Integer.parseInt(indices[0]);
                    int endIndex = Integer.parseInt(indices[1]);

                    if (startIndex >= 1 && endIndex >= startIndex && endIndex <= store.size()) {
                        return store.deleteTask(startIndex - 1, endIndex - 1); // Adjust for 0-based indexing
                    } else {
                        throw new IllegalArgumentException("Invalid task index range.");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid task index format.");
                }
            } else {
                int number = Integer.parseInt(commandTask[1]);
                if (number >= 1 && number <= store.size()) {
                    return store.deleteTask(number - 1); // Adjust for 0-based indexing
                } else {
                    throw new IllegalArgumentException("Invalid task index.");
                }
            }
        } else if (command.equals("mark")) {
            int number = Integer.parseInt(commandTask[1]);
            if (number >= 1 && number <= store.size()) {
                return store.markTaskAsDone(number - 1); // Adjust for 0-based indexing
            } else {
                throw new IllegalArgumentException("Invalid task index.");
            }
        } else {
            int number = Integer.parseInt(commandTask[1]);
            if (number >= 1 && number <= store.size()) {
                return store.unMarkTaskAsDone(number - 1); // Adjust for 0-based indexing
            } else {
                throw new IllegalArgumentException("Invalid task index.");
            }
        }
    }


    /**
     * Handles adding a todo task.
     *
     * @param store       The TaskList containing tasks.
     * @param commandTask The command and task details.
     * @return A response message to the user's command.
     * @throws SaeException If the todo task is invalid.
     */
    private static String handleTodo(TaskList store, String[] commandTask) throws SaeException {
        assert store != null : "TaskList 'store' should not be null.";

        if (commandTask.length < 2 || commandTask[1].isEmpty()) {
            throw new InvalidTodoException();
        }
        return store.addToDoTask(commandTask[1]);
    }

    /**
     * Handles adding a deadline task.
     *
     * @param store       The TaskList containing tasks.
     * @param commandTask The command and task details.
     * @return A response message to the user's command.
     * @throws SaeException If the deadline task is invalid.
     */
    private static String handleDeadline(TaskList store, String[] commandTask) throws SaeException {
        assert store != null : "TaskList 'store' should not be null.";

        if (commandTask.length < 2 || !commandTask[1].contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String[] parts = commandTask[1].split("/by");
        String description = parts[0].trim();
        String dateTimeString = parts[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        return store.addDeadlineTask(description, dateTime);
    }

    /**
     * Handles adding an event task.
     *
     * @param store       The TaskList containing tasks.
     * @param commandTask The command and task details.
     * @return A response message to the user's command.
     * @throws SaeException If the event task is invalid.
     */
    private static String handleEvent(TaskList store, String[] commandTask) throws SaeException {
        if (commandTask.length < 2 || !commandTask[1].contains("/from") || !commandTask[1].contains("/to")) {
            throw new InvalidEventException();
        }
        String[] eventParts = commandTask[1].split("/from|/to");
        return store.addEventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
    }

    /**
     * Handles the "bye" command.
     *
     * @return A farewell message.
     */
    private static String handleBye() {
        Ui ui = new Ui();
        return ui.bidGoodbye();
    }
}

