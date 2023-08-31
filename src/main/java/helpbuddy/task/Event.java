package helpbuddy.task;

import helpbuddy.exception.HelpBuddyException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class creates a Task storing a start time and an end time of type LocalDateTime.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event object with specified String description and LocalDateTime from as start time of Event
     * and LocalDateTime to as end time of Event.
     * @param description the description of Task.
     * @param from the start time of Event.
     * @param to the end time of Event.
     * @throws HelpBuddyException if description, from and to is empty and if from and to are invalid time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws HelpBuddyException {
        super(description);
        this.from = from;
        this.to = to;
        if (description.isBlank()) {
            throw new HelpBuddyException("The description of a event cannot be empty.\n");
        } else if (from == null) {
            throw new HelpBuddyException("Please enter /from followed by a start time of event.\n");
        } else if (to == null) {
            throw new HelpBuddyException("Please enter /to followed by an end time of event.\n");
        } else if (to.isBefore(from)) {
            throw new HelpBuddyException("End time must be after the start time!\n");
        } else if (to.isEqual(from)) {
            throw new HelpBuddyException("Both start and end time are the same, please check!\n");
        }
    }
    /**
     * Returns String representing the Event object.
     * @return a string representation of description, isDone, from and to.
     */
    @Override
    public String toString() {
        String startTime = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String endTime = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    /**
     * Returns String representing the Event object to be saved in a file.
     * @return a string representation of description, isDone, from and to.
     */
    @Override
    public String stringifyTask() {
        return String.format("E|%d|%s|%s", this.isDone ? 1 : 0, this.description, this.from + " to " + this.to);
    }

    /**
     * Compares the object to the specified object. The result is true if and only if argument is not null and
     * its stringifyTask() is the same as the object's.
     * @param obj the object to compare with.
     * @return true if objects are the same; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Event) {
            Event task = (Event) obj;
            return this.stringifyTask().equals(task.stringifyTask());
        }

        return false;
    }

}
