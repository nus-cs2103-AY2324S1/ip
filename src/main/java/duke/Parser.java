package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
     * @return A string containing the list of tasks.
     */
    public static String executeList(TaskList tasks) {
        StringBuilder result = new StringBuilder(" Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getAll().size(); i++) {
            result.append(" ").append(i + 1).append(".").append(tasks.getAll().get(i).toString()).append("\n");
        }
        Ui.showMessage(result.toString());
        return result.toString();
    }

    /**
     * Executes the 'mark' or 'unmark' command to mark or unmark a task as done.
     *
     * @param command         The user command to execute ('mark' or 'unmark').
     * @param tasks           The TaskList object containing tasks.
     * @param separateCommand An array containing command and task number.
     * @return A string containing the result of marking or unmarking a task.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String executeMarkUnmark(String command, TaskList tasks,
                                           String[] separateCommand) throws DukeException {
        assert command != null;
        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getSize()) {
                throw new DukeException(" OOPS!!! Invalid number");
            }
            StringBuilder result = new StringBuilder();
            int taskNumber = Integer.parseInt(separateCommand[1]);
            Task task = tasks.getTask(taskNumber - 1);
            if (command.startsWith("mark") || command.startsWith("m")) {
                tasks.getTask(taskNumber - 1).markAsDone();
                result.append(" Nice! I've marked this task as done:\n");
            } else if (command.startsWith("unmark") || command.startsWith("um")) {
                tasks.getTask(taskNumber - 1).markAsUndone();
                result.append(" OK, I've marked this task as not done yet:\n");
            }
            result.append("   ").append(task.toString()).append("\n");
            Ui.showMessage(result.toString());
            return result.toString();
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(" OOPS!!! Invalid number");
        }
    }

    /**
     * Executes the 'create' command to add a task to the TaskList.
     *
     * @param command The user command to execute ('create').
     * @param tasks   The TaskList object containing tasks.
     * @return A string containing the result of creating a task.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String executeCreate(String command, TaskList tasks) throws DukeException {
        assert command != null;
        StringBuilder result = new StringBuilder();
        if (command.startsWith("todo") || command.startsWith("t")) {
            createTodo(command, tasks);
        } else if (command.startsWith("deadline") || command.startsWith("d")) {
            createDeadline(command, tasks);
        } else if (command.startsWith("event") || command.startsWith("e")) {
            createEvent(command, tasks);
        } else {
            throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Task task = tasks.getTask(tasks.getSize() - 1);
        result.append(" Got it. I've added this task:\n")
                .append("   ").append(task.toString()).append("\n")
                .append(" Now you have ").append(tasks.getSize()).append(" tasks in the list.");
        Ui.showMessage(result.toString());
        return result.toString();
    }

    /**
     * Creates a new Todo task and adds it to the TaskList.
     *
     * @param command The user command containing the Todo task description.
     * @param tasks   The TaskList object to add the task to.
     * @throws DukeException If there is an issue or error encountered during task creation.
     */
    public static void createTodo(String command, TaskList tasks) throws DukeException {
        try {
            String description;
            if (command.startsWith("todo")) {
                description = command.substring(5);
            } else {
                description = command.substring(2);
            }
            if (description.length() == 0) {
                throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.addTask(new ToDo(description));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Creates a new Deadline task and adds it to the TaskList.
     *
     * @param command The user command containing the Deadline task description and deadline date.
     * @param tasks   The TaskList object to add the task to.
     * @throws DukeException If there is an issue or error encountered during task creation.
     */
    public static void createDeadline(String command, TaskList tasks) throws DukeException {
        try {
            String[] parts = command.split("/by");
            String description;
            if (command.startsWith("deadline")) {
                description = parts[0].substring(9).trim();
            } else {
                description = parts[0].substring(2).trim();
            }
            if (description.length() == 0) {
                throw new DukeException(" OOPS!!! The description of a deadline cannot be empty.");
            }
            try {
                LocalDate by = LocalDate.parse(parts[1].trim());
                tasks.addTask(new Deadline(description, by));
            } catch (DateTimeParseException e) {
                throw new DukeException(" OOPS!!! Invalid date format.");
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Creates a new Event task and adds it to the TaskList.
     *
     * @param command The user command containing the Event task description, start, and end times.
     * @param tasks   The TaskList object to add the task to.
     * @throws DukeException If there is an issue or error encountered during task creation.
     */
    public static void createEvent(String command, TaskList tasks) throws DukeException {
        try {
            String[] parts = command.split("/from");
            String description;
            if (command.startsWith("event")) {
                description = parts[0].substring(6).trim();
            } else {
                description = parts[0].substring(2).trim();
            }
            if (description.length() == 0) {
                throw new DukeException(" OOPS!!! The description of an event cannot be empty.");
            }
            String[] timeParts = parts[1].split("/to");
            String start = timeParts[0].trim();
            String end = timeParts[1].trim();
            tasks.addTask(new Event(description, start, end));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(" OOPS!!! The description of an event cannot be empty.");
        }
    }

    /**
     * Executes the 'delete' command to remove a task.
     *
     * @param command The user command to execute ('delete').
     * @param tasks   The TaskList object containing tasks.
     * @return A string containing the result of deleting a task.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String executeDelete(String command, TaskList tasks, String[] separateCommand) throws DukeException {
        assert command != null;
        StringBuilder result = new StringBuilder();
        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getAll().size()) {
                throw new DukeException(" OOPS!!! Invalid number");
            }
            int taskNumber = Integer.parseInt(separateCommand[1]);
            Task task = tasks.getTask(taskNumber - 1);
            if (command.startsWith("delete") || command.startsWith("del")) {
                result.append(" Noted. I've removed this task:\n")
                        .append("   ").append(task.toString()).append("\n");
                tasks.removeTask(taskNumber - 1);
            }
            Ui.showMessage(result.toString());
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(" OOPS!!! Invalid number");
        }
        return result.toString();
    }

    /**
     * Executes the 'find' command to search for tasks containing a specific keyword.
     *
     * @param command The user command to execute ('find').
     * @param tasks   The TaskList object to search for matching tasks.
     * @return A string containing the matching tasks.
     */
    public static String executeFind(String command, TaskList tasks) {
        assert command != null;
        StringBuilder result = new StringBuilder();
        String keyword;
        if (command.startsWith("find")) {
            keyword = command.substring(5).trim();
        } else {
            keyword = command.substring(2).trim();
        }
        List<Task> matchingTasks = tasks.findTasks(keyword);
        result.append(" Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append(" ").append(i + 1).append(".").append(matchingTasks.get(i).toString()).append("\n");
        }
        Ui.showMessage(result.toString());
        return result.toString();
    }

    /**
     * Executes the specified command and performs the corresponding action.
     *
     * @param command The user command to be executed.
     * @param tasks   The TaskList object to manage tasks.
     * @return A string containing the result of executing the command.
     */
    public static String executeCommand(String command, TaskList tasks) {
        assert command != null;
        StringBuilder result = new StringBuilder();
        try {
            String[] separateCommand = command.split(" ");
            if (command.equals("list") || command.equals("ls")) {
                result.append(Parser.executeList(tasks));
            } else if (command.startsWith("mark") || command.startsWith("unmark") || command.startsWith("m")
                    || command.startsWith("um")) {
                result.append(Parser.executeMarkUnmark(command, tasks, separateCommand));
            } else if (command.startsWith("delete") || command.startsWith("del")) {
                result.append(Parser.executeDelete(command, tasks, separateCommand));
            } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")
                    || command.startsWith("t") || command.startsWith("d") || command.startsWith("e")) {
                result.append(Parser.executeCreate(command, tasks));
            } else if (command.startsWith(("find")) || command.startsWith("f")) {
                result.append(Parser.executeFind(command, tasks));
            } else {
                result.append(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            result.append(e.getMessage());
            return result.toString();
        }
        return result.toString();
    }
}
