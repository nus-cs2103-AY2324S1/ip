package atlas.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import atlas.commands.AddTaskCommand;
import atlas.commands.Command;
import atlas.commands.DeleteTaskCommand;
import atlas.commands.FindCommand;
import atlas.commands.ListByDateCommand;
import atlas.commands.ListCommand;
import atlas.commands.MarkTaskCommand;
import atlas.commands.UnknownCommand;
import atlas.commands.UnmarkTaskCommand;
import atlas.tasks.Deadline;
import atlas.tasks.Event;
import atlas.tasks.Task;
import atlas.tasks.Todo;

/**
 * Class that parses user commands
 */
public class Parser {
    private static final String DATE_FORMAT = "dd-MM-yyyy";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final String DATETIME_FORMAT = "dd-MM-yyyy HHmm";

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    /**
     * Parses the user input into a Command object
     * @param commandInput String containing user input
     * @return A Command object that can be executed
     */
    public Command parse(String commandInput) {
        final int maxArgCount = 2;
        final String commandArgDelimiter = " ";
        String[] splitInput = commandInput.split(commandArgDelimiter, maxArgCount);
        String commandType = splitInput[0];
        try {
            switch (commandType) {
            case "todo":
                return parseArguments(Command.Type.TODO, splitInput[1]);
            case "deadline":
                return parseArguments(Command.Type.DEADLINE, splitInput[1]);
            case "event":
                return parseArguments(Command.Type.EVENT, splitInput[1]);
            case "list":
                return new ListCommand();
            case "mark":
                return parseArguments(Command.Type.MARK, splitInput[1]);
            case "unmark":
                return parseArguments(Command.Type.UNMARK, splitInput[1]);
            case "delete":
                return parseArguments(Command.Type.DELETE, splitInput[1]);
            case "date":
                return parseArguments(Command.Type.DATE, splitInput[1]);
            case "find":
                return parseArguments(Command.Type.FIND, splitInput[1]);
            default:
                return new UnknownCommand();
            }
        } catch (IndexOutOfBoundsException e) {
            return new UnknownCommand(String.format("%s requires additional arguments!", commandType));
        }
    }

