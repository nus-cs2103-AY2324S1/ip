package ipbot.model;

import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the general structure of a task to be added to the list.
 */
public class Task {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "uuuu-MM-dd HHmm");
    public static final DateTimeFormatter DISPLAY_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "dd LLL yyyy hh:mm a");
    private static final String TASK_STRING_TOO_SHORT = "Task string is too short!";
    private static final String TASK_STRING_TYPE_INCORRECT_FORMAT = "Task string has incorrect format for type!";
    private static final String TASK_STRING_STATUS_INCORRECT_FORMAT = "" +
            "Task string has incorrect format for completion status!";
    private static final String WRONG_NUMBER_ARGUMENTS_FORMAT = "Task string has wrong number of arguments for %s %d!";
    private static final String INVALID_TASK_TYPE = "Task type is invalid!";
    private static final char MARKED_TASK = 'X';
    private static final char UNMARKED_TASK = ' ';
    protected String description;
    protected boolean isDone;

    /**
     * Defines a task with a description and marks it as unmarked.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a char to represent whether the task has been marked.
     *
     * @return "X" if task has been marked. " " otherwise.
     */
    public char getStatusIcon() {
        return (this.isDone ? MARKED_TASK : UNMARKED_TASK);
    }

    /**
     * Marks this task as done.
     *
     * @return True if task was not marked before. False otherwise.
     */
    public boolean markDone() {
        if(this.isDone){
            return false;
        }
        this.isDone = true;
        return true;
    }

    /**
     * Marks this task as undone.
     *
     * @return True if task was not unmarked before. False otherwise.
     */
    public boolean unmarkDone() {
        if(!this.isDone){
            return false;
        }
        this.isDone = false;
        return true;
    }

    /**
     * Parses the parameters of a Task from its CSV representation.
     * @param string The String representation of the task in CSV format
     * @return The parsed parameters of the Task.
     * @throws TaskFormatException
     */
    private static String[] commaStringToArgs(String string) throws TaskFormatException {
        String[] args = string.split(",");
        if (args.length < 2) {
            throw new TaskFormatException(TASK_STRING_TOO_SHORT);
        }
        if (!args[0].matches("^[a-zA-Z]$")) {
            throw new TaskFormatException(TASK_STRING_TYPE_INCORRECT_FORMAT);
        }
        if (!args[1].matches("^[" + MARKED_TASK + UNMARKED_TASK + "]$")) {
            throw new TaskFormatException(TASK_STRING_STATUS_INCORRECT_FORMAT);
        }
        return args;
    }

    /**
     * Creates a task from its String representation in CSV format.
     *
     * @param string The String representation of the task in CSV format.
     * @return The corresponding created Task object.
     * @throws TaskFormatException
     * @throws DateTimeParseException
     */
    public static Task fromCommaString(String string) throws TaskFormatException, DateTimeParseException {
        assert string != null : "String representation of Task is null";
        String[] args = commaStringToArgs(string);
        char taskType = args[0].charAt(0);
        char completionStatus = args[1].charAt(0);
        Task retTask;
        switch (taskType) {
        case 'T':
            if (args.length != 3) {
                throw new TaskFormatException(String.format(WRONG_NUMBER_ARGUMENTS_FORMAT, "ToDo", args.length));
            }
            ToDo toDo = new ToDo(args[2]);
            retTask = toDo;
            break;
        case 'D':
            if (args.length != 4) {
                throw new TaskFormatException(String.format(WRONG_NUMBER_ARGUMENTS_FORMAT, "Deadline", args.length));
            }
            Deadline deadline = new Deadline(args[2], args[3]);
            retTask = deadline;
            break;
        case 'E':
            if (args.length != 5) {
                throw new TaskFormatException(String.format(WRONG_NUMBER_ARGUMENTS_FORMAT, "Event", args.length));
            }
            Event event = new Event(args[2], args[3], args[4]);
            retTask = event;
            break;
        default:
            throw new TaskFormatException(INVALID_TASK_TYPE);
        }
        if (completionStatus == MARKED_TASK) {
            retTask.markDone();
        }
        return retTask;
    }

    /**
     * Returns the CSV representation of the task object.
     *
     * @return The CSV representation of the task object.
     */
    public String toCommaString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Creates a copy of this Task with the same parameters.
     * Copy is unmarked by default.
     *
     * @return A copy of this Task.
     */
    public Task copy() {
        throw new UnsupportedOperationException();
    }

    /**
     * Translates all the relevant datetime variables by a certain period.
     *
     * @param period The period to translate by.
     */
    public void translateTime(Period period) {
        throw new UnsupportedOperationException();
    }

    protected enum TaskType {
        TODO('T'),
        DEADLINE('D'),
        EVENT('E');

        private final char type;

        TaskType(char type) {
            this.type = type;
        }

        public char getType() {
            return type;
        }
    }
}

