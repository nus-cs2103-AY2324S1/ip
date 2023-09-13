package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Reads the users command.
 */
public class Parser {

    /**
     * Reads the user's command and executes the corresponding task operation.
     * @param command The user's input command
     * @param tasklist The task list that stores the tasks
     * @return A string message that indicates the result of the operation.
     */
    public static String readTask(String command, TaskList tasklist) {
        String[] commandSplit = command.split(" ");
        String firstWord = commandSplit[0].toLowerCase(); // Convert to lowercase for case insensitivity

        switch (firstWord) {
        case "bye":
            System.exit(0);
            return Ui.exit();
        case "list":
            return Ui.listTasks(tasklist);
        case "find":
            if (commandSplit.length > 1) {
                return tasklist.findTasks(commandSplit[1]);
            } else {
                return Ui.showError(new DukeException("Please provide a keyword to search for."));
            }
        case "todo":
        case "deadline":
        case "event":
            return addTask(command, tasklist);
        case "mark":
        case "unmark":
        case "delete":
            return handleMarkUnmarkDelete(commandSplit, tasklist);
        default:
            return Ui.showError(new DukeException("I'm sorry, but I don't know what that means :-("));
        }
    }

    private static String addTask(String command, TaskList tasklist) {
        try {
            if (command.startsWith("todo")) {
                return addTodoTask(command, tasklist);
            } else if (command.startsWith("deadline")) {
                return addDeadlineTask(command, tasklist);
            } else if (command.startsWith("event")) {
                return addEventTask(command, tasklist);
            } else {
                return Ui.showError(new DukeException("I'm sorry, but I don't know what that means :-("));
            }
        } catch (DukeException e) {
            return Ui.showError(e);
        }
    }

    private static String addTodoTask(String command, TaskList tasklist) throws DukeException {
        String taskDescription = command.substring(5).trim();
        if (taskDescription.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task t = new Todo(taskDescription);
        return tasklist.addTask(t);
    }

    private static String addDeadlineTask(String command, TaskList tasklist) throws DukeException {
        String[] taskAndDeadline = command.substring(9).split(" /by ");
        if (taskAndDeadline.length != 2) {
            throw new DukeException("Invalid deadline format. Usage: deadline <description> /by <yyyy-MM-dd>");
        }
        String taskDescription = taskAndDeadline[0].trim();
        String deadline = taskAndDeadline[1].trim();

        if (taskDescription.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        if (deadline.isEmpty()) {
            throw new DukeException("The deadline (yyyy-MM-dd) is not specified.");
        }

        Task t = new Deadline(taskDescription, deadline);
        return tasklist.addTask(t);
    }

    private static String addEventTask(String command, TaskList tasklist) throws DukeException {
        String[] taskAndDate = command.substring(6).split(" /from | /to ");
        if (taskAndDate.length != 3) {
            throw new DukeException("Invalid event format. Usage: event <description> /from <start> /to <end>");
        }
        String taskDescription = taskAndDate[0].trim();
        String start = taskAndDate[1].trim();
        String end = taskAndDate[2].trim();

        if (taskDescription.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        if (start.isEmpty() || end.isEmpty()) {
            throw new DukeException("The dates (yyyy-MM-dd) of an event cannot be empty.");
        }

        Task t = new Event(taskDescription, start, end);
        return tasklist.addTask(t);
    }

    private static String handleMarkUnmarkDelete(String[] commandSplit, TaskList tasklist) {
        try {
            if (commandSplit.length > 1) {
                int index = Integer.parseInt(commandSplit[1]) - 1;
                if (commandSplit[0].equals("mark")) {
                    return tasklist.markTask(index);
                } else if (commandSplit[0].equals("unmark")) {
                    return tasklist.unMarkTask(index);
                } else if (commandSplit[0].equals("delete")) {
                    return tasklist.deleteTask(index);
                }
            } else {
                return Ui.showError(new DukeException("Please enter a valid number."));
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return Ui.showError(new DukeException("Please enter a valid number."));
        }
        return Ui.showError(new DukeException("I'm sorry, but I don't know what that means :-("));
    }
}
