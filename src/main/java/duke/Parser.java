package duke;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InsertCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.InsufficientArgumentsException;
import duke.exceptions.UnknownCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


/**
 * Represents the Application object responsible for storing and executing commands.
 */
public class Parser {
    public static final String OUTPUT_DATE_TIME_PATTERN = "d MMM yyyy, HHmm";
    public static final String STORAGE_DATE_TIME_PATTERN = "yyyy-MM-dd HHmm";

    /**
     * All supported date time formats for our application.
     */
    private static final List<String> dateTimeFormats = Arrays.asList(
            "d/M/yyyy", "d/M/yyyy HHmm",
            "d/MM/yyyy", "d/MM/yyyy HHmm",
            "dd/M/yyyy", "dd/M/yyyy HHmm",
            "dd/MM/yyyy", "dd/MM/yyyy HHmm",
            "yyyy-M-d", "yyyy-M-d HHmm",
            "yyyy-M-dd", "yyyy-M-dd HHmm",
            "yyyy-MM-d", "yyyy-MM-d HHmm",
            "yyyy-MM-dd", "yyyy-MM-dd HHmm"
    );
    /**
     * All commands stored in a map.
     */
    private final HashMap<String, Command> commandMap = new HashMap<>();

    /**
     * Creates a new {@code Parser} instance.
     */
    public Parser() {
        this.addCommand("delete", new DeleteCommand());
        this.addCommand("list", new ListCommand());

        this.addCommand("mark", new MarkCommand());
        this.addCommand("unmark", new UnmarkCommand());

        this.addCommand("find", new FindCommand());

        this.addCommand(new String[]{Deadline.TASK_TYPE, Event.TASK_TYPE, ToDo.TASK_TYPE},
                new InsertCommand());
        this.addCommand(new String[]{"bye", "exit", "leave", "quit"}, new ExitCommand());
    }

    /**
     * Parses a date time string to get a local date time object.
     *
     * @param dateTimeString The date time string to be parsed.
     * @return The local date time object based on the string.
     */
    public static LocalDateTime parseDate(String dateTimeString) {
        for (String format : dateTimeFormats) {
            LocalDateTime dateTime = Parser.parseDateFormat(dateTimeString, format);
            if (dateTime != null) {
                return dateTime;
            }
        }
        throw new DateTimeParseException(String.format(Messages.ERROR_PREFIX, String.format(
                Messages.INVALID_DATE_TIME_FORMAT, dateTimeString)), dateTimeString, 0);
    }

    /**
     * Parses a date time string to get a local date time object based on a datetime format.
     *
     * @param dateTimeString The date time string to be parsed.
     * @param format         The datetime format.
     * @return The local date time object based on the string if it is compatible with the format.
     */
    private static LocalDateTime parseDateFormat(String dateTimeString, String format) {
        try {
            if (format.contains("HHmm")) {
                return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format));
            }
            // input without specifying time will default to 0000
            LocalDate date = LocalDate.parse(dateTimeString,
                    DateTimeFormatter.ofPattern(format));
            return LocalDateTime.of(date, LocalTime.MIDNIGHT);
        } catch (DateTimeParseException ignored) {
            return null;
        }
    }

    /**
     * Creates a task based on task type and input string.
     *
     * @param taskType The string representing the task type.
     * @param input    The input string used for creating a new task.
     * @return The created task.
     * @throws InsufficientArgumentsException If input is insufficient to create task.
     */
    public static Task createTask(String taskType, String input)
            throws InsufficientArgumentsException, DateTimeParseException {
        if (Objects.equals(input, "")) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "description", taskType));
        }
        switch (taskType) {
        case Deadline.TASK_TYPE:
            return Deadline.parseUserInput(input);
        case Event.TASK_TYPE:
            return Event.parseUserInput(input);
        case ToDo.TASK_TYPE:
            return ToDo.parseUserInput(input);
        default:
            return null;
        }
    }

    /**
     * Parses an input from storage to create a task.
     *
     * @param taskCode The char representing the task type.
     * @param input    The input string used for creating a new task.
     * @return The created task.
     * @throws InsufficientArgumentsException If input is insufficient to create task.
     * @throws DateTimeParseException         If the date in storage is formatted wrongly.
     */
    public static Task parse(String taskCode, String input)
            throws InsufficientArgumentsException, DateTimeParseException {
        String taskType;
        switch (taskCode) {
        case Deadline.TASK_CODE:
            taskType = Deadline.TASK_TYPE;
            break;
        case Event.TASK_CODE:
            taskType = Event.TASK_TYPE;
            break;
        case ToDo.TASK_CODE:
            taskType = ToDo.TASK_TYPE;
            break;
        default:
            taskType = "";
        }
        if (Objects.equals(input, "")) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                    "isDone", taskType));
        }
        if (input.length() < 5) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE, "description", taskType));
        }
        switch (taskCode) {
        case Deadline.TASK_CODE:
            return Deadline.parseStorageInput(input);
        case Event.TASK_CODE:
            return Event.parseStorageInput(input);
        case ToDo.TASK_CODE:
            return ToDo.parseStorageInput(input);
        default:
            return null;
        }
    }

    /**
     * Validates arguments in input string.
     *
     * @param input         The input string used to create task.
     * @param parameterName The parameter name to be checked.
     * @throws InsufficientArgumentsException If input is missing arguments from task.
     */
    public static void validateContainsArgument(String input, String taskType,
                                                String parameterName)
            throws InsufficientArgumentsException {
        if (!input.contains(String.format("/%s", parameterName))) {
            throw new InsufficientArgumentsException(String.format(
                    Messages.INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE,
                    parameterName, taskType));
        }
    }

    /**
     * Adds a command to the application's command map.
     *
     * @param name    The name of the command.
     * @param command The associated command.
     */
    private void addCommand(String name, Command command) {
        this.commandMap.put(name, command);
    }

    /**
     * Adds a command to the application's command map.
     *
     * @param names   The names of the command.
     * @param command The associated command.
     */
    private void addCommand(String[] names, Command command) {
        for (String name : names) {
            this.commandMap.put(name, command);
        }
    }

    /**
     * Executes a command given an input.
     *
     * @param input The input of the user.
     * @return The string output of the command's execution.
     * @throws UnknownCommandException If no known command can be found from the input.
     */
    public String executeCommand(String input, TaskList taskList) throws UnknownCommandException {
        // Separate the command name and the command input
        String[] args = input.split(" ");
        String commandName = args[0];
        Command command = this.commandMap.get(commandName);
        if (command != null) {
            return command.run(input, taskList);
        } else {
            throw new UnknownCommandException(Messages.UNKNOWN_COMMAND_ERROR_MESSAGE);
        }
    }
}
