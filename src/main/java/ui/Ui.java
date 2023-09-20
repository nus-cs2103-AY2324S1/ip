package ui;

import customexceptions.WrongCommandException;
import parser.Parser;
import storage.Storage;
import tasks.*;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Ui class provides methods for interacting with the user.
 */
public class Ui {
    private Parser parser;
    private Storage store;

    /**
     * Processes user commands and provides responses.
     *
     * @param storage The storage object for managing tasks.
     * @param task    The task list.
     * @param parse   The parser for parsing user input.
     * @param command The user's command.
     * @return A response to the user's command.
     * @throws IOException If an error occurs while performing file operations.
     */
    public String takeCommands(Storage storage, TaskList task, Parser parse, String command) throws IOException {
        parser = parse;
        store = storage;

        ArrayList<String> commands = new ArrayList<>();
        String[] commandList = {"todo", "deadline", "event", "mark", "unmark", "bye"};
        Collections.addAll(commands, commandList);

        if (command.equalsIgnoreCase("bye")) {
            return command + " " + command + "...please come back soon :(";
        } else {
            if (command.equalsIgnoreCase("list")) {
                return listCommand(task);
            } else if (command.toLowerCase().contains("unmark")) {
                return unmarkCommand(command, task, store);
            } else if (command.toLowerCase().contains("mark")) {
                return markCommand(command, task, store);
            } else if (command.toLowerCase().contains("delete")) {
                return deleteCommand(command, task, store);
            } else if (command.toLowerCase().contains("find ")) {
                return findCommand(command, task);
            } else if (command.toLowerCase().contains("archive")) {
                return archiveCommand(command, task, store);
            } else {
                return processTask(command, task, parser, commands);
            }
        }
    }

    /**
     * Handles the "archive" command to archive a task.
     *
     * @param input The user's input.
     * @param tasks The task list.
     * @param store The storage for saving tasks to a file.
     * @return A response message indicating which task has been archived
     * @throws IOException If an error occurs while performing file operations.
     */
    private String archiveCommand(String input, TaskList tasks, Storage store) throws IOException {
        StringBuilder result = new StringBuilder();
        int number = parser.findNum(input);
        try {
            Task index = tasks.retrieve(number - 1);
            store.archive(index);
            tasks.remove(index);
            result.append("I have archived the following task:\n").append(index).append("\nYour list has ")
                    .append(tasks.size()).append(" items left\n\n");
        } catch (IndexOutOfBoundsException e) {
            result.append(number).append(" is too high! List size is only ").append(tasks.size()).append("\n");
        }
        store.overwrite();
        return result.toString();
    }

    /**
     * Handles the "list" command to display the task list.
     *
     * @param tasks The task list.
     * @return A string representing the task list.
     */
    private String listCommand(TaskList tasks) {
        return tasks.printList() + "\n";
    }

    /**
     * Handles the "unmark" command to mark a task as undone.
     *
     * @param input The user's input.
     * @param tasks The task list.
     * @param store The storage for saving tasks to a file.
     * @return A response message.
     * @throws IOException If an error occurs while performing file operations.
     */
    private String unmarkCommand(String input, TaskList tasks, Storage store) throws IOException {
        StringBuilder result = new StringBuilder();
        int number = parser.findNum(input);
        try {
            result.append(tasks.retrieve(number - 1).unmarkDone());
        } catch (IndexOutOfBoundsException e) {
            result.append(number).append(" is too high! List size is only ").append(tasks.size()).append("\n");
        } finally {
            store.overwrite();
        }
        return result.toString();
    }

    /**
     * Handles the "mark" command to mark a task as done.
     *
     * @param input The user's input.
     * @param tasks The task list.
     * @param store The storage for saving tasks to a file.
     * @return A response message.
     * @throws IOException If an error occurs while performing file operations.
     */
    private String markCommand(String input, TaskList tasks, Storage store) throws IOException {
        StringBuilder result = new StringBuilder();
        int number = parser.findNum(input);
        try {
            result.append(tasks.retrieve(number - 1).markDone());
        } catch (IndexOutOfBoundsException e) {
            result.append(number).append(" is too high! List size is only ").append(tasks.size()).append("\n");
        } finally {
            store.overwrite();
        }
        return result.toString();
    }

    /**
     * Handles the "delete" command to remove a task.
     *
     * @param input The user's input.
     * @param tasks The task list.
     * @param store The storage for saving tasks to a file.
     * @return A response message.
     * @throws IOException If an error occurs while performing file operations.
     */
    private String deleteCommand(String input, TaskList tasks, Storage store) throws IOException {
        StringBuilder result = new StringBuilder();
        int number = parser.findNum(input);
        try {
            Task index = tasks.retrieve(number - 1);
            tasks.remove(index);
            result.append("I have deleted the following task:\n").append(index.toString()).append("\nYour list has ")
                    .append(tasks.size()).append(" items left\n\n");
        } catch (IndexOutOfBoundsException e) {
            result.append(number).append(" is too high! List size is only ").append(tasks.size()).append("\n");
        }
        store.overwrite();
        return result.toString();
    }

    /**
     * Handles the "find" command to search for tasks.
     *
     * @param input The user's input.
     * @param tasks The task list.
     * @return A response message.
     */
    private String findCommand(String input, TaskList tasks) {
        return "Here are the matching items in your list:\n" + "\n" +
                tasks.find(parser.find(input)) + "\n";
    }

    /**
     * Processes user input to create and add tasks.
     *
     * @param input    The user's input.
     * @param tasks    The task list.
     * @param parser   The parser for parsing user input.
     * @param commands The list of valid commands.
     * @return A response message.
     * @throws IOException If an error occurs while performing file operations.
     */
    private String processTask(String input, TaskList tasks, Parser parser, ArrayList<String> commands) throws IOException {
        StringBuilder result = new StringBuilder();
        if (input.toLowerCase().contains("todo ")) {
            Task newTask = new ToDos(parser.taskName(input), false);
            tasks.add(newTask);
            store.write(newTask);
            result.append("Okay! I have added the following task\n").append(newTask).append("\n");
        } else if (input.toLowerCase().contains("deadline ")) {
            try {
                Task newTask = new Deadlines(parser.taskName(input), parser.taskBy(input), false);
                tasks.add(newTask);
                result.append("Okay! I have added the following task\n").append(newTask).append("\n");
                store.write(newTask);
            } catch (DateTimeParseException e) {
                result.append("Please enter a valid date in the format yyyy-mm-dd\n");
            }

        } else if (input.toLowerCase().contains("event ")) {
            try {
                Task newTask = new Events(parser.taskName(input), parser.taskFrom(input), parser.taskTo(input), false);
                tasks.add(newTask);
                result.append("Okay! I have added the following task\n").append(newTask).append("\n");
                store.write(newTask);
            } catch (DateTimeParseException e) {
                result.append("Please enter a valid date in the format yyyy-mm-dd\n");
            }
        } else {
            try {
                if (!commands.contains(input.split(" ")[0].toLowerCase())) {
                    throw new WrongCommandException(input);
                }
            } catch (WrongCommandException e) {
                result.append(e.getMessage()).append("\n");
            }
        }
        return result.toString();
    }
}