    /**
     * Parses arguments for commands that require arguments
     * @param commandType Command Type
     * @param args String containing the arguments
     * @return Command of type commandType initialised with parsed arguments
     */
    protected Command parseArguments(Command.Type commandType, String args) {
        try {
            switch (commandType) {
            case TODO:
                Task newTodo = parseTodoArgs(args);
                return new AddTaskCommand(newTodo);
            case DEADLINE:
                Task newDeadline = parseDeadlineArgs(args);
                return new AddTaskCommand(newDeadline);
            case EVENT:
                Task newEvent = parseEventArgs(args);
                return new AddTaskCommand(newEvent);
            case DATE:
                LocalDate searchDate = parseDate(args);
                return new ListByDateCommand(searchDate);
            case MARK:
                return new MarkTaskCommand(parseMultipleOneBasedIndicesToZeroBased(args));
            case UNMARK:
                return new UnmarkTaskCommand(parseMultipleOneBasedIndicesToZeroBased(args));
            case DELETE:
                return new DeleteTaskCommand(parseOneBasedIndexToZeroBased(args));
            case FIND:
                return new FindCommand(args);
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
     * Parses arguments for a Todo task (name) and creates a new Todo
     * @param args String containing the name of the Todo
     * @return Todo task with the given name
     */
    private static Todo parseTodoArgs(String args) {
        String name = parseNonEmptyString(args);
        return new Todo(name);
    }

    /**
     * Parses arguments for a Deadline task (name, deadline) and creates a new Deadline
     * @param args String containing the arguments: name, time
     * @return Deadline task with the given name and deadline
     * @throws IllegalArgumentException Thrown if either the name or time is not provided
     */
    private static Deadline parseDeadlineArgs(String args) throws IllegalArgumentException {
        final String argDelimiter = " /by ";
        String[] deadlineArgs = args.split(argDelimiter);

        final int numArgsRequired = 2;
        if (deadlineArgs.length != numArgsRequired) {
            throw new IllegalArgumentException("Deadlines should be created with the following format:\n"
                    + "deadline [name] /by [time]");
        }

        String name = parseNonEmptyString(deadlineArgs[0]);
        LocalDateTime byTime = parseDateTime(deadlineArgs[1]);
        return new Deadline(name, byTime);
    }

    /**
     * Parses arguments for an Event task (name, start, end) and creates a new Event
     * @param args String containing the arguments: name, start, end
     * @return Event task with the given name, start and end
     * @throws IllegalArgumentException Thrown if either one of the arguments is not present
     */
    private static Event parseEventArgs(String args) throws IllegalArgumentException {
        final String badFormatErrorMessage = "Events should be created "
                + "with the following format:\n event [name] /from [start time] /to [end time]";

        final String nameDatesDelimiter = " /from ";
        String[] splitNameDates = args.split(nameDatesDelimiter);

        if (splitNameDates.length != 2) {
            throw new IllegalArgumentException(badFormatErrorMessage);
        }

        String name = splitNameDates[0];
        String dates = splitNameDates[1];

        final String startEndDelimiter = " /to ";
        String[] splitTime = dates.split(startEndDelimiter);

        final int numDatetimeRequired = 2;
        if (splitTime.length != numDatetimeRequired) {
            throw new IllegalArgumentException(badFormatErrorMessage);
        }

        String startTimeString = splitTime[0];
        String endTimeString = splitTime[1];

        LocalDateTime startTime = parseDateTime(startTimeString);
        LocalDateTime endTime = parseDateTime(endTimeString);
        return new Event(name, startTime, endTime);
    }

    /**
     * Creates a task based on a line read from a save file
     * @param fileArgs Line containing task saved data
     * @return Task initialised with arguments
     * @throws UnsupportedTaskType Thrown if task type specified in fileArgs do not match
     *     any known task types
     * @throws IllegalArgumentException Thrown if line does not have 2 delimiters " | "
     */
    public static Task parseFileTasks(String fileArgs) throws UnsupportedTaskType, IllegalArgumentException {
        final String taskArgsDelimiter = " \\| ";
        String[] args = fileArgs.split(taskArgsDelimiter);
        final int numTaskArgsRequired = 3;
        if (args.length != numTaskArgsRequired) {
            throw new IllegalArgumentException("Save file is corrupted, skipping line");
        }

        Task loadedTask;
        String taskTypePrefix = args[0];
        String taskArgs = args[2];
        switch (taskTypePrefix) {
        case "T":
            loadedTask = parseTodoArgs(taskArgs);
            break;
        case "D":
            loadedTask = parseDeadlineArgs(taskArgs);
            break;
        case "E":
            loadedTask = parseEventArgs(taskArgs);
            break;
        default:
            throw new UnsupportedTaskType(taskTypePrefix);
        }

        final String taskMarkedKeyword = "true";

        boolean isMarked = args[1].equals(taskMarkedKeyword);
        if (isMarked) {
            loadedTask.markDone();
        }

        return loadedTask;
    }

    /**
     * Exception class for unsupported task types
     */
    public static class UnsupportedTaskType extends RuntimeException {
        protected final String taskType;

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

    /**
     * Parses array of zero-based indices from string representation of one-based indices
     * @param input String representation of one or more one-based indices
     * @return Array of zero-based indices in integer form
     */
    protected int[] parseMultipleOneBasedIndicesToZeroBased(String input) {
        final String indexDelimiter = " ";
        String[] splitIndices = input.split(indexDelimiter);
        int[] output = new int[splitIndices.length];
        for (int i = 0; i < splitIndices.length; ++i) {
            output[i] = parseOneBasedIndexToZeroBased(splitIndices[i]);
        }
        return output;
    }
}
