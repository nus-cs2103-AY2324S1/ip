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
