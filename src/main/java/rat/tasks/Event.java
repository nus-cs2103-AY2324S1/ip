package rat.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates an Event task.
 * An Event task is a task with a start time and end time as Strings.
 * @author Keagan
 */
public class Event extends Task {

    /**
     * The start time of the task, represented by a LocalDateTime object.
     */
    private LocalDateTime startTime;

    /**
     * The end time of the task, represented by a String.
     */
    private LocalDateTime endTime;

    /**
     * The start time of the task, represented by a String.
     * The date and time is formatted as "EEE, d MMM yyyy HH:mm".
     */
    private String startTimeString;

    /**
     * The end time of the task, represented by a String.
     * The date and time is formatted as "EEE, d MMM yyyy HH:mm".
     */
    private String endTimeString;

    /**
     * Constructor for an Event task.
     * This constructor uses Strings to represent the start time and end time of the task.
     * Used during regular runtime of Rat where user enters data from the command line.
     * @param startTime The start time of the task.
     * @param endTime The end time of the task.
     * @param name The name of the task.
     */
    protected Event(String startTime, String endTime, String name) {
        super(name);
        this.startTime = LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.endTime = LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.startTimeString = this.startTime.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm"));
        this.endTimeString = this.endTime.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm"));
    }

    /**
     * Constructor for an Event task.
     * This constructor uses LocalDateTime objects to represent the start time and end time of the task.
     * Used when reading data from a file.
     * @param startTime The start time of the task.
     * @param endTime The end time of the task.
     * @param name The name of the task.
     */
    protected Event(LocalDateTime startTime, LocalDateTime endTime, String name) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
        this.startTimeString = this.startTime.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm"));
        this.endTimeString = this.endTime.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm"));
    }

    /**
     * Returns a String representation of an Event task.
     * The String representation of an Event task is its name prefixed by its status and type.
     * @return A String representation of an Event task.
     */
    @Override
    public String toString() {
        String taskType = "[E]";
        return taskType + super.toString() + " (from: " + this.startTimeString + " to: " + this.endTimeString + ")";
    }

    /**
     * Returns a String representation of an Event task that is used to write to a file.
     * @return The String representation of an Event task in the format used to write to a file.
     */
    @Override
    public String formatForFile() {
        String taskType = "E";
        return taskType + ", " + super.formatForFile() + ", "
                + this.startTime.toString() + ", " + this.endTime.toString();
    }

}
