package gbot;

import exceptions.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * The chatbot parser for the command inputs of users.
 *
 * @author Gallen Ong
 */
public class Parser {
    private static final String INVALID_TASK = "Although you might not be wrong, I simply do not understand...\n" +
                                              "Kindly enter a valid task number.";
    private static final String INVALID_INDEX = "I apologise, that task does not exist.";
    private static final String INVALID_FORMAT = "I must apologise for correcting you.\n" +
                                                 "Kindly enter a valid date in the YYYY-MM-DD format.";

    /**
     * Returns corresponding methods after parsing user inputs.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     */
    public static String parse(String message, TaskList tasks) throws GBotException {
        if (message.isBlank()) {
            throw new GBotException("Please enter a command.");
        }
        switch (message.strip()) {
        case "list":
            return tasks.listTasks();
        case "deleteAll":
            return tasks.deleteAllTasks();
        case "markAll":
            return tasks.markAllTasks();
        case "unmarkAll":
            return tasks.unmarkAllTasks();
        default:
            return checkPrefix(message, tasks);
        }
    }

    /**
     * Returns corresponding methods after parsing user inputs, given it is
     * not a single command.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     * @throws GBotException If user command is invalid.
     */
    private static String checkPrefix(String message, TaskList tasks) throws RuntimeException {
        assert !message.isBlank();
        String prefix = message.split(" ")[0];
        switch (prefix) {
        case "help":
            return Ui.getHelpMessage();
        case "mark":
            return parseMark(message, tasks);
        case "unmark":
            return parseUnmark(message, tasks);
        case "todo":
            return parseTodo(message, tasks);
        case "deadline":
            return parseDeadline(message, tasks);
        case "event":
            return parseEvent(message, tasks);
        case "delete":
            return parseDelete(message, tasks);
        case "find":
            return parseFind(message, tasks);
        default: // if invalid command provided
            throw new GBotException();
        }
    }

    /**
     * Returns response for Mark after parsing input.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     */
    private static String parseMark(String message, TaskList tasks) {
        String[] words = message.split(" ");
        if (words.length != 2) {
            return INVALID_TASK;
        }
        try {
            return tasks.markTask(Integer.parseInt(words[1]));
        } catch (NumberFormatException e) {
            throw new GBotException(INVALID_TASK);
        } catch (TaskException e) {
            throw new GBotException(INVALID_INDEX);
        }
    }

    /**
     * Returns response for Unmark after parsing input.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     */
    private static String parseUnmark(String message, TaskList tasks) {
        String[] words = message.split(" ");
        if (words.length != 2) {
            return INVALID_TASK;
        }
        try {
            return tasks.unmarkTask(Integer.parseInt(words[1]));
        } catch (NumberFormatException e) {
            throw new GBotException(INVALID_TASK);
        } catch (TaskException e) {
            throw new GBotException(INVALID_INDEX);
        }
    }

    /**
     * Returns response for adding a Todo task after parsing input.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     */
    private static String parseTodo(String message, TaskList tasks) {
        String desc = message.substring(4);
        if (desc.isBlank()) {
            return "I apologise. Kindly input a task description.";
        }
        return tasks.addTodo(desc.strip());
    }

    /**
     * Returns response for adding a Deadline task after parsing input.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     */
    private static String parseDeadline(String message, TaskList tasks) {
        String details = message.substring(8);
        String[] taskDesc = details.split(" /by ");
        if (taskDesc.length != 2) {
            return "I apologise for correcting you. Kindly follow the following:\n" +
                   "deadline (task) /by (deadline in YYYY-MM-DD)\n" +
                   "eg. deadline return book /by 2023-09-21";
        }

        try {
            return tasks.addDeadline(taskDesc[0].strip(), taskDesc[1].strip());
        } catch (DateTimeParseException e) {
            throw new GBotException(INVALID_FORMAT);
        }
    }

    /**
     * Returns response for adding an Event task after parsing input.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     */
    private static String parseEvent(String message, TaskList tasks) {
        String details = message.substring(5);
        String[] inputSplitByFrom = details.split(" /from");
        String[] inputSplitByTo = details.split(" /to");
        if (inputSplitByFrom.length != 2 || inputSplitByTo.length != 2) {
            return "I apologise for correcting you. Kindly follow the following:\n" +
                    "event (task) /from (start in YYYY-MM-DD) /to (end in YYYY-MM-DD)\n" +
                    "eg. event attend meeting /from 2023-09-21 /to 2023-09-22";
        }

        String[] inputAfterFrom = inputSplitByFrom[1].split(" /to");
        String desc;
        String fromDate;
        String toDate;

        if (inputAfterFrom.length == 2) {
            System.out.println(inputAfterFrom.length);
            desc = inputSplitByFrom[0];
            fromDate = inputAfterFrom[0];
            toDate = inputAfterFrom[1];
        } else {
            desc = inputSplitByTo[0];
            fromDate = inputSplitByTo[1].split(" /from")[1];
            toDate = inputSplitByTo[1].split(" /from")[0];
        }

        try {
            return tasks.addEvent(desc.strip(), fromDate.strip(), toDate.strip());
        } catch (DateTimeParseException e) {
            throw new GBotException(INVALID_FORMAT);
        }
    }

    /**
     * Returns response for deleting a task after parsing input.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     */
    private static String parseDelete(String message, TaskList tasks) {
        String[] words = message.split(" ");
        if (words.length != 2) {
            return INVALID_TASK;
        }
        try {
            return tasks.deleteTask(Integer.parseInt(words[1]));
        } catch (NumberFormatException e) {
            throw new GBotException(INVALID_TASK);
        } catch (TaskException e) {
            throw new GBotException(INVALID_INDEX);
        }
    }

    /**
     * Returns response for finding a task based on keyword after parsing input.
     *
     * @param message The user input.
     * @param tasks The TaskList object that stores tasks.
     */
    private static String parseFind(String message, TaskList tasks) {
        String[] words = message.split(" ");
        if (words.length != 2) {
            return "I wish I could read minds like you. Do kindly enter a keyword though.";
        }
        return tasks.find(message.substring(4));
    }

    /**
     * Loads tasks line by line from file provided and adds to task list provided.
     *
     * @param taskInFile The current task line in the file.
     * @param tasks The task list provided and to be updated.
     */
    public static void loadTaskFromFile(String taskInFile, ArrayList<Task> tasks) {
        String[] fields = taskInFile.split(" \\| ");
        switch (fields[0]) {
        case "T":
            tasks.add(new Todo(fields[1], fields[2]));
            break;
        case "D":
            tasks.add(new Deadline(fields[1], fields[2], fields[3]));
            break;
        case "E":
            tasks.add(new Event(fields[1], fields[2], fields[3], fields[4]));
            break;
        default:
            break;
        }
    }
}
