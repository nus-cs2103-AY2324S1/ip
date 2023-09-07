package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
        String result = "";
        result += " Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getAll().size(); i++) {
            result += " " + (i + 1) + "." + tasks.getAll().get(i).toString() + "\n";
        }
        Ui.showMessage(result);
        return result;
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
        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getSize()) {
                throw new DukeException(" OOPS!!! Invalid number");
            }
            String result = "";
            int taskNumber = Integer.parseInt(separateCommand[1]);
            Task task = tasks.getTask(taskNumber - 1);
            if (command.startsWith("mark")) {
                tasks.getTask(taskNumber - 1).markAsDone();
                result += " Nice! I've marked this task as done:\n";
            } else if (command.startsWith("unmark")) {
                tasks.getTask(taskNumber - 1).markAsUndone();
                result += " OK, I've marked this task as not done yet:\n";
            }
            result += "   " + task.toString() + "\n";
            Ui.showMessage(result);
            return result;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(" OOPS!!! Invalid number");
        }
    }

    /**
     * Executes the 'delete' command to remove a task.
     *
     * @param command         The user command to execute ('delete').
     * @param tasks           The TaskList object containing tasks.
     * @return A string containing the result of deleting a task.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String executeCreate(String command, TaskList tasks) throws DukeException {
        String result = "";
        if (command.startsWith("todo")) {
            try {
                String description = command.substring(5);
                if (description.length() == 0) {
                    throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
                }
                tasks.addTask(new ToDo(description));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException(" OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (command.startsWith("deadline")) {
            try {
                String[] parts = command.split("/by");
                String description = parts[0].substring(9).trim();
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
        } else if (command.startsWith("event")) {
            try {
                String[] parts = command.split("/from");
                String description = parts[0].substring(6).trim();
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
        } else {
            throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        Task task = tasks.getTask(tasks.getSize() - 1);
        result += " Got it. I've added this task:" + "\n" + "   "
                + task.toString() + "\n" + " Now you have "
                + tasks.getSize() + " tasks in the list.";
        Ui.showMessage(result);
        return result;
    }

    /**
     * Executes the specified command and performs the corresponding action.
     *
     * @param command The user command to be executed.
     * @param tasks   The TaskList object to manage tasks.
     * @return A string containing the result of executing the command.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String executeDelete(String command, TaskList tasks, String[] separateCommand) throws DukeException {
        String result = "";
        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getAll().size()) {
                throw new DukeException(" OOPS!!! Invalid number");
            }
            int taskNumber = Integer.parseInt(separateCommand[1]);
            Task task = tasks.getTask(taskNumber - 1);
            if (command.startsWith("delete")) {
                result += " Noted. I've removed this task:\n";
                result += "   " + task.toString() + "\n";
                tasks.removeTask(taskNumber - 1);
            }
            Ui.showMessage(result);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(" OOPS!!! Invalid number");
        }
        return result;
    }

    /**
     * Executes the 'find' command to search for tasks containing a specific keyword.
     *
     * @param command The user command to execute ('find').
     * @param tasks   The TaskList object to search for matching tasks.
     */
    public static String executeFind(String command, TaskList tasks) {
        String result = "";
        String keyword = command.substring(5).trim();
        List<Task> matchingTasks = tasks.findTasks(keyword);
        result += " Here are the matching tasks in your list:\n";
        for (int i = 0; i < matchingTasks.size(); i++) {
            result += " " + (i + 1) + "." + matchingTasks.get(i).toString() + "\n";
        }
        Ui.showMessage(result);
        return result;
    }

    /**
     * Executes the specified command and performs the corresponding action.
     *
     * @param command The user command to be executed.
     * @param tasks   The TaskList object to manage tasks.
     * @throws DukeException If there is an issue or error encountered during command execution.
     */
    public static String executeCommand(String command, TaskList tasks) {
        String result = "";
        try {
            String[] separateCommand = command.split(" ");
            if (command.equals("list")) {
                result += Parser.executeList(tasks);
            } else if (command.startsWith("mark") || command.startsWith("unmark")) {
                result += Parser.executeMarkUnmark(command, tasks, separateCommand);
            } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")) {
                result += Parser.executeCreate(command, tasks);
            } else if (command.startsWith("delete")) {
                result += Parser.executeDelete(command, tasks, separateCommand);
            } else if (command.startsWith(("find"))) {
                result += Parser.executeFind(command, tasks);
            } else {
                result += " OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        } catch (DukeException e) {
            result += e.getMessage();
            return result;
        }
        return result;
    }
}
