package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class inherits from the Task class and represents an Event task type
 * with a description, a start and end date & time and completion status
 */
public class Event extends Task {
    LocalDateTime startDate;
    LocalDateTime endDate;
    //Introducing LocalDateTime to parse the string inputs given by the user
    //for the relevant date and time

    /**
     * Constructor to build an Event Task Object with the task description,
     * start date, and end date & time.
     *
     * @param description The description of the Event Task.
     * @param startDate   The start date and time of the Event.
     * @param endDate     The end date and time of the Event.
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Overloaded constructor to build an Event Task Object read from the tasks
     * saved in the txt file with the task description, completion status,
     * start date and end date & time.
     *
     * @param description The description of the Event Task.
     * @param isDone      Represents the completion status of the task.
     * @param startDate   The start date and time of the Event.
     * @param endDate     The end date and time of the Event.
     */
    public Event(String description, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Returns a formatted string representation of the Event Task, including
     * its type, description, start date, and end date & time.
     *
     * @return A string representing the Event Task.
     */
    @Override
    public String taskString() {
        return "[E]" + super.taskString() + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
    /**
     * Returns a string representation of the Event Task that can be used for
     * saving the event tasks to a txt file, including its type, completion status,
     * description, start date, and end date & time.
     *
     * @return A string suitable for saving the Event Task to a text file.
     */
    @Override
    public String saveTaskString() {
        String status = (this.isDone ? "1" : "0");
        return "E" + "|" + status + "|" + this.description
                + "|" + this.startDate + "|" + this.endDate;
    }
}