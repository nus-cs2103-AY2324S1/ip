package duke.components;

import duke.commands.AddTaskCommand;
import duke.commands.Command;
import duke.commands.DeleteTaskCommand;
import duke.commands.ExitCommand;
import duke.commands.ListByDateCommand;
import duke.commands.ListCommand;
import duke.commands.MarkTaskCommand;
import duke.commands.UnknownCommand;
import duke.commands.UnmarkTaskCommand;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class that parses user commands
 */
public class Parser {
    static final String DATE_FORMAT = "dd-MM-yyyy";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    static final String DATETIME_FORMAT = "dd-MM-yyyy HHmm";

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    /**
     * Parses the user input into a Command object
     * @param commandInput String containing user input
     * @return A Command object that can be executed
     */
    public Command parse(String commandInput) {
        String[] splitInput = commandInput.split(" ", 2);
        String command = splitInput[0];
        try {
            switch (command) {
                case "todo":
                    return parseArguments(Command.Types.TODO, splitInput[1]);
                case "deadline":
                    return parseArguments(Command.Types.DEADLINE, splitInput[1]);
                case "event":
                    return parseArguments(Command.Types.EVENT, splitInput[1]);
                case "list":
                    return new ListCommand();
                case "bye":
                    return new ExitCommand();
                case "mark":
                    return parseArguments(Command.Types.MARK, splitInput[1]);
                case "unmark":
                    return parseArguments(Command.Types.UNMARK, splitInput[1]);
                case "delete":
                    return parseArguments(Command.Types.DELETE, splitInput[1]);
                case "date":
                    return parseArguments(Command.Types.DATE, splitInput[1]);
                default:
                    return new UnknownCommand();
            }
        } catch (IndexOutOfBoundsException e) {
            return new UnknownCommand(String.format("%s requires additional arguments!", command));
        }
    }

    /**
     * Parses arguments for commands that require arguments
     * @param commandType Command Type
     * @param args String containing the arguments
     * @return Command of type commandType initialised with parsed arguments
     */
    protected Command parseArguments(Command.Types commandType, String args) {
        try {
            switch (commandType) {
                case TODO:
                    Task newTodo = parseTaskArgs(Task.Types.TODO, args);
                    return new AddTaskCommand(newTodo);
                case DEADLINE:
                    Task newDeadline = parseTaskArgs(Task.Types.DEADLINE, args);
                    return new AddTaskCommand(newDeadline);
                case EVENT:
                    Task newEvent = parseTaskArgs(Task.Types.EVENT, args);
                    return new AddTaskCommand(newEvent);
                case DATE:
                    LocalDate searchDate = parseDate(args);
                    return new ListByDateCommand(searchDate);
                case MARK:
                    return new MarkTaskCommand(parseOneBasedIndexToZeroBased(args));
                case UNMARK:
                    return new UnmarkTaskCommand(parseOneBasedIndexToZeroBased(args));
                case DELETE:
                    return new DeleteTaskCommand(parseOneBasedIndexToZeroBased(args));
                default:
                    return new UnknownCommand();
            }
        } catch (NumberFormatException e) {
            return new UnknownCommand("I need a positive integer to know which task you're referring to!");
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return new UnknownCommand(e.getMessage());
        }
    }

    /**
     * Creates a task based on a line read from a save file
     * @param fileArgs Line containing task saved data
     * @return Task initialised with arguments
     * @throws UnsupportedTaskType Thrown if task type specified in fileArgs do not match
     * any known task types
     */
    public static Task parseFileTasks(String fileArgs) throws UnsupportedTaskType {
        String[] args = fileArgs.split(" \\| ");
        if (args.length != 3) {
            throw new IllegalArgumentException("Save file is corrupted, skipping line");
        }
        boolean isMarked = args[1].equals("true");
        Task loadedTask;
        switch (args[0]) {
            case "T":
                loadedTask = parseTaskArgs(Task.Types.TODO, args[2]);
                break;
            case "D":
                loadedTask = parseTaskArgs(Task.Types.DEADLINE, args[2]);
                break;
            case "E":
                loadedTask = parseTaskArgs(Task.Types.EVENT, args[2]);
                break;
            default:
                throw new UnsupportedTaskType(args[0]);
        }
        if (isMarked) {
            loadedTask.markAsDone();
        }
        return loadedTask;
    }

