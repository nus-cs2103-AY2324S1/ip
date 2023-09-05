package duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDoTask;

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
     * @throws DukeException If an error occurs during parsing or execution.
     */
    public static void parse(String input, TaskList tasks, Ui ui) throws DukeException {
        Parser.ui = ui;
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "list":
            tasks.listTask();
            break;
        case "delete":
            int task = parseDeleteCommand(parts);
            tasks.deleteTask(task, ui);
            break;
        case "todo":
            ToDoTask toDoTask = parseTodoCommand(parts, false);
            tasks.addTask(toDoTask, ui);
            break;
        case "deadline":
            DeadlineTask deadlineTask = parseDeadline(parts[1], false);
            tasks.addTask(deadlineTask, ui);
            break;
        case "event":
            EventTask eventTask = parseEvent(parts[1], false);
            tasks.addTask(eventTask, ui);
            break;
        case "mark":
            int markTask = parseMarkCommand(parts) - 1;
            if (markTask < 0 || markTask >= tasks.getTotalTasks()) {
                System.out.println("There is no task for this number!");
                break;
            }
            tasks.getTask(markTask).markTask();
            break;
        case "unmark":
            int unmarkTask = parseUnmarkCommand(parts) - 1;
            if (unmarkTask < 0 || unmarkTask >= tasks.getTotalTasks()) {
                System.out.println("There is no task for this number!");
                break;
            }
            tasks.getTask(unmarkTask).unmarkTask();
            break;
        case "find":
            tasks.findTasks(parseFindCommand(parts), ui);
            break;
        default:
            System.out.println("You inputted an invalid command! Please try deadline, todo, or event :)");
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
    public static Task parse(String taskType, String taskDetails, boolean isDone) throws DukeException {
        if (taskType.equalsIgnoreCase("[T")) {
            taskDetails = taskDetails.trim();
            return new ToDoTask(taskDetails, isDone);
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
        return parts[1];
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
        String description = details[0];
        description = description.trim();
        String by = details[1];
        by = by.replace(" ", "");
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
        String description = details[0];
        description = description.trim();
        String from = innerDetails[0];
        from = from.replace(" ", "");
        String to = innerDetails[1];
        to = to.replace(" ", "");

        return new EventTask(description, from, to, isDone);
    }
}
