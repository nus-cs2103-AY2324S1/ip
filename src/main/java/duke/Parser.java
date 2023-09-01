package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The Parser class handles the parsing of user commands and executes corresponding actions.
 */
public class Parser {

    /**
     * Executes the 'list' command to display all tasks.
     *
     * @param tasks The TaskList object containing tasks to list.
     */
    public static void executeList(TaskList tasks) {
        Ui.listTasks(tasks.getAll());
    }

    /**
     * Executes the 'mark' or 'unmark' command to mark or unmark a task as done.
     *
     * @param command         The user command to execute ('mark' or 'unmark').
     * @param tasks           The TaskList object containing tasks.
     * @param separateCommand An array containing command and task number.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static void executeMarkUnmark(String command, TaskList tasks,
                                         String[] separateCommand) throws DukeException {
        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getSize()) {
                throw new DukeException("☹ OOPS!!! Invalid number");
            }
            int taskNumber = Integer.parseInt(separateCommand[1]);
            if (command.startsWith("mark")) {
                tasks.getTask(taskNumber - 1).markAsDone();
                Ui.markDoneMessage();
            } else if (command.startsWith("unmark")) {
                tasks.getTask(taskNumber - 1).markAsUndone();
                Ui.markUndoneMessage();
            }
            Ui.showTaskMessage(tasks.getTask(taskNumber - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Invalid number");
        }
    }

    /**
     * Executes the 'todo', 'deadline', or 'event' command to create a new task.
     *
     * @param command The user command to execute ('todo', 'deadline', or 'event').
     * @param tasks   The TaskList object to add the new task to.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static void executeCreate(String command, TaskList tasks) throws DukeException {
        if (command.startsWith("todo")) {
            try {
                String description = command.substring(5);
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                tasks.addTask(new ToDo(description));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] parts = command.split("/by");
                String description = parts[0].substring(9).trim();
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                try {
                    LocalDate by = LocalDate.parse(parts[1].trim());
                    tasks.addTask(new Deadline(description, by));
                } catch (DateTimeParseException e) {
                    throw new DukeException("☹ OOPS!!! Invalid date format.");
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
        } else if (command.startsWith("event")) {
            try {
                String[] parts = command.split("/from");
                String description = parts[0].substring(6).trim();
                if (description.length() == 0) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                String[] timeParts = parts[1].split("/to");
                String start = timeParts[0].trim();
                String end = timeParts[1].trim();
                tasks.addTask(new Event(description, start, end));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Ui.addTaskMessage(tasks.getTask(tasks.getSize() - 1), tasks.getSize());
    }

    /**
     * Executes the 'delete' command to remove a task.
     *
     * @param command         The user command to execute ('delete').
     * @param tasks           The TaskList object containing tasks.
     * @param separateCommand An array containing command and task number.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static void executeDelete(String command, TaskList tasks, String[] separateCommand) throws DukeException {
        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getAll().size()) {
                throw new DukeException("☹ OOPS!!! Invalid number");
            }
            int taskNumber = Integer.parseInt(separateCommand[1]);
            if (command.startsWith("delete")) {
                Ui.removeTaskMessage();
                Ui.showTaskMessage(tasks.getTask(taskNumber - 1));
                tasks.removeTask(taskNumber - 1);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Invalid number");
        }
    }

    /**
     * Executes the 'find' command to search for tasks containing a specific keyword.
     *
     * @param command The user command to execute ('find').
     * @param tasks   The TaskList object to search for matching tasks.
     */
    public static void executeFind(String command, TaskList tasks) {
        String keyword = command.substring(5).trim();
        List<Task> matchingTasks = tasks.findTasks(keyword);
        Ui.listMatchedTasks(matchingTasks);
    }

    /**
     * Executes the specified command and performs the corresponding action.
     *
     * @param command The user command to be executed.
     * @param tasks   The TaskList object to manage tasks.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static void executeCommand(String command, TaskList tasks) throws DukeException {
        String[] separateCommand = command.split(" ");
        if (command.equals("list")) {
            executeList(tasks);
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            executeMarkUnmark(command, tasks, separateCommand);
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
            executeCreate(command, tasks);
        } else if (command.startsWith("delete")) {
            executeDelete(command, tasks, separateCommand);
        } else if (command.startsWith(("find"))) {
            executeFind(command, tasks);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
