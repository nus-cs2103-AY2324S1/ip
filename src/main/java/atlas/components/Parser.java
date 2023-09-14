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
        final int maxArgumentsCount = 2;
        String[] splitInput = commandInput.split(" ", maxArgumentsCount);
        String command = splitInput[0];
        try {
            switch (command) {
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
            return new UnknownCommand(command + " requires additional arguments!");
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
                Todo newTodo = parseTodoArgs(args);
                return new AddTaskCommand(newTodo);
            case DEADLINE:
                Deadline newDeadline = parseDeadlineArgs(args);
                return new AddTaskCommand(newDeadline);
            case EVENT:
                Event newEvent = parseEventArgs(args);
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
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return new UnknownCommand(e.getMessage());
        }
    }

    /**
     * Creates a Todo task based on arguments passed in
     * @param args String containing the name of the Todo
     * @return Todo task containing the specified name
     */
    private static Todo parseTodoArgs(String args) {
        String name = parseNonEmptyString(args);
        return new Todo(name);
    }

    /**
     * Creates a Deadline task based on the arguments passed in
     * @param args String in the following format: [name of task] /by [date]
     * @return Deadline task containing the specified name and deadline
     * @throws IllegalArgumentException Thrown if wrong number of arguments provided
     */
    private static Deadline parseDeadlineArgs(String args) throws IllegalArgumentException {
        final String nameDateDelimiter = " /by ";
        String[] deadlineArgs = args.split(nameDateDelimiter);
        boolean hasNameAndDate = deadlineArgs.length == 2;
        if (!hasNameAndDate) {
            throw new IllegalArgumentException("Deadlines should be created with the following format:\n"
                    + "deadline [name] /by [date]");
        }

        String name = parseNonEmptyString(deadlineArgs[0]);
        LocalDateTime byTime = parseDateTime(deadlineArgs[1]);
        return new Deadline(name, byTime);
    }

    /**
     * Creates an Event task based on the arguments passed in
     * @param args String in the following format: [name of task] /from [start time] /to [end time]
     * @return Event task containing the specified name, start time, and end time
     * @throws IllegalArgumentException Thrown if wrong number of arguments provided
     */
    private static Event parseEventArgs(String args) throws IllegalArgumentException {
        IllegalArgumentException badFormat = new IllegalArgumentException("Events should be created "
                + "with the following format:\n event [name] /from [start time] /to [end time]");

        final String nameDateDelimiter = " /from ";
        String[] splitNameDates = args.split(nameDateDelimiter);
        boolean hasNameAndDates = args.length() == 2;
        if (!hasNameAndDates) {
            throw badFormat;
        }

        String name = splitNameDates[0];

        final String datesDelimiter = " /to ";
        String[] splitTime = splitNameDates[1].split(datesDelimiter);
        boolean hasStartAndEndDates = splitTime.length == 2;
        if (!hasStartAndEndDates) {
            throw badFormat;
        }

        LocalDateTime startTime = parseDateTime(splitTime[0]);
        LocalDateTime endTime = parseDateTime(splitTime[1]);
        return new Event(name, startTime, endTime);
    }

    /**
     * Creates a task based on a line read from a save file
     * @param fileArgs Line containing task saved data
     * @return Task initialised with arguments
     * @throws IllegalArgumentException Thrown if line does not have 2 delimiters " | "
     */
    public static Task parseFileTasks(String fileArgs) throws IllegalArgumentException {
        final String fileTaskDelimiter = " \\| ";
        String[] args = fileArgs.split(fileTaskDelimiter);

        boolean hasPrefixStatusArgs = args.length == 3;
        if (!hasPrefixStatusArgs) {
            throw new IllegalArgumentException("Save file is corrupted, skipping line");
        }

        String taskTypePrefix = args[0];
        String taskStatus = args[1];
        String taskArgs = args[2];

        Task loadedTask = createTaskFromPrefix(taskTypePrefix, taskArgs);
        return markTaskByStatus(loadedTask, taskStatus);
    }

    /**
     * Creates a task based on the task prefix and task arguments
     * @param taskTypePrefix "T" for Todo, "D" for Deadline, "E" for Event
     * @param taskArgs Arguments for the task specified
     * @return Task of specified task type and task arguments
     * @throws UnsupportedTaskType Thrown if task type is not one of "T", "D", or "E"
     */
    private static Task createTaskFromPrefix(String taskTypePrefix, String taskArgs) throws
            UnsupportedTaskType {
        switch (taskTypePrefix) {
        case "T":
            return parseTodoArgs(taskArgs);
        case "D":
            return parseDeadlineArgs(taskArgs);
        case "E":
            return parseEventArgs(taskArgs);
        default:
            throw new UnsupportedTaskType(taskTypePrefix);
        }
    }

    /**
     * Marks task based on the taskStatus saved in the file
     * @param task Task to mark as completed/not completed
     * @param taskStatus "true" is completed, "false" otherwise (anything other than "true"
     *                   is assumed to be "false")
     * @return Task with completion status updated based on task status
     */
    private static Task markTaskByStatus(Task task, String taskStatus) {
        final String taskMarkedKeyword = "true";
        boolean isMarked = taskStatus.equals(taskMarkedKeyword);
        if (isMarked) {
            task.markDone();
        } else {
            task.markNotDone();
        }
        return task;
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
     * @throws NumberFormatException Thrown if input is not an unsigned integer in string format
     */
    protected int parseOneBasedIndexToZeroBased(String input) throws NumberFormatException {
        try {
            return Integer.parseUnsignedInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("I need an unsigned integer as index, "
                    + "I can't parse this index: " + input);
        }
    }

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
