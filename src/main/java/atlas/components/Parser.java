package atlas.components;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;

import atlas.commands.AddTaskCommand;
import atlas.commands.Command;
import atlas.commands.DeleteTaskCommand;
import atlas.commands.FindCommand;
import atlas.commands.HelpCommand;
import atlas.commands.ListByDateCommand;
import atlas.commands.ListCommand;
import atlas.commands.ListRemindersCommand;
import atlas.commands.MarkTaskCommand;
import atlas.commands.UnmarkTaskCommand;
import atlas.exceptions.BadDateException;
import atlas.exceptions.BadDateTimeException;
import atlas.exceptions.BadFormatException;
import atlas.exceptions.BadIndexException;
import atlas.exceptions.MissingCommandArgsException;
import atlas.exceptions.MissingNameException;
import atlas.exceptions.UnknownCommandException;
import atlas.exceptions.UnsupportedTaskTypeException;
import atlas.exceptions.WrongDateOrderException;
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
     * @throws UnknownCommandException Thrown if command type is not recognised
     * @throws MissingCommandArgsException Thrown if command requires arguments but none are given
     */
    public Command parse(String commandInput) throws UnknownCommandException, MissingCommandArgsException {
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
            case "remind":
                return new ListRemindersCommand();
            case "help":
                return new HelpCommand();
            default:
                throw new UnknownCommandException(commandType);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MissingCommandArgsException(commandType);
        }
    }

    /**
     * Parses arguments for commands that require arguments
     * @param commandType Command Type
     * @param args String containing the arguments
     * @return Command of type commandType initialised with parsed arguments
     * @throws UnknownCommandException Thrown if command type does not handle arguments
     */
    protected Command parseArguments(Command.Type commandType, String args) throws UnknownCommandException {
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
            // This branch should never be encountered, unless the switch statements do not cover the entire
            // enumeration of Command.Type
            throw new UnknownCommandException(commandType.name());
        }
    }

    /**
     * Creates a Todo task based on arguments passed in
     * @param args String containing the name of the Todo and optionally a date from which
     *             to start reminders. If a reminder date is included, the format of the
     *             args should be: "[name] /remind [reminder start date]"
     * @return Todo task containing the specified name and reminder (if provided)
     */
    private static Todo parseTodoArgs(String args) {
        String[] nameAndReminder = splitArgsAndReminders(args);
        String name = parseNonEmptyName(Task.Type.TODO, nameAndReminder[0]);

        boolean hasReminder = nameAndReminder.length == 2;
        if (hasReminder) {
            LocalDate reminderStartDate = parseDate(nameAndReminder[1]);
            return new Todo(name, reminderStartDate);
        }
        return new Todo(name);
    }

    /**
     * Splits task args and reminder dates into separate elements
     * @param args String containing task args and optionally reminder dates. If the latter is
     *             included, the string format should be: "[task args] /remind [reminder start date]"
     * @return Either an array of 2 elements [task args, reminder date], or just [task args] if
     *      the reminder date is not provided
     */
    private static String[] splitArgsAndReminders(String args) {
        final String reminderDelimiter = " /remind ";
        return args.split(reminderDelimiter);
    }

    /**
     * Creates a Deadline task based on the arguments passed in
     * @param args String in the following format: "[name of task] /by [date]
     *             (/remind [reminder start date])"
     * @return Deadline task containing the specified name and deadline
     * @throws BadFormatException Thrown if command does not follow the specified format
     * @throws WrongDateOrderException Thrown if reminder is after the deadline
     */
    private static Deadline parseDeadlineArgs(String args) throws BadFormatException, WrongDateOrderException {
        final String deadLineCorrectFormat = "deadline [name] /by [date] (/remind [reminder start date])";
        String[] argsAndReminder = splitArgsAndReminders(args);

        final String nameDateDelimiter = " /by ";
        String[] deadlineArgs = argsAndReminder[0].split(nameDateDelimiter);
        boolean hasNameAndDate = deadlineArgs.length == 2;
        if (!hasNameAndDate) {
            throw new BadFormatException(Task.Type.DEADLINE, deadLineCorrectFormat);
        }

        String name = parseNonEmptyName(Task.Type.DEADLINE, deadlineArgs[0]);
        LocalDateTime byTime = parseDateTime(deadlineArgs[1]);

        boolean hasReminder = argsAndReminder.length == 2;
        if (hasReminder) {
            LocalDate reminderStartDate = parseDate(argsAndReminder[1]);
            if (reminderStartDate.isAfter(byTime.toLocalDate())) {
                throw new WrongDateOrderException(reminderStartDate, byTime.toLocalDate());
            }
            return new Deadline(name, byTime, reminderStartDate);
        }
        return new Deadline(name, byTime);
    }

    /**
     * Creates an Event task based on the arguments passed in
     * @param args String in the following format: "[name of task] /from [start time] /to [end time]
     *             (/remind [reminder start date])"
     * @return Event task containing the specified name, start time, and end time
     * @throws BadFormatException Thrown if command does not follow the format specified
     * @throws WrongDateOrderException Thrown if reminder is after the end time
     */
    private static Event parseEventArgs(String args) throws BadFormatException, WrongDateOrderException {
        final String eventCorrectFormat = "event [name] /from [start time] /to [end time]"
                + " (/remind [reminder start date])";

        String[] argsAndReminder = splitArgsAndReminders(args);

        final String nameDateDelimiter = " /from ";
        String[] splitNameDates = argsAndReminder[0].split(nameDateDelimiter);
        boolean hasNameAndDates = splitNameDates.length == 2;
        if (!hasNameAndDates) {
            throw new BadFormatException(Task.Type.EVENT, eventCorrectFormat);
        }

        final String datesDelimiter = " /to ";
        String[] splitTime = splitNameDates[1].split(datesDelimiter);
        boolean hasStartAndEndDates = splitTime.length == 2;
        if (!hasStartAndEndDates) {
            throw new BadFormatException(Task.Type.EVENT, eventCorrectFormat);
        }

        String name = parseNonEmptyName(Task.Type.EVENT, splitNameDates[0]);
        LocalDateTime startTime = parseDateTime(splitTime[0]);
        LocalDateTime endTime = parseDateTime(splitTime[1]);

        boolean hasReminder = argsAndReminder.length == 2;
        if (hasReminder) {
            LocalDate reminderStartDate = parseDate(argsAndReminder[1]);
            if (reminderStartDate.isAfter(endTime.toLocalDate())) {
                throw new WrongDateOrderException(reminderStartDate, endTime.toLocalDate());
            }
            return new Event(name, startTime, endTime, reminderStartDate);
        }
        return new Event(name, startTime, endTime);
    }

    /**
     * Creates a task based on a line read from a save file
     * @param fileArgs Line containing task saved data
     * @return An Optional object containing the Task initialised with arguments if successful,
     *      otherwise an empty Optional object
     */
    public static Optional<Task> parseFileTasks(String fileArgs) {
        final String fileTaskDelimiter = " \\| ";
        String[] args = fileArgs.split(fileTaskDelimiter);

        boolean hasPrefixStatusArgs = args.length == 3;
        if (!hasPrefixStatusArgs) {
            return Optional.empty();
        }

        String taskTypePrefix = args[0];
        String taskStatus = args[1];
        String taskArgs = args[2];

        try {
            Task loadedTask = createTaskFromPrefix(taskTypePrefix, taskArgs);
            return Optional.of(markTaskByStatus(loadedTask, taskStatus));
        } catch (IllegalArgumentException e) {
            System.out.println("Your scrolls are gibberish, mortal. Nothing could be comprehended.");
        } catch (UnsupportedTaskTypeException e) {
            System.out.println("Unsupported task type " + e.getTaskType() + ", skipping task");
        }
        return Optional.empty();
    }

    /**
     * Creates a task based on the task prefix and task arguments
     * @param taskTypePrefix "T" for Todo, "D" for Deadline, "E" for Event
     * @param taskArgs Arguments for the task specified
     * @return Task of specified task type and task arguments
     * @throws UnsupportedTaskTypeException Thrown if task type is not one of "T", "D", or "E"
     */
    private static Task createTaskFromPrefix(String taskTypePrefix, String taskArgs) throws
            UnsupportedTaskTypeException {
        switch (taskTypePrefix) {
        case "T":
            return parseTodoArgs(taskArgs);
        case "D":
            return parseDeadlineArgs(taskArgs);
        case "E":
            return parseEventArgs(taskArgs);
        default:
            throw new UnsupportedTaskTypeException(taskTypePrefix);
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
            return task.markDone();
        } else {
            return task.markNotDone();
        }
    }

    /**
     * Parses string argument for dates
     * @param dateInput String representation of date in dd-MM-yyyy format
     * @return LocalDate object
     * @throws BadDateException Thrown if string is not of dd-MM-yyyy format
     */
    protected static LocalDate parseDate(String dateInput) throws BadDateException {
        try {
            return LocalDate.parse(dateInput, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new BadDateException(dateInput);
        }
    }

    /**
     * Parses string argument for DateTimes
     * @param dateTimeInput String representation of date in dd-MM-yyyy HHmm format
     * @return LocalDateTime object
     * @throws BadDateTimeException Thrown if string is not of dd-MM-yyyy HHmm format
     */
    protected static LocalDateTime parseDateTime(String dateTimeInput) throws BadDateTimeException {
        try {
            return LocalDateTime.parse(dateTimeInput, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new BadDateTimeException(dateTimeInput);
        }
    }

    /**
     * Returns input if non-empty, otherwise throws a MissingNameException
     * @param input String input
     * @return input
     * @throws MissingNameException Thrown if input is empty
     */
    protected static String parseNonEmptyName(Task.Type taskType, String input) throws MissingNameException {
        if (input.isEmpty()) {
            throw new MissingNameException(taskType);
        }
        return input;
    }

    /**
     * Parses zero-based index from string representation of one-based index
     * @param input String representation of one-based index
     * @return Zero-based index in integer form
     * @throws BadIndexException Thrown if input is not an integer greater than zero in string format
     */
    protected int parseOneBasedIndexToZeroBased(String input) throws BadIndexException {
        try {
            return Integer.parseUnsignedInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new BadIndexException(input);
        }
    }

    /**
     * Parses array of zero-based indices from string representation of one-based indices
     * @param input String representation of one or more one-based indices
     * @return Array of zero-based indices in integer form
     */
    protected Integer[] parseMultipleOneBasedIndicesToZeroBased(String input) {
        final String indexDelimiter = " ";
        String[] splitIndices = input.split(indexDelimiter);
        return Arrays.stream(splitIndices)
                .map(this::parseOneBasedIndexToZeroBased)
                .toArray(Integer[]::new);
    }
}
