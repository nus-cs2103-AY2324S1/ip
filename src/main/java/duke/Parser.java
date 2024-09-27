package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import javafx.application.Platform;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
        return Ui.getAllTasksMessage(tasks);
    }

    /**
     * Executes the 'mark' or 'unmark' command to mark or unmark a task as done.
     *
     * @param command         The user command to execute ('mark' or 'unmark').
     * @param tasks           The TaskList object containing tasks.
     * @param separateCommand An array containing command and task number.
     * @return A string containing the result of marking or unmarking a task.
     * @throws InvalidNumberException If there is an issue with index of task accessed.
     */
    public static String executeMarkUnmark(String command, TaskList tasks,
                                           String[] separateCommand) throws DukeException {
        assert command != null;
        String result = "";

        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getSize()) {
                throw new InvalidNumberException();
            }

            int taskNumber = Integer.parseInt(separateCommand[1]);
            Task task = tasks.getTask(taskNumber - 1);
            if (command.startsWith("mark") || command.startsWith("m")) {
                task.markAsDone();
                result = Ui.getMarkMessage(task);
            } else if (command.startsWith("unmark") || command.startsWith("um")) {
                task.markAsUndone();
                result = Ui.getUnmarkMessage(task);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidNumberException();
        }

        return result;
    }

    /**
     * Executes the 'create' command to add a task to the TaskList.
     *
     * @param command The user command to execute ('create').
     * @param tasks   The TaskList object containing tasks.
     * @return A string containing the result of creating a task.
     * @throws UnknownCommandMessageException If there is an unknown command.
     */
    public static String executeCreate(String command, TaskList tasks) throws DukeException {
        assert command != null;
        Task task;

        if (command.startsWith("todo") || command.startsWith("t ")) {
            task = createTodo(command);
        } else if (command.startsWith("deadline") || command.startsWith("d ")) {
            task = createDeadline(command);
        } else if (command.startsWith("event") || command.startsWith("e ")) {
            task = createEvent(command);
        } else {
            throw new UnknownCommandMessageException();
        }

        tasks.addTask(task);
        return Ui.getAddTaskMessage(tasks, task);
    }

    /**
     * Creates a new Todo task and adds it to the TaskList.
     *
     * @param command The user command containing the Todo task description.
     * @throws EmptyDescriptionException If there is no description during task creation.
     */
    public static Task createTodo(String command) throws DukeException {
        try {
            String description;

            if (command.startsWith("todo")) {
                description = command.substring(5);
            } else {
                description = command.substring(2);
            }

            if (description.length() == 0) {
                throw new EmptyDescriptionException("todo");
            }

            return new ToDo(description);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("todo");
        }
    }

    /**
     * Creates a new Deadline task and adds it to the TaskList.
     *
     * @param command The user command containing the Deadline task description and deadline date.
     * @throws EmptyDescriptionException If there is no description during task creation.
     * @throws InvalidDateException      If there is an invalid date during task creation.
     */
    public static Task createDeadline(String command) throws DukeException {
        try {
            String[] parts = command.split("/by");
            String description;

            if (command.startsWith("deadline")) {
                description = parts[0].substring(9).trim();
            } else {
                description = parts[0].substring(1).trim();
            }

            if (description.length() == 0) {
                throw new EmptyDescriptionException("deadline");
            }

            try {
                LocalDateTime by = LocalDateTime.parse(parts[1].trim());
                return new Deadline(description, by);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("deadline");
        }
    }

    /**
     * Creates a new Event task and adds it to the TaskList.
     *
     * @param command The user command containing the Event task description, start, and end times.
     * @throws EmptyDescriptionException If there is no description during task creation.
     * @throws InvalidDateException      If there is an invalid date during task creation.
     */
    public static Task createEvent(String command) throws DukeException {
        try {
            String[] parts = command.split("/from");
            String description;

            if (command.startsWith("event")) {
                description = parts[0].substring(6).trim();
            } else {
                description = parts[0].substring(2).trim();
            }

            if (description.length() == 0) {
                throw new EmptyDescriptionException("event");
            }

            String[] timeParts = parts[1].split("/to");

            try {
                LocalDateTime start = LocalDateTime.parse(timeParts[0].trim());
                LocalDateTime end = LocalDateTime.parse(timeParts[1].trim());
                return new Event(description, start, end);
            } catch (DateTimeParseException e) {
                throw new InvalidDateException();
            }
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("event");
        }
    }

    /**
     * Executes the 'delete' command to remove a task.
     *
     * @param command The user command to execute ('delete').
     * @param tasks   The TaskList object containing tasks.
     * @return A string containing the result of deleting a task.
     * @throws InvalidNumberException If there is an issue with index of task accessed.
     */
    public static String executeDelete(String command, TaskList tasks, String[] separateCommand) throws DukeException {
        assert command != null;

        try {
            if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > tasks.getAll().size()) {
                throw new InvalidNumberException();
            }

            int taskNumber = Integer.parseInt(separateCommand[1]);
            Task oldTask = tasks.getTask(taskNumber - 1);
            tasks.removeTask(taskNumber - 1);

            return Ui.getDeleteMessage(oldTask);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidNumberException();
        }
    }

    /**
     * Executes the 'find' command to search for tasks containing a specific keyword.
     *
     * @param command The user command to execute ('find').
     * @param tasks   The TaskList object to search for matching tasks.
     * @return A string containing the matching tasks.
     * @throws UnknownCommandMessageException If there is an unknown command.
     * @throws NoSearchParameterException     If there is no search parameter during find operation.
     */
    public static String executeFind(String command, TaskList tasks) throws DukeException {
        assert command != null;

        try {
            String keyword;

            if (command.startsWith("find")) {
                keyword = command.substring(5).trim();
            } else if (command.startsWith("f ")) {
                keyword = command.substring(2).trim();
            } else {
                throw new UnknownCommandMessageException();
            }

            if (keyword.equals("")) {
                throw new NoSearchParameterException();
            }

            TaskList matchingTasks = new TaskList(tasks.findTasks(keyword));

            return Ui.getFindMessage(matchingTasks);
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            throw new NoSearchParameterException();
        }
    }

    /**
     * Checks if the input command is related to marking or unmarking a task as done.
     *
     * @param command The user command to check.
     * @return True if the command is a mark or unmark command, false otherwise.
     */
    private static boolean isMarkUnmarkCommand(String command) {
        return command.startsWith("mark") || command.startsWith("unmark")
                || command.startsWith("m") || command.startsWith("um");
    }

    /**
     * Checks if the input command is related to creating a new task (e.g., todo, deadline, event).
     *
     * @param command The user command to check.
     * @return True if the command is a create task command, false otherwise.
     */
    private static boolean isCreateCommand(String command) {
        return command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")
                || command.startsWith("t") || command.startsWith("d") || command.startsWith("e");
    }

    /**
     * Checks if the input command is related to deleting a task.
     *
     * @param command The user command to check.
     * @return True if the command is a delete command, false otherwise.
     */
    private static boolean isDeleteCommand(String command) {
        return command.startsWith("delete") || command.startsWith("del");
    }

    /**
     * Checks if the input command is related to searching for tasks.
     *
     * @param command The user command to check.
     * @return True if the command is a find command, false otherwise.
     */
    private static boolean isFindCommand(String command) {
        return command.startsWith("find") || command.startsWith("f");
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
        String result = "";
        try {
            String[] separateCommand = command.split(" ");
            if (command.equals("list") || command.equals("ls")) {
                result = executeList(tasks);
            } else if (isMarkUnmarkCommand(command)) {
                result = executeMarkUnmark(command, tasks, separateCommand);
            } else if (isDeleteCommand(command)) {
                result = executeDelete(command, tasks, separateCommand);
            } else if (isCreateCommand(command)) {
                result = executeCreate(command, tasks);
            } else if (isFindCommand(command)) {
                result = executeFind(command, tasks);
            } else if (command.equals("goodbye")) {
                Ui.goodbye();
                Platform.exit();
            } else {
                result = Ui.getUnknownCommandMessage();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return result;
    }
}
