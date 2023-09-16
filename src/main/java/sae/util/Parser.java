package sae.util;

import sae.task.TaskList;
import sae.exceptions.InvalidTodoException;
import sae.exceptions.SaeException;
import sae.exceptions.InvalidDeadlineException;
import sae.exceptions.InvalidEventException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static void executeCommand(TaskList store, String[] commandTask) {
        if (commandTask.length == 0) {
            System.out.println("Invalid command.");
            return;
        }

        String command = commandTask[0];

        try {
            switch (command) {
                case "delete":
                case "mark":
                case "unmark":
                    handleDeleteMarkUnmark(store, command, commandTask);
                    break;

                case "list":
                    store.listTasks();
                    break;

                case "todo":
                    handleTodo(store, commandTask);
                    break;

                case "deadline":
                    handleDeadline(store, commandTask);
                    break;

                case "event":
                    handleEvent(store, commandTask);
                    break;

                case "find":
                    store.findKeyword(commandTask[1]);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid command.");
            }
        } catch (SaeException | IllegalArgumentException errorMessage) {
            System.out.println("☹ OOPS!!! " + errorMessage.getMessage());
        }
    }

    private static void handleDeleteMarkUnmark(TaskList store, String command, String[] commandTask) {
        if (commandTask.length < 2) {
            throw new IllegalArgumentException("Command requires an index.");
        }
        int number = Integer.parseInt(commandTask[1]);
        if (number < 1 || number > store.size()) {
            throw new IllegalArgumentException("Invalid task index.");
        }
        number--; // Adjust for 0-based indexing
        if (command.equals("delete")) {
            store.deleteTask(number);
        } else if (command.equals("mark")) {
            store.markTaskAsDone(number);
        } else {
            store.unMarkTaskAsDone(number);
        }
    }

    private static void handleTodo(TaskList store, String[] commandTask) throws SaeException {
        if (commandTask.length < 2 || commandTask[1].isEmpty()) {
            throw new InvalidTodoException();
        }
        store.addToDoTask(commandTask[1]);
    }

    private static void handleDeadline(TaskList store, String[] commandTask) throws SaeException {
        if (commandTask.length < 2 || !commandTask[1].contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String[] parts = commandTask[1].split("/by");
        String description = parts[0].trim();
        String dateTimeString = parts[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        store.addDeadlineTask(description, dateTime);
    }

    private static void handleEvent(TaskList store, String[] commandTask) throws SaeException {
        if (commandTask.length < 2 || !commandTask[1].contains("/from") || !commandTask[1].contains("/to")) {
            throw new InvalidEventException();
        }
        String[] eventParts = commandTask[1].split("/from|/to");
        store.addEventTask(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
    }

}

