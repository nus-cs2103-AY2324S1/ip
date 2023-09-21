package Frenchie;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event that inherits from the Task class.
 * <p>
 * The Event class has 2 additional LocalDateTime attributes startTime and endTime
 * which represent the time the event starts and ends respectively.
 * <p>
 */
public class Event extends Task{
    LocalDateTime startTime;
    LocalDateTime endTime;

    /**
     *  Constructs a new Event object, with a default false value for isCompleted as all tasks inputted into the task list are incomplete.
     *  Takes in a String which is the name of the task.
     *  Takes in String startTime and String endTime in the format "dd/MM/yyyy HH:mm"
     *  which is then parsed into a formatter and stored as LocalDateTime objects.
     */
    public Event(String name, String startTime, String endTime) {
        this.taskName = name;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.startTime = LocalDateTime.parse(startTime, formatter);
        this.endTime = LocalDateTime.parse(endTime, formatter);
        this.isCompleted = false;
    }

    /**
     *  Overrides the toString() method inherited from the Task class.
     *  [E] to indicate that the task is an Event.
     *  startTime and endTime is formatted in 'yyyy-MM-dd HH:mm' to output.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String desiredStartFormat = this.startTime.format(formatter);
        String desiredEndFormat = this.endTime.format(formatter);
        return "[E]" + super.toString() + "(from: " + desiredStartFormat + " to: " + desiredEndFormat + ")";
    }
}
