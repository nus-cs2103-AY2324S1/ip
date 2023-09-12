package peko.tasks;

import peko.DateTimeHandler;
import peko.exceptions.InvalidTaskException;

/**
 * The Event class represents a task that spans a period, starting and ending at specific times.
 * It extends the Task class and includes additional functionality for handling events.
 */
public class Event extends Task {
    private char type = 'E';
    private DateTimeHandler startHandler;
    private DateTimeHandler endHandler;
    /**
     * Constructs an Event object with the specified description.
     *
     * @param s The description of the event task, including the task name, start time, and end time.
     * @throws InvalidTaskException If the provided description is invalid or missing essential parts.
     */
    public Event(String s) throws InvalidTaskException {
        super(s);
        String[] split = s.split(" /from ");
        if (split.length == 1) {
            System.out.println("There's no start date peko!");
            return;
        } else if (split.length >= 3) {
            System.out.println("You can't have two start dates peko!");
            return;
        }

        split[0].split(" ", 2);
        this.name = split[0];
        split = split[1].split(" /to ");
        if (split.length == 1) {
            System.out.println("There's no end date peko!");
            return;
        } else if (split.length >= 3) {
            System.out.println("You can't have two end dates peko!");
            return;
        }
        startHandler = new DateTimeHandler(split[0]);
        endHandler = new DateTimeHandler(split[1]);
    }

    /**
     * Converts the {@code Event} object to a formatted string representation.
     *
     * @return A formatted string representing the event task, including its type, status,
     *         task name, start time, and end time.
     */
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString() + " (from: " + startHandler.stringDisplay()
                + " to: " + endHandler.stringDisplay() + ")";
    }

    /**
     * Converts the {@code Event} object to a string for storage in a text file.
     *
     * @return A string representation of the event task suitable for storage in a text file.
     */
    public String toStore() {
        String curr = this.status ? "0" : "1";
        return "E" + " | " + curr + " | " + this.name + " | " + startHandler.toString() + " | " + endHandler.toString();
    }
}
