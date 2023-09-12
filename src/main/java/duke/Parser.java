package duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDoTask;

import java.lang.reflect.InvocationTargetException;

/**
 * The Parser class is responsible for parsing user input and executing
 * the corresponding commands on the task list.
 */
public class Parser {

    private static TaskList tasks;
    private static Ui ui;

    /**
     * Parses the user input and executes the corresponding command.
     *
     * @param input The user's input command.
     * @param tasks The TaskList instance to perform operations on.
     * @param ui    The Ui instance for user interaction.
     * @return The result of executing the command as a String.
     * @throws DukeException If an error occurs during parsing or execution.
     */
    public static String parse(String input, TaskList tasks, Ui ui) throws DukeException {
        Parser.ui = ui;
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        try {
            switch (command) {
            case "list":
                return tasks.listTask();
            case "delete":
                int task = parseDeleteCommand(parts);
                return tasks.deleteTask(task, ui);
            case "todo":
                ToDoTask toDoTask = parseTodoCommand(parts, false);
                return tasks.addTask(toDoTask, ui);
            case "deadline":
                DeadlineTask deadlineTask = parseDeadline(parts[1], false);
                return tasks.addTask(deadlineTask, ui);
            case "event":
                EventTask eventTask = parseEvent(parts[1], false);
                return tasks.addTask(eventTask, ui);
            case "mark":
                int markNo = parseMarkCommand(parts) - 1;
                return tasks.markTask(markNo, ui);
            case "unmark":
                int unmarkNo = parseUnmarkCommand(parts) - 1;
                return tasks.unmarkTask(unmarkNo, ui);
            case "find":
                return tasks.findTasks(parseFindCommand(parts), ui);
            default:
                return ui.showInvalidCommand();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("The description of a deadline/event cannot be empty.");
        }
    }


    /**
     * Parses the task type, details, and completion status to create a Task object during data loading
     *
     * @param taskType    The type of task (e.g., [T], [D], [E]).
     * @param taskDetails The details of the task.
     * @param isDone      The completion status of the task.
     * @return A Task object created from the provided information.
     * @throws DukeException If an error occurs during parsing.
     */
    public static Task parseLoad(String taskType, String taskDetails, boolean isDone) throws DukeException {
        assert taskType != null : "Task type cannot be null.";
        if (taskType.equalsIgnoreCase("[T")) {
            return new ToDoTask(clearWhitespace(taskDetails), isDone);
        } else if (taskType.equals("[D")) {
            return Parser.parseDeadline(taskDetails, isDone);
        } else if (taskType.equals("[E")) {
            return Parser.parseEvent(taskDetails, isDone);
        } else {
            return null;
        }
    }

    private static String parseFindCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("The description of a find cannot be empty.");
        }
        String keyword = parts[1];
        return keyword;
    }

    /**
     * Parses the task number from a "mark" command.
     *
     * @param parts The input command split into parts.
     * @return The task number to mark as done.
     * @throws DukeException If the command is not properly formatted.
     */
    private static int parseMarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'done' command.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Please pick a number instead of using letters!");
        }
    }
    /**
     * Parses the task number from an "unmark" command.
     *
     * @param parts The input command split into parts.
     * @return The task number to unmark as undone.
     * @throws DukeException If the command is not properly formatted.
     */
    private static int parseUnmarkCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'done' command.");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Please pick a number instead of using letters!");
        }
    }
    /**
     * Parses the task number from a "delete" command.
     *
     * @param parts The input command split into parts.
     * @return The task number to delete.
     * @throws DukeException If the command is not properly formatted.
     */
    private static int parseDeleteCommand(String[] parts) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("Please provide a task number for 'delete' command.");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new DukeException("Please pick a number instead of using letters!");
        }
    }
    /**
     * Parses a "todo" command and creates a ToDoTask.
     *
     * @param parts  The input command split into parts.
     * @param isDone The completion status of the task.
     * @return A ToDoTask created from the input.
     * @throws DukeException If the command is not properly formatted.
     */
    private static ToDoTask parseTodoCommand(String[] parts, boolean isDone) throws DukeException {
        if (parts.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return new ToDoTask(parts[1], isDone);
    }
    /**
     * Parses a "deadline" command and creates a DeadlineTask.
     *
     * @param taskDetails The details of the deadline task.
     * @param isDone      The completion status of the task.
     * @return A DeadlineTask created from the input.
     * @throws DukeException If the command is not properly formatted.
     */
    private static DeadlineTask parseDeadline(String taskDetails, boolean isDone) throws DukeException {
        if (!taskDetails.contains("by:")) {
            throw new DukeException("Remember to include 'by:' after the deadline command!");
        }
        String[] details = taskDetails.split("by:", 2);
        String description = trimDescription(details[0]);
        String by = clearWhitespace(details[1]);
        return new DeadlineTask(description, by, isDone);
    }
    /**
     * Parses an "event" command and creates an EventTask.
     *
     * @param taskDetails The details of the event task.
     * @param isDone      The completion status of the task.
     * @return An EventTask created from the input.
     * @throws DukeException If the command is not properly formatted.
     */
    public static EventTask parseEvent(String taskDetails, boolean isDone) throws DukeException {
        if (!taskDetails.contains("from:") || !taskDetails.contains("to:")) {
            throw new DukeException("Remember to include 'from:' and 'to:' after the event command!");
        }
        String[] details = taskDetails.split("from:", 2);
        String[] innerDetails = details[1].split(" to:", 2);
        String description = trimDescription(details[0]);
        String from = clearWhitespace(innerDetails[0]);
        String to = clearWhitespace(innerDetails[1]);
        return new EventTask(description, from, to, isDone);
    }

    /**
     * Trims leading and trailing whitespace from the given description.
     *
     * @param description The description to be trimmed.
     * @return The trimmed description.
     * @throws DukeException If the trimmed description becomes empty.
     */
    public static String trimDescription(String description) throws DukeException {
        description = description.trim();
        if (description.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        return description;
    }

    /**
     * Removes all whitespace from the given description.
     *
     * @param description The description from which whitespace will be removed.
     * @return The description with all whitespace removed.
     */
    public static String clearWhitespace(String description) {
        description = description.replace(" ", "");
        return description;
    }
}

