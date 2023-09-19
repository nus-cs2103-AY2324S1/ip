package sae.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sae.exceptions.*;
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
                    return handleDelete(store, commandTask);

                case "mark":
                    return handleMark(store, commandTask);

                case "unmark":
                    return handleUnmark(store, commandTask);

                case "list":
                    return store.listTasks();

                case "todo":
                    return handleTodo(store, commandTask);

                case "deadline":
                    return handleDeadline(store, commandTask);

                case "event":
                    return handleEvent(store, commandTask);

                case "find":
                    return handleFind(commandTask, store);

                case "bye" :
                    return handleBye();

                default:
                    throw new SaeException();
            }
        } catch (SaeException | IllegalArgumentException errorMessage) {
            return errorMessage.toString();
        }
    }

    /**
     * Handles finding tasks in the task list based on a keyword.
     *
     * @param commandTask The command and task details, where the second element is the keyword to search for.
     * @param store       The TaskList containing tasks to search within.
     * @return A response message listing tasks matching the keyword.
     * @throws InvalidFindException If the provided keyword is empty or the command is invalid.
     */
    private static String handleFind(String[] commandTask, TaskList store) throws InvalidFindException {
        if (commandTask.length >= 2 && !commandTask[1].isEmpty()) {
            return store.findKeyword(commandTask[1]);
        } else {
            throw new InvalidFindException();
        }
    }

    /**
     * Handles deleting a task.
     *
     * @param store       The TaskList containing tasks.
     * @param commandTask The command and task details.
     * @return A response message to the user's command.
     */
    private static String handleDelete(TaskList store, String[] commandTask) throws InvalidDeleteException{
        assert store != null : "TaskList 'store' should not be null.";

        if (commandTask.length < 2) {
            throw new IllegalArgumentException("Command requires an index.");
        }

        String[] indices = commandTask[1].split(" to ");
        if (indices.length == 2) {
            int startIndex = Integer.parseInt(indices[0]);
            int endIndex = Integer.parseInt(indices[1]);

            if (startIndex >= 1 && endIndex >= startIndex && endIndex <= store.size()) {
                return store.deleteTask(startIndex - 1, endIndex - 1); // Adjust for 0-based indexing
            } else {
                throw new InvalidDeleteException();
            }
        } else {
            int number = Integer.parseInt(commandTask[1]);
            if (number >= 1 && number <= store.size()) {
                return store.deleteTask(number - 1); // Adjust for 0-based indexing
            } else {
                throw new InvalidDeleteException();
            }
        }
    }

    /**
     * Handles marking a task as done.
     *
     * @param store       The TaskList containing tasks.
     * @param commandTask The command and task details.
     * @return A response message to the user's command.
     */
    private static String handleMark(TaskList store, String[] commandTask) throws InvalidMarkException {
        int number = Integer.parseInt(commandTask[1]);
        if (number >= 1 && number <= store.size()) {
            return store.markTaskAsDone(number - 1); // Adjust for 0-based indexing
        } else {
            throw new InvalidMarkException();
        }
    }

    /**
     * Handles unmarking a task as done.
     *
     * @param store       The TaskList containing tasks.
     * @param commandTask The command and task details.
     * @return A response message to the user's command.
     */
    private static String handleUnmark(TaskList store, String[] commandTask) throws InvalidUnMarkException {
        int number = Integer.parseInt(commandTask[1]);
        if (number >= 1 && number <= store.size()) {
            return store.unMarkTaskAsDone(number - 1); // Adjust for 0-based indexing
        } else {
            throw new InvalidUnMarkException();
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
    private static String handleTodo(TaskList store, String[] commandTask) throws InvalidTodoException {

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
    private static String handleDeadline(TaskList store, String[] commandTask) throws InvalidDeadlineException {

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
    private static String handleEvent(TaskList store, String[] commandTask) throws InvalidEventException {
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

