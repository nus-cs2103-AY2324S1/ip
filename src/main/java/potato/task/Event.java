package potato.task;

import potato.*;
public class Event extends Task {
    private static final String LINE = "-----------------------------------------\n";
    protected DateTime start;
    protected DateTime end;
    protected String from;
    protected String to;

    public Event(String description, String from, String to, boolean isDone, String priority) {
        super(description, isDone, priority);
        start = new DateTime(from);
        end = new DateTime(to);
        this.from = from;
        this.to = to;
    }

    public static Event eventParse(String input) {
        int indexFrom = input.indexOf("/from");
        int indexTo = input.indexOf("/to");
        if (input.length() < 1) {
            new PotatoException(LINE + "Bruh you wanna do air or something?\n" + LINE);
            return null;
        } else {
            assert input.length() > 1 : "input length should be > 1";
            return new Event(input.substring(0, indexFrom - 1),
                    input.substring(indexFrom + 6, indexTo - 1),
                    input.substring(indexTo + 4), false, "NIL");
        }
    }

    public static Event eventSavedParse(String[] input) {
        return new Event(input[2], input[3], input[4], input[1].equals("1"), input[5]);
    }

    @Override
    public String toSave() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to + " | " + priority.toUpperCase();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.getDate() + " to: " + end.getDate() + ")";
    }
}
