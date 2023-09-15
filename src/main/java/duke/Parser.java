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
        MARK, UNMARK, LIST, EXIT, TODO, DEADLINE, EVENT, DELETE, FIND, LOCATION
    }

    public Parser() {
    }

    // takes in a line of text, split into different parts and covert them to a task

    /**
     * Converts a line of text into different useful parts and convert into a task.
     *
     * @param text The line of user input.
     * @return The task made from user input.
     */
    public static Command parse(String text) throws DukeException {
        //ArrayList<String> result = new ArrayList<>();
        String[] parts = text.split(" ", 2);
        String description = null;
        if (parts.length >= 2) {
            description = parts[1];
        }
        Cmd action = COMMAND_MAP.get(parts[0]);
        switch (action) {
        case EXIT:
            return new ExitCommand();
        case LIST:
            return new ListCommand(description);
        case FIND:
            return new FindCommand(description);
        // tasks related commands below
        case MARK:
            int taskNumber;
            try {
                taskNumber = Integer.parseInt(description) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number. Please enter a valid number to mark");
            }
            return new MarkCommand(taskNumber);
        case UNMARK:
            int taskNumberToUnmark;
            try {
                taskNumberToUnmark = Integer.parseInt(description) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(
                        "Invalid task number. Please enter a valid number to unmark");
            }
            return new UnmarkCommand(taskNumberToUnmark);
        case DELETE:
            int taskNumberToDelete;
            try {
                taskNumberToDelete = Integer.parseInt(description) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException(
                        "Invalid task number. Please enter a valid number to delete");
            }
            return new DeleteCommand(taskNumberToDelete);
        case TODO:
            String[] todoParts = description.split(" /at ");
            String todoLocation = "";
            if (todoParts.length > 1) {
                description = todoParts[0];
                todoLocation = todoParts[1];
            }
            return new TodoCommand(description, todoLocation);
        case EVENT:
            try {
                parts = description.split(" /from ", 2);
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
        case DEADLINE:
            try {
                parts = description.split(" /by ", 2);
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
        case LOCATION:
            // finds all tasks at certain location given by description
            return new LocationCommand(description);
        default:
            throw new DukeException("Command given is invalid! You must start with a "
                    + "todo/event/deadline to add tasks, "
                    + "or list/mark/unmark/bye for other purposes");
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
}
