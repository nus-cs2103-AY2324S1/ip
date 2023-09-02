package duck.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duck.DuckException;
import duck.Parser;

/**
 * Represents a event task.
 */
public class EventTask extends Task {
    LocalDate start;
    LocalDate end;

    /**
     * Creates a new EventTask.
     * 
     * @param name   Name of the task.
     * @param isDone Status of the task.
     * @param start Start of the task.
     * @param end End of the task.
     */
    public EventTask(String name, boolean isDone, LocalDate start, LocalDate end) {
        super(name,  isDone);
        this.start = start;
        this.end = end;
    }

    private String formatStart() {
        return start.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    private String formatEnd() {
        return end.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    @Override
    public String stringify() {
        String startString = formatStart();
        String endString = formatEnd();
        return "E" + super.stringify() + 
                startString.length() + "/" + startString + 
                endString.length() + "/" + endString;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatStart() + " to " + formatEnd() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EventTask) {
            EventTask other = (EventTask) obj;
            return super.equals(other) && start.equals(other.start) && end.equals(other.end);
        }
        return false;
    }

    /**
     * Parses the savable string representation of a EventTask.
     * Implementation depends on stringify format.
     * 
     * @param fileLine Savable string representation of the task.
     * @return EventTask parsed from the string representation.
     * @throws DuckException If the string representation is invalid.
     */
    public static EventTask parse(String fileLine) throws DuckException {

        // Finding isDone
        boolean isDone = fileLine.charAt(1) == '1';

        // Finding name
        int slashIndex = fileLine.indexOf("/");
        int nameLength = Integer.parseInt(fileLine.substring(2, slashIndex));
        String name = fileLine.substring(slashIndex + 1, slashIndex + 1 + nameLength);

        // Finding start
        int secondSlashIndex = fileLine.indexOf("/", slashIndex + 1);
        String startString = fileLine.substring(secondSlashIndex + 1, secondSlashIndex + 12);
        LocalDate start = LocalDate.parse(startString, Parser.fileDateFormatter);

        // Finding end
        int thirdSlashIndex = fileLine.indexOf("/", secondSlashIndex + 1);
        String endString = fileLine.substring(thirdSlashIndex + 1);
        LocalDate end = LocalDate.parse(endString, Parser.fileDateFormatter);

        return new EventTask(name, isDone, start, end);
    }
}