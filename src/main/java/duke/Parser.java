package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.LocationCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Object that deals with making sense of user command.
 */
public class Parser {
    private static final HashMap<String, Cmd> COMMAND_MAP = new HashMap<>(
            Map.of("todo", Cmd.TODO, "event", Cmd.EVENT, "mark", Cmd.MARK, "unmark",
                    Cmd.UNMARK, "list", Cmd.LIST, "bye", Cmd.EXIT, "deadline",
                    Cmd.DEADLINE, "delete", Cmd.DELETE, "find", Cmd.FIND, "location", Cmd.LOCATION)
    );

    private enum Cmd {
        MARK, UNMARK, LIST, EXIT, TODO, DEADLINE, EVENT, DELETE, FIND, LOCATION, INVALID
    }

    public Parser() {
    }

    /**
     * Converts a line of text into different useful parts and convert into a task.
     *
     * @param text The line of user input.
     * @return The task made from user input.
     */
    public static Command parse(String text) throws DukeException {
        String[] parts = text.split(" ", 2);
        String description = parts.length >= 2 ? parts[1] : null;
        Cmd action = COMMAND_MAP.getOrDefault(parts[0], Cmd.INVALID);
        switch (action) {
        case EXIT:
            return new ExitCommand();
        case LIST:
            return new ListCommand(description);
        case FIND:
            return new FindCommand(description);
        // tasks related commands below
        case MARK:
            return handleMark(description);
        case UNMARK:
            return handleUnmark(description);
        case DELETE:
            return handleDelete(description);
        case TODO:
            return handleTodo(description);
        case EVENT:
            return handleEvent(description);
        case DEADLINE:
            return handleDeadline(description);
        case LOCATION:
            return handleLocation(description);
        default:
            handleDefault();
            return null;
        }
    }

    /**
     * Converts the segmented strings form the storage/file into Tasks.
     *
     * @param parts The segmented strings containing task information.
     * @return The task described by the information in parts.
     * @throws DukeException If the parts format is wrong and there is an error parsing them into Tasks.
     */
    public static Task parseTask(String[] parts) throws DukeException {
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        Task task;
        try {
            if (taskType.equals("Event")) {
                task = new Event(parts[2], parts[3], parts[4], parts[5]);
            } else if (taskType.equals("Todo")) {
                task = new Todo(parts[2], parts[3]);
            } else if (taskType.equals("Deadline")) {
                task = new Deadline(parts[2], parts[3], parts[4]);
            } else {
                throw new DukeException("Wrong storing format in storage");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Wrong storing format in storage");
        }
        if (isDone) {
            task.doTask();
        }
        assert task != null : "task should not be empty after parsing";
        return task;
    }

    // below are some private methods abstracted out from the main parsing method
    private static MarkCommand handleMark(String description) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number. Please enter a valid number to mark");
        }
        return new MarkCommand(taskNumber);
    }

    private static UnmarkCommand handleUnmark(String description) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(
                    "Invalid task number. Please enter a valid number to unmark");
        }
        return new UnmarkCommand(taskNumber);
    }

    private static DeleteCommand handleDelete(String description) throws DukeException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException(
                    "Invalid task number. Please enter a valid number to delete");
        }
        return new DeleteCommand(taskNumber);
    }

    private static TodoCommand handleTodo(String description) throws DukeException {
        String[] todoParts = description.split(" /at ");
        String todoLocation = "";
        if (todoParts.length > 1) {
            description = todoParts[0];
            todoLocation = todoParts[1];
        }
        return new TodoCommand(description, todoLocation);
    }

    private static EventCommand handleEvent(String description) throws DukeException {
        try {
            String[] parts = description.split(" /from ", 2);
            String eventLocation = "";
            description = parts[0];
            String eventDates = parts[1];
            parts = eventDates.split(" /to ", 2);
            String fromTime = parts[0];
            String toTime = parts[1];
            parts = toTime.split(" /at ", 2);
            if (parts.length > 1) {
                toTime = parts[0];
                eventLocation = parts[1];
            }
            LocalDate parsedFromTime = LocalDate.parse(fromTime);
            LocalDate parsedToTime = LocalDate.parse(toTime);
            return new EventCommand(description, eventLocation, parsedFromTime, parsedToTime);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "Invalid format for adding an event! Please enter in this format:\n"
                            + "event [description] /from [date] /to [date]");
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format! Please use yyyy-mm-dd format");
        }
    }

    private static DeadlineCommand handleDeadline(String description) throws DukeException {
        try {
            String[] parts = description.split(" /by ", 2);
            String deadlineLocation = "";
            description = parts[0];
            String deadline = parts[1];
            parts = deadline.split(" /at ", 2);
            if (parts.length > 1) {
                deadline = parts[0];
                deadlineLocation = parts[1];
            }
            LocalDate parsedDeadline = LocalDate.parse(deadline);
            return new DeadlineCommand(description, deadlineLocation, parsedDeadline);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "Invalid format for adding a deadline! Please enter in this format:\n"
                            + "deadline [description] /by [date]");
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format! Please use yyyy-mm-dd format");
        }
    }

    private static LocationCommand handleLocation(String description) throws DukeException {
        // finds all tasks at certain location given by description
        return new LocationCommand(description);
    }

    private static void handleDefault() throws DukeException {
        throw new DukeException("Command given is invalid! You must start with a "
                + "todo/event/deadline to add tasks, "
                + "or list/mark/unmark/bye for other purposes");
    }
}
