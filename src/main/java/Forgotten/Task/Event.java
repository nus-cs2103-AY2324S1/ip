package Forgotten.Task;

import Forgotten.Priority;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    private Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Default constructor method.
     *
     * @param description Description of this event task.
     * @param startTime The starting time of this event.
     * @param endTime The ending time of this event.
     * @param isDone Status of this task, either done or not done.
     */
    public Event(String description, LocalDate startTime,
                 LocalDate endTime, boolean isDone, Priority priority) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * This is a factory method which generates an Event task.
     *
     * @param description Description of this Event task.
     * @return The Event task.
     */
    public static Event createNewEventTask(String description) {
        String[] splitMessage1 = description.split(" /from ");
        String[] splitMessage2 = splitMessage1[1].split(" /to ");
        return new Event(splitMessage1[0], LocalDate.parse(splitMessage2[0]), LocalDate.parse(splitMessage2[1]));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                + " [P: " + this.priority + "]";
    }

    /**
     * This method returns the string representation of this Event task
     * in a format which can be stored in the hard disk.
     *
     * @return The string representation of this Event task.
     */
    @Override
    public String toFileString() {
        return "[E]" + super.toString()
                + " (from: " + this.startTime + " to: " + this.endTime + ")"
                + " [P: " + this.priority + "]";
    }
}
