package task;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * The `Task` class represents a generic task in the BloopBot application.
 * It serves as the base class for different types of tasks, including Add, ToDo, DeadLine, and Event tasks.
 * A task contains a description and can be marked as completed or not completed.
 * It also provides methods for parsing and formatting date and time information.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String dateTime;

    /**
     * Constructs a new task with the specified description.
     *
     * @param taskDesc The description of the task.
     */
    public Task(String taskDesc) {
        this.description = taskDesc;
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as completed.
     *
     * @return `true` if the task is marked as completed, `false` otherwise.
     */
    public boolean checkIsDone() {
        return isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as completed.
     */
    public void isCompleted() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void isNotCompleted() {
        isDone = false;
    }

    /**
     * Gets the status icon of the task to represent its completion status.
     *
     * @return The status icon as a string, either "[X]" for completed or "[ ]" for not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets a string representation of the task's type icon.
     * This is used to identify the type of the task (Add, ToDo, DeadLine, Event).
     *
     * @return The type icon as a string.
     */
    public String getTask() {
        return "[" + getTypeIcon() + "]";
    }

    /**
     * Gets the type icon of the task.
     * It identifies the type of task (A for Add, T for ToDo, D for DeadLine, E for Event).
     *
     * @return The type icon as a string.
     */
    public String getTypeIcon() {
        if (this instanceof Add) {
            return "A";
        } else if (this instanceof ToDo) {
            return "T";
        } else if (this instanceof DeadLine) {
            return "D";
        } else if (this instanceof Event) {
            return "E";
        }
        return "";
    }

    /**
     * Validates a date string against a specified date format.
     *
     * @param date   The date string to validate.
     * @param format The expected date format (e.g., "yyyy-MM-dd HH:mm").
     * @return true if the date string is valid; false otherwise.
     */
    private static boolean validateDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            formatter.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Formats a LocalDateTime object as a string with the pattern "dd MMM yyyy HH:mm".
     *
     * @param dateTime The LocalDateTime object to be formatted.
     * @return The formatted date and time as a string.
     */
    public String printDateTime(LocalDateTime dateTime) {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String formattedDate = dateTime.format(printFormat);
        return formattedDate;
    }

    /**
     * Checks if dateTime.length() is of length 10 or 15
     * then it returns format properly
     *
     * @param dateTime The date and time string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public LocalDateTime parseDateTime(String dateTime) {
        if (dateTime.length() == 15 || validateDate(dateTime, "dd/MM/yyyy HHmm")) {
            return parseDateTime15(dateTime);
        } else if (dateTime.length() == 10 || validateDate(dateTime, "dd/MM/yyyy")) {
            return parseDateTime10(dateTime);
        } else {
            return parseDateTime15(dateTime);
        }
    }

    /**
     * Parses a date and time string of length 10 as a LocalDateTime object.
     * The expected format of the date is "dd/MM/yyyy".
     *
     * @param dateTime The date and time string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public LocalDateTime parseDateTime10(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate date = LocalDate.parse(dateTime, inputFormatter);
            LocalDateTime opDate = date.atStartOfDay();
            return opDate;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses a date and time string of length 15 as a LocalDateTime object.
     * The expected format of the date is "dd/MM/yyyy HHmm".
     *
     * @param dateTime The date and time string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws IllegalArgumentException if the date format is invalid.
     */
    public LocalDateTime parseDateTime15(String dateTime) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(dateTime, inputFormatter);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
            String formattedFrom = fromDateTime.format(outputFormatter);

            return LocalDateTime.parse(formattedFrom, outputFormatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return getTask() + getStatusIcon() + " " + description;
    }
}
