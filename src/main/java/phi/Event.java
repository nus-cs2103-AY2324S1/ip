package phi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String start;
    private String end;

    public Event(String msg, boolean isDone, String start, String end) {
        super(Type.E, isDone, msg);
        this.start = start;
        this.end = end;
        LocalDate startDate;
        LocalDate endDate;
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

        try {
            startDate = LocalDate.parse(start, inputFormat);
            this.start = startDate.format(outputFormat);
        } catch (DateTimeParseException e) {
            //System.out.println("Can't find a proper date format, using /from input as a String");
        }

        try {
            endDate = LocalDate.parse(end, inputFormat);
            this.end = endDate.format(outputFormat);
        } catch (DateTimeParseException e) {
            //System.out.println("Can't find a proper date format, using /to input as a String");
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (from: %s to: %s)", start, end);
    }

    public static Event newEvent(String input) {
        if (input.equals("event")) {
            throw new IllegalArgumentException("You gotta put an actual message in...");
        } else if (!input.startsWith("event ")) {
            throw new IllegalArgumentException(
                    String.format("Hey genius, did you mean \"event %s\"...", input.substring(5)));
        } else if (!input.contains("/from") && !input.contains("/to")) {
            throw new IllegalArgumentException("Congratulations you're the only idiot who would leave out both " +
                    "\"/from\" and \"/to\" flags");
        } else if (!input.contains("/from")) {
            throw new IllegalArgumentException("You need to add a \"/from\" flag before the \"/to\" flag, halfwit");
        } else if (!input.contains("/to")) {
            throw new IllegalArgumentException("You need to add a \"/to\" flag after the \"/from\" flag, dimwit");
        }

        int fromFlagStart = input.indexOf("/from");
        int fromFlagEnd = fromFlagStart + 6;
        int toFlagStart = input.indexOf("/to");
        int toFlagEnd = toFlagStart + 4;

        if (fromFlagStart > toFlagStart) {
            throw new IllegalArgumentException("Please put the \"/from\" flag before the \"/to\" flag, thanksss");
        } else if (fromFlagStart == 6) {
            throw new IllegalArgumentException("Come on you have to fill in something...");
        } else if (toFlagStart == fromFlagEnd) {
            throw new IllegalArgumentException("Your \"/from\" flag can't be empty! Leave a space if you want it blank.");
        } else if (input.endsWith("/to")) {
            throw new IllegalArgumentException("Your \"/to\" flag can't be empty! Leave a space if you want it blank.");
        }

        String taskMsg = input.substring(6, fromFlagStart - 1);
        String fromStr = input.substring(fromFlagEnd, toFlagStart - 1);
        String toStr = input.substring(toFlagEnd);

        return new Event(taskMsg, false, fromStr, toStr);
    }

    @Override
    public String outputFormat() {
        return String.format("%s|%b|%s|%s|%s", taskType.toString(), done, taskName, start, end);

    }
}