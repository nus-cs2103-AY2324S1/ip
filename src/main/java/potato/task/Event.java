package potato.task;

import potato.*;

/**
 * The Event class represents a task with a specified start and end date.
 * It extends the Task class and adds event-related functionality.
 */
public class Event extends Task {
    private static final String LINE = "-----------------------------------------\n";
    protected DateTime start;
    protected DateTime end;
    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the provided description, start and end dates, completion status, and priority.
     *
     * @param description The description of the event.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     * @param isDone      The completion status of the event.
     * @param priority    The priority of the event.
     */
    public Event(String description, String from, String to, boolean isDone, String priority) {
        super(description, isDone, priority);
        start = new DateTime(from);
        end = new DateTime(to);
        this.from = from;
        this.to = to;
    }

    /**
     * Parses a user input string to create an Event object.
     *
     * @param input The user input string representing the event task.
     * @return An Event object created from the input.
     * @throws PotatoException If the input is empty or invalid.
     */
    public static Event parseEvent(String input) {
        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");
        if (input.length() < 1) {
            throw new PotatoException("Bruh you wanna do air or something?");
        } else if (indexFrom < 0) {
            throw new PotatoException("Looks like your event don't need to start?");
        } else if (indexTo < 0) {
            throw new PotatoException("Looks like your event goes on forever?");
        } else {
            assert input.length() > 1 : "input length should be > 1";
            return new Event(input.substring(0, indexFrom - 1),
                    input.substring(indexFrom + 6, indexTo - 1),
                    input.substring(indexTo + 4), false, "NIL");
        }
    }

    /**
     * Parses a saved representation of an Event object.
     *
     * @param input The string array containing the saved representation of the event task.
     * @return An Event object created from the saved data.
     */
    public static Event parseSavedEvent(String[] input) {
        return new Event(input[2], input[3], input[4], input[1].equals("1"), input[5]);
    }

    /**
     * Converts the Event object to a string representation suitable for saving.
     *
     * @return A string representation of the Event object for saving.
     */
    @Override
    public String toSave() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to + " | " + priority.toUpperCase();
    }

    /**
     * Converts the Event object to a string for displaying to the user.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.getDate() + " to: " + end.getDate() + ")";
    }
}
