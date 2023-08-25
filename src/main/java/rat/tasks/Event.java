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
     * The start time of the task, represented by a String.
     */
    private LocalDateTime startTime;

    /**
     * The end time of the task, represented by a String.
     */
    private LocalDateTime endTime;

    private String startTimeString;

    private String endTimeString;

    /**
     * Constructor for an Event task.
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

    @Override
    public String formatForFile() {
        String taskType = "E";
        return taskType + ", " + super.formatForFile() + ", " + this.startTime.toString() + ", " + this.endTime.toString();
    }

}
