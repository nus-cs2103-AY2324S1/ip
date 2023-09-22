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
            case "remind":
                return new ListRemindersCommand();
            case "help":
                return new HelpCommand();
            default:
                return new UnknownCommand();
            }
        } catch (IndexOutOfBoundsException e) {
            return new UnknownCommand(commandType + " requires additional arguments!");
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
            return new UnknownCommand("I may be an expert mathematician, but even I need a positive"
                    + " integer to know what you are referring to.");
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return new UnknownCommand(e.getMessage());
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
        String name = nameAndReminder[0];

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
     * @throws IllegalArgumentException Thrown if wrong number of arguments provided
     */
    private static Deadline parseDeadlineArgs(String args) throws IllegalArgumentException {
        String[] argsAndReminder = splitArgsAndReminders(args);

        final String nameDateDelimiter = " /by ";
        String[] deadlineArgs = argsAndReminder[0].split(nameDateDelimiter);
        boolean hasNameAndDate = deadlineArgs.length == 2;
        if (!hasNameAndDate) {
            throw new IllegalArgumentException("Deadlines can only be invoked with the following format:\n"
                    + "deadline [name] /by [date] (/remind [reminder start date])");
        }

        String name = parseNonEmptyString(deadlineArgs[0]);
        LocalDateTime byTime = parseDateTime(deadlineArgs[1]);

        boolean hasReminder = argsAndReminder.length == 2;
        if (hasReminder) {
            LocalDate reminderStartDate = parseDate(argsAndReminder[1]);
            return new Deadline(name, byTime, reminderStartDate);
        }
        return new Deadline(name, byTime);
    }

    /**
     * Creates an Event task based on the arguments passed in
     * @param args String in the following format: "[name of task] /from [start time] /to [end time]
     *             (/remind [reminder start date])"
     * @return Event task containing the specified name, start time, and end time
     * @throws IllegalArgumentException Thrown if wrong number of arguments provided
     */
    private static Event parseEventArgs(String args) throws IllegalArgumentException {
        IllegalArgumentException badFormat = new IllegalArgumentException("Events can only be invoked "
                + "with the following format:\n event [name] /from [start time] /to [end time]"
                + " (/remind [reminder start date])");

        String[] argsAndReminder = splitArgsAndReminders(args);

        final String nameDateDelimiter = " /from ";
        String[] splitNameDates = argsAndReminder[0].split(nameDateDelimiter);
        boolean hasNameAndDates = splitNameDates.length == 2;
        if (!hasNameAndDates) {
            throw badFormat;
        }

        final String datesDelimiter = " /to ";
        String[] splitTime = splitNameDates[1].split(datesDelimiter);
        boolean hasStartAndEndDates = splitTime.length == 2;
        if (!hasStartAndEndDates) {
            throw badFormat;
        }

        String name = splitNameDates[0];
        LocalDateTime startTime = parseDateTime(splitTime[0]);
        LocalDateTime endTime = parseDateTime(splitTime[1]);

        boolean hasReminder = argsAndReminder.length == 2;
        if (hasReminder) {
            LocalDate reminderStartDate = parseDate(argsAndReminder[1]);
            return new Event(name, startTime, endTime, reminderStartDate);
        }
        return new Event(name, startTime, endTime);
    }

    /**
     * Creates a task based on a line read from a save file
     * @param fileArgs Line containing task saved data
     * @return An Optional object containing the Task initialised with arguments if successful,
     *      otherwise an empty Optional object
     * @throws IllegalArgumentException Thrown if line does not have 2 delimiters " | "
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
        } catch (UnsupportedTaskType e) {
            System.out.println("Unsupported task type " + e.getTaskType() + ", skipping task");
        }
        return Optional.empty();
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
            return task.markDone();
        } else {
            return task.markNotDone();
        }
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
    protected static LocalDate parseDate(String dateInput) throws DateTimeParseException {
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