    /**
     * Creates a task based on the task type and additional arguments
     * @param taskType Type of task
     * @param args Additional arguments required to create the task
     * @return Task of specified task type initialised with arguments
     * @throws UnsupportedTaskType Thrown if task type is not covered (should not happen)
     */
    private static Task parseTaskArgs(Task.Types taskType, String args) throws UnsupportedTaskType {
        String name;
        switch (taskType) {
            case TODO:
                name = parseNonEmptyString(args);
                return new Todo(name);
            case DEADLINE:
                String[] deadlineArgs = args.split(" /by ");
                if (deadlineArgs.length != 2) {
                    throw new IllegalArgumentException("Deadlines should be created with the following format:\n"
                            + "deadline [name] /by [date]");
                }
                name = parseNonEmptyString(deadlineArgs[0]);
                LocalDateTime byTime = parseDateTime(deadlineArgs[1]);
                return new Deadline(name, byTime);
            case EVENT:
                IllegalArgumentException badFormat = new IllegalArgumentException("Events should be created "
                        + "with the following format:\n event [name] /from [start time] /to [end time]");
                String[] splitNameDates = args.split(" /from ");
                if (splitNameDates.length != 2) {
                    throw badFormat;
                }
                name = splitNameDates[0];
                String[] splitTime = splitNameDates[1].split(" /to ");
                if (splitTime.length != 2) {
                    throw badFormat;
                }
                LocalDateTime startTime = parseDateTime(splitTime[0]);
                LocalDateTime endTime = parseDateTime(splitTime[1]);
                return new Event(name, startTime, endTime);
            default:
                throw new UnsupportedTaskType(taskType.toString());
        }
    }

    /**
     * Exception class for unsupported task types
     */
    public static class UnsupportedTaskType extends RuntimeException {
        final protected String taskType;

        /**
         * Constructs an UnsupportedTaskType exception
         * @param taskType Name of illegal task type
         */
        public UnsupportedTaskType(String taskType) {
            this.taskType = taskType;
        }

        public String getTaskType() {
            return taskType;
        }
    }

    /**
     * Parses string argument for dates
     * @param dateInput String representation of date in dd-MM-yyyy format
     * @return LocalDate object
     * @throws DateTimeParseException Thrown if string is not of dd-MM-yyyy format
     */
    protected LocalDate parseDate(String dateInput) throws DateTimeParseException {
        try {
            return LocalDate.parse(dateInput, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Dates must be of the form " + DATE_FORMAT,
                    e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Parses string argument for DateTimes
     * @param dateTimeInput String representation of date in dd-MM-yyyy HHmm format
     * @return LocalDateTime object
     * @throws DateTimeParseException Thrown if string is not of dd-MM-yyyy HHmm format
     */
    protected static LocalDateTime parseDateTime(String dateTimeInput) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(dateTimeInput, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("DateTimes must be of the form " + DATETIME_FORMAT,
                    e.getParsedString(), e.getErrorIndex());
        }
    }

    /**
     * Returns input if non-empty, otherwise throws an IllegalArgumentException
     * @param input String input
     * @return input
     * @throws IllegalArgumentException Thrown if input is empty
     */
    protected static String parseNonEmptyString(String input) throws IllegalArgumentException {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("I need a name for the task!");
        }
        return input;
    }

    /**
     * Parses zero-based index from string representation of one-based index
     * @param input String representation of one-based index
     * @return Zero-based index in integer form
     */
    protected int parseOneBasedIndexToZeroBased(String input) {
        return Integer.parseUnsignedInt(input) - 1;
    }
}
