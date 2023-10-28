package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.RescheduleCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidDescriptionException;
import duke.exceptions.InvalidKeywordException;
import duke.exceptions.InvalidRescheduleException;
import duke.exceptions.InvalidTaskIndexException;
import duke.exceptions.MissingTaskIndexException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * The duke.parser.Parser class is responsible for parsing user input and
 * converting it into meaningful commands and tasks.
 */
public class Parser {

    /**
     * Parses an input from user to get duke.commands.Command.
     *
     * @param str The user input string.
     * @return The parsed command.
     */
    public static Command getCommand(String str, Storage storage, TaskList taskList, Ui ui) {

        String commandWord = str.split(" ")[0];
        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(str);
        case "unmark":
            return new UnmarkCommand(str);
        case "todo":
            return new ToDoCommand(str);
        case "deadline":
            return new DeadlineCommand(str);
        case "event":
            return new EventCommand(str);
        case "delete":
            return new DeleteCommand(str);
        case "find":
            return new FindCommand(str);
        case "bye":
            return new ByeCommand();
        case "reschedule":
            return new RescheduleCommand(str);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Extracts the task index from a user input string and marks the task as done or undone.
     *
     * @param str    The user input string.
     * @param tasks  The list of tasks to operate on.
     * @return The index of the task that was marked.
     * @throws InvalidTaskIndexException   If the task index is invalid.
     * @throws MissingTaskIndexException  If the task index is missing.
     */
    public static int taskToMark(String str, TaskList tasks)
            throws InvalidTaskIndexException, MissingTaskIndexException {
        int parts = str.split(" ").length;
        if (parts != 2) {
            throw new MissingTaskIndexException("Task Index Missing.");
        }
        assert parts == 2 : "parts should be 2";
        int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
        if (taskIndex + 1 > tasks.getSize() || taskIndex < 0) {
            throw new InvalidTaskIndexException("Invalid Task Index.");
        }
        tasks.markTaskAsDone(taskIndex);
        return taskIndex;
    }

    /**
     * Parses a user input string to get the index of a task to unmark.
     *
     * @param str    The user input string.
     * @param tasks  The list of tasks.
     * @return The index of the task that was marked as undone.
     * @throws InvalidTaskIndexException   If the task index is invalid.
     * @throws MissingTaskIndexException  If the task index is missing.
     */
    public static int taskToUnmark(String str, TaskList tasks)
            throws InvalidTaskIndexException, MissingTaskIndexException {
        int parts = str.split(" ").length;
        if (parts != 2) {
            throw new MissingTaskIndexException("Task Index Missing.");

        }
        assert parts == 2 : "parts should be 2";
        int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
        if (taskIndex + 1 > tasks.getSize() || taskIndex < 0) {
            throw new InvalidTaskIndexException("Invalid Task Index.");
        }
        tasks.markTaskAsUndone(taskIndex);
        return taskIndex;
    }

    /**
     * Extracts the keyword from user input and finds tasks with the specified keyword.
     *
     * @param str The user input string.
     * @param tasks The list of tasks to operate on.
     * @return A TaskList of tasks containing the keyword.
     * @throws InvalidKeywordException If the keyword is missing, or if there is more than 1 keyword.
     */
    public static TaskList findKeyword(String str, TaskList tasks) throws InvalidKeywordException {
        int parts = str.split(" ").length;
        if (parts != 2) {
            throw new InvalidKeywordException("Keyword given is not a single word.");
        }
        assert parts == 2 : "parts should be 2";
        String keyword = str.split(" ")[1];
        return tasks.findTask(keyword);
    }

    /**
     * Extracts the task index from a user input string and deletes the task.
     *
     * @param str    The user input string.
     * @param tasks  The list of tasks to operate on.
     * @return The task that was deleted.
     * @throws InvalidTaskIndexException   If the task index is invalid.
     * @throws MissingTaskIndexException  If the task index is missing.
     */
    public static Task taskToDelete(String str, TaskList tasks)
            throws InvalidTaskIndexException, MissingTaskIndexException {
        int parts = str.split(" ").length;
        if (parts != 2) {
            throw new MissingTaskIndexException("Task Index Missing.");
        }
        assert parts == 2 : "parts should be 2";
        int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
        if (taskIndex + 1 > tasks.getSize() || taskIndex < 0) {
            throw new InvalidTaskIndexException("Invalid Task Index.");
        }
        Task toRemove = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        return toRemove;
    }

    /**
     * Converts a user input string to a task index for rescheduling.
     *
     * @param str The user input string containing the task index.
     * @param tasks The task list containing the tasks.
     * @return The task index (0-based) that corresponds to the user input.
     * @throws InvalidTaskIndexException If the task index is not within the valid range.
     * @throws MissingTaskIndexException If the task index is missing in the user input.
     */
    public static int taskIndexToReschedule(String str, TaskList tasks)
            throws InvalidTaskIndexException, MissingTaskIndexException {
        int parts = str.split(" ").length;
        if (parts < 2) {
            throw new MissingTaskIndexException("Task Index Missing.");
        }
        assert parts == 2 : "parts should be 2";
        int taskIndex = Integer.parseInt(str.split(" ")[1]) - 1;
        if (taskIndex + 1 > tasks.getSize() || taskIndex < 0) {
            throw new InvalidTaskIndexException("Invalid Task Index.");
        }
        return taskIndex;
    }

    private static String getByDatetime(String userInput) throws InvalidRescheduleException {
        int parts = userInput.split(" ").length;
        if (parts != 4) {
            throw new InvalidRescheduleException("Invalid number of inputs");
        }
        String datetime = userInput.split(" ", 3)[2];
        return datetime;
    }

    /**
     * Extracts the new 'by' date from user input for rescheduling.
     *
     * @param userInput The user input string.
     * @return The LocalDate representing the new 'by' date.
     * @throws InvalidRescheduleException If the input format is invalid.
     * @throws InvalidDateTimeException If the date and time cannot be parsed.
     */
    public static LocalDate getNewByDate(String userInput)
            throws InvalidRescheduleException, InvalidDateTimeException {
        try {
            String datetime = getByDatetime(userInput);
            LocalDate newByDate = LocalDate.parse(datetime.split(" ")[0]);
            return newByDate;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid Datetime.");
        }
    }

    /**
     * Extracts the new 'by' time from user input for rescheduling.
     *
     * @param userInput The user input string.
     * @return The LocalDate representing the new 'by' time.
     * @throws InvalidRescheduleException If the input format is invalid.
     * @throws InvalidDateTimeException If the date and time cannot be parsed.
     */
    public static LocalTime getNewByTime(String userInput)
            throws InvalidRescheduleException, InvalidDateTimeException {
        try {
            String datetime = getByDatetime(userInput);
            LocalTime newByTime = LocalTime.parse(datetime.split(" ")[1]);
            return newByTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid Datetime.");
        }
    }

    private static String getFromDatetime(String userInput) throws InvalidRescheduleException {
        int parts = userInput.split(" ").length;
        if (parts != 6) {
            throw new InvalidRescheduleException("Invalid number of inputs");
        }
        String datetime = userInput.split(" ", 3)[2];
        return datetime;
    }

    /**
     * Extracts the new 'from' date from user input for rescheduling.
     *
     * @param userInput The user input string.
     * @return The LocalDate representing the new 'from' date.
     * @throws InvalidRescheduleException If the input format is invalid.
     * @throws InvalidDateTimeException If the date and time cannot be parsed.
     */
    public static LocalDate getNewFromDate(String userInput)
            throws InvalidRescheduleException, InvalidDateTimeException {
        try {
            String datetime = getFromDatetime(userInput);
            LocalDate newFromDate = LocalDate.parse(datetime.split(" ")[0]);
            return newFromDate;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid Datetime");
        }
    }

    /**
     * Extracts the new 'from' time from user input for rescheduling.
     *
     * @param userInput The user input string.
     * @return The LocalDate representing the new 'from' time.
     * @throws InvalidRescheduleException If the input format is invalid.
     * @throws InvalidDateTimeException If the date and time cannot be parsed.
     */
    public static LocalTime getNewFromTime(String userInput)
            throws InvalidRescheduleException, InvalidDateTimeException {
        try {
            String datetime = getFromDatetime(userInput);
            LocalTime newFromTime = LocalTime.parse(datetime.split(" ")[1]);
            return newFromTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid Datetime");
        }
    }

    /**
     * Extracts the new 'to' date from user input for rescheduling.
     *
     * @param userInput The user input string.
     * @return The LocalDate representing the new 'to' date.
     * @throws InvalidRescheduleException If the input format is invalid.
     * @throws InvalidDateTimeException If the date and time cannot be parsed.
     */
    public static LocalDate getNewToDate(String userInput)
            throws InvalidRescheduleException, InvalidDateTimeException {
        try {
            String datetime = getFromDatetime(userInput);
            LocalDate newToDate = LocalDate.parse(datetime.split(" ")[2]);
            return newToDate;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid Datetime");
        }
    }

    /**
     * Extracts the new 'to' time from user input for rescheduling.
     *
     * @param userInput The user input string.
     * @return The LocalDate representing the new 'to' time.
     * @throws InvalidRescheduleException If the input format is invalid.
     * @throws InvalidDateTimeException If the date and time cannot be parsed.
     */
    public static LocalTime getNewToTime(String userInput)
            throws InvalidRescheduleException, InvalidDateTimeException {
        try {
            String datetime = getFromDatetime(userInput);
            LocalTime newToTime = LocalTime.parse(datetime.split(" ")[3]);
            return newToTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid Datetime");
        }
    }







    /**
     * Creates a ToDo task based on data from storage.
     *
     * @param storageText The stored text data.
     * @return A ToDo task from storage.
     */
    public static Task parseStorageToToDo(String storageText) {
        boolean isMarked = storageText.charAt(4) == 'X';
        String description = storageText.split("] ")[1];
        ToDo taskT = new ToDo(description);
        if (isMarked) {
            taskT.markAsDone();
        }
        return taskT;
    }

    /**
     * Creates a Deadline task based on data from storage.
     *
     * @param storageText The stored text data.
     * @return A Deadline task from storage.
     */
    public static Task parseStorageToDeadline(String storageText) {
        boolean isMarked = storageText.charAt(4) == 'X';
        String description = storageText.split("] ")[1];
        description = description.split(" \\(by: ")[0];
        String by = storageText.split(" \\(by: ")[1];
        LocalDate date = LocalDate.parse(by.split(" ")[0]);
        LocalTime time = LocalTime.parse(by.split(" ")[1].replaceAll(".$", ""));
        Deadline taskD = new Deadline(description, date, time);
        if (isMarked) {
            taskD.markAsDone();
        }
        return taskD;
    }

    /**
     * Creates an Event task based on data from storage.
     *
     * @param storageText The stored text data.
     * @return An Event task from storage.
     */
    public static Task parseStorageToEvent(String storageText) {
        boolean isMarked = storageText.charAt(4) == 'X';
        String description = storageText.split("] ")[1];
        description = description.split(" \\(from: ")[0];
        String from = String.join("", storageText.split("\\(from: ")[1]).split(" to: ")[0];
        String to = storageText.split(" to: ")[1];
        LocalDate fromDate = LocalDate.parse(from.split(" ")[0]);
        LocalTime fromTime = LocalTime.parse(from.split(" ")[1]);
        LocalDate toDate = LocalDate.parse(to.split(" ")[0]);
        LocalTime toTime = LocalTime.parse(to.split(" ")[1].replaceAll(".$", ""));
        Event taskE = new Event(description, fromDate, fromTime, toDate, toTime);
        if (isMarked) {
            taskE.markAsDone();
        }
        return taskE;
    }

    /**
     * Creates a ToDo task based on data from user input.
     *
     * @param userInput The user input string.
     * @return A ToDo task from user input.
     */
    public static Task parseInputToToDo(String userInput) {
        ToDo todo = new ToDo(userInput.split(" ")[1]);
        return todo;
    }

    /**
     * Creates a Deadline task based on data from user input.
     *
     * @param userInput The user input string.
     * @return A Deadline task from user input.
     */
    public static Task parseInputToDeadline(String userInput) throws InvalidDateTimeException {
        String fullTaskDescription = userInput.split(" ", 2)[1];
        String description = fullTaskDescription.split(" /by ")[0];
        String by = fullTaskDescription.split(" /by ")[1];
        String[] datetime = by.split(" ");
        try {
            LocalDate date = LocalDate.parse(datetime[0]);
            LocalTime time = LocalTime.parse(datetime[1]);
            Deadline deadline = new Deadline(description, date, time);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid Datetime.");
        }
    }

    /**
     * Creates an Event task based on data from user input.
     *
     * @param userInput The user input string.
     * @return An Event task from user input.
     */
    public static Task parseInputToEvent(String userInput) throws InvalidDateTimeException {
        String fullTaskDescription = userInput.split(" ", 2)[1];
        String description = fullTaskDescription.split(" /from ")[0];
        String from = String.join("", fullTaskDescription.split(" /from ")[1]).split(" /to ")[0];
        String to = fullTaskDescription.split(" /to ")[1];
        try {
            String[] fromDatetime = from.split(" ");
            String[] toDatetime = to.split(" ");
            LocalDate fromDate = LocalDate.parse(fromDatetime[0]);
            LocalTime fromTime = LocalTime.parse(fromDatetime[1]);
            LocalDate toDate = LocalDate.parse(toDatetime[0]);
            LocalTime toTime = LocalTime.parse(toDatetime[1]);
            Event event = new Event(description, fromDate, fromTime, toDate, toTime);
            return event;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Invalid Datetime");
        }
    }

    /**
     * Parses a user input string into a task.
     *
     * @param userInput The user input string.
     * @return The parsed task.
     * @throws InvalidDescriptionException If the task description is invalid.
     * @throws InvalidDateTimeException    If the task date and time are invalid.
     */
    public static Task parseInputToTask(String userInput, String commandWord)
            throws InvalidDescriptionException, InvalidDateTimeException {
        switch(commandWord) {
        case "todo":
            if (userInput.split(" ").length <= 1) {
                throw new InvalidDescriptionException("Invalid description.");
            }
            return parseInputToToDo(userInput);
        case "deadline":
            if (userInput.split(" ").length <= 3) {
                throw new InvalidDescriptionException("Invalid description.");
            }
            return parseInputToDeadline(userInput);
        case "event":
            if (userInput.split(" ").length <= 4) {
                throw new InvalidDescriptionException("Invalid description.");
            }
            return parseInputToEvent(userInput);
        default:
            return null;
        }
    }
}
