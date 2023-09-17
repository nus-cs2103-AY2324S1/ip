package sae.util;

import sae.task.TaskList;
import sae.exceptions.InvalidTodoException;
import sae.exceptions.SaeException;
import sae.exceptions.InvalidDeadlineException;
import sae.exceptions.InvalidEventException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static String executeCommand(TaskList store, String str) {

        assert str != null : "Input string 'str' is null.";

        String[] commandTask = str.split(" ", 2);

        assert commandTask.length >= 1 : "Command task array should have at least one element.";

        String command = commandTask[0];
        if (commandTask.length == 0) {
            System.out.println("Invalid command.");
            return "Invalid command.";
        }

        try {
            switch (command) {
                case "delete":
                case "mark":
                case "unmark":
                    return handleDeleteMarkUnmark(store, command, commandTask);
                    //break;

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
                    Ui ui = new Ui();
                    return ui.bidGoodbye();

                default:
                    assert false : "Unhandled command type: " + command;

                    throw new IllegalArgumentException("Invalid command.");
            }
        } catch (SaeException | IllegalArgumentException errorMessage) {
            return "☹ OOPS!!! " + errorMessage.getMessage();
        }
    }

    private static String handleDeleteMarkUnmark(TaskList store, String command, String[] commandTask) {
        assert store != null : "TaskList 'store' should not be null.";

        if (commandTask.length < 2) {
            throw new IllegalArgumentException("Command requires an index.");
        }

        int number = Integer.parseInt(commandTask[1]);
        if (number < 1 || number > store.size()) {
            throw new IllegalArgumentException("Invalid task index.");
        }

        assert number >= 1 && number <= store.size() : "Invalid task index.";

        number--; // Adjust for 0-based indexing
        if (command.equals("delete")) {
            return store.deleteTask(number);
        } else if (command.equals("mark")) {
            return store.markTaskAsDone(number);
        } else {
            return store.unMarkTaskAsDone(number);
        }
    }

    private static String handleTodo(TaskList store, String[] commandTask) throws SaeException {
        assert store != null : "TaskList 'store' should not be null.";

        if (commandTask.length < 2 || commandTask[1].isEmpty()) {
            throw new InvalidTodoException();
        }
        return store.addToDoTask(commandTask[1]);
    }

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

    private static String handleEvent(TaskList store, String[] commandTask) throws SaeException {
        if (commandTask.length < 2 || !commandTask[1].contains("/from") || !commandTask[1].contains("/to")) {
            throw new InvalidEventException();
        }
        String[] eventParts = commandTask[1].split("/from|/to");
        return store.addEventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
    }

}

