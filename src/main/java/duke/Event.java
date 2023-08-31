package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private static final Pattern PATTERN_COMMAND_CREATE_EVENT =
            Pattern.compile("^event ?(?<taskName>.*?)? ?(/from (?<startTime>.*?))? ?(/to (?<endTime>.*))?$");

    private String startTime;
    private String endTime;

    Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime.trim();
        this.endTime = endTime.trim();
    }

    Event(boolean isDone, String name, String startTime, String endTime) {
        super(name, isDone);
        this.startTime = startTime.trim();
        this.endTime = endTime.trim();
    }

    /**
     * Creates and Returns an Event
     *
     * @param command String command that specifies the values the created Event object should have
     * @return Event created
     * @throws LukeException If command is of an invalid format
     */
    public static Event createEvent(String command) throws LukeException {
        Matcher matcher = PATTERN_COMMAND_CREATE_EVENT.matcher(command);
        matcher.find();

        String taskName = matcher.group("taskName");
        if (taskName == null || taskName.isBlank()) {
            throw new LukeException("The description of an event cannot be empty.");
        }

        String startTime = matcher.group("startTime");
        if (startTime == null || startTime.isBlank()) {
            throw new LukeException("The start time (/from ...) of an event cannot be empty.");
        }

        String endTime = matcher.group("endTime");
        if (endTime == null || endTime.isBlank()) {
            throw new LukeException("The end time (/end ...) of an event cannot be empty.");
        }

        return new Event(taskName, startTime, endTime);
    }

    /**
     * Creates and Returns an Event
     * This function assumes correct ordering of args provided
     *
     * @param args Arguments used to create the Event from its constructor
     * @param isDone Boolean indicating if the Event is done
     * @return Event object created
     * @throws LukeException If there is insufficient/ excessive number of arguments in args
     */
    public static Event createEvent(String[] args, boolean isDone) throws LukeException {
        if (args.length != 3) {
            throw new LukeException("Error creating Event: Incorrect number of arguments");
        }

        return new Event(isDone, args[0], args[1], args[2]);
    }

    @Override
    public String toSaveStr() {
        return "E"
                + " | " + super.toSaveStr()
                + " | " + startTime
                + " | " + endTime;
    }

    /**
     * Determines if this Event is equal to another object
     *
     * @param o Other object to be compared with
     * @return true if o is an Event, satisfies the equals condition of its superclass,
     *         and has the same start and end time.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event eventObj = (Event) o;

            return super.equals(o)
                    && startTime.equals(eventObj.startTime)
                    && endTime.equals(eventObj.endTime);
        }

        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
