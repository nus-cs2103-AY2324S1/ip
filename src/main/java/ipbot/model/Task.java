package ipbot.model;

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
     * Returns a String to represent whether the task has been marked.
     *
     * @return "X" if task has been marked. " " otherwise.
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
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
     * Creates a task from its String representation in CSV format.
     *
     * @param string The String representation in CSV format.
     * @return The corresponding created Task object.
     * @throws TaskFormatException
     * @throws DateTimeParseException
     */
    public static Task fromString(String string) throws TaskFormatException, DateTimeParseException {
        String[] args = string.split(",");
        if (args.length < 2) {
            throw new TaskFormatException("Task string is too short!");
        }
        if (!args[0].matches("^[a-zA-Z]$")) {
            throw new TaskFormatException("Task string has incorrect format for type!");
        }
        char taskType = args[0].charAt(0);
        if (!args[1].matches("^[X ]$")) {
            throw new TaskFormatException("Task string has incorrect format for completion status!");
        }
        char completionStatus = args[1].charAt(0);
        Task retTask;
        switch (taskType) {
        case 'T':
            if (args.length != 3) {
                throw new TaskFormatException(
                        "Task string has wrong number of arguments for ToDo " + args.length + "!");
            }
            ToDo toDo = new ToDo(args[2]);
            retTask = toDo;
            break;
        case 'D':
            if (args.length != 4) {
                throw new TaskFormatException(
                        "Task string has wrong number of arguments for Deadline " + args.length + "!");
            }
            Deadline deadline = new Deadline(args[2], args[3]);
            retTask = deadline;
            break;
        case 'E':
            if (args.length != 5) {
                throw new TaskFormatException(
                        "Task string has wrong number of arguments for Event " + args.length + "!");
            }
            Event event = new Event(args[2], args[3], args[4]);
            retTask = event;
            break;
        default:
            throw new TaskFormatException("Task type is invalid!");
        }
        if (completionStatus == 'X') {
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
}

